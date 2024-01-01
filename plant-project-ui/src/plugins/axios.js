import { reactive, watch } from "vue";
import axios from "axios";

const ACCEPTED_STATUS = [200, 201, 202, 204, 400];
const auth = reactive({
  isAuthenticated: false,
  role: null,
});

export default {
  install: (app) => {
    // Création et configuration d'une instance axios
    const axiosInstance = axios.create({
      baseURL: import.meta.env.VITE_API_BASE_URL,
      validateStatus: (status) => {
        return ACCEPTED_STATUS.includes(status);
      },
    });
    axiosInstance.interceptors.response.use(
      (response) => {
        const status = response.status;
        const data = response.data;
        const body = data != "" ? data : null;
        if (response.data.token) {
          const accesToken = data.token;
          const refreshToken = data.refreshToken;
          const role = data.role;
          const userName = data.firstName;
          localStorage.clear();
          localStorage.setItem("token", accesToken);
          localStorage.setItem("refreshToken", refreshToken);
          localStorage.setItem("isAuthenticated", true);
          localStorage.setItem("role", role);
          localStorage.setItem("userName", userName);
          auth.isAuthenticated = true;
          auth.role = role;
        }
        return { status: status, body: body };
      }
      // (error) => {
      //   if (error.response && error.response.status === 401) {
      //     // Unauthorized, possibly due to an expired token
      //     // Log the user out and redirect to the login page
      //     localStorage.clear();
      //     $router.push("/signin");
      //   }
      //   return Promise.reject(error);
      // }
    );
    axiosInstance.interceptors.request.use(
      (config) => {
        const token = localStorage.getItem("token");
        if (token) {
          // Check if the token is expired or not
          const decodedToken = parseJwt(token);
          if (decodedToken.exp * 1000 < Date.now()) {
            // Token is expired, attempt to refresh it
            return refreshToken().then((newToken) => {
              config.headers.Authorization = `Bearer ${newToken}`;
              return config;
            });
          } else {
            // Token is still valid, attach it to the request headers
            config.headers.Authorization = `Bearer ${token}`;
          }
        }
        return config;
      },
      (error) => {
        return Promise.reject(error);
      }
    );

    function parseJwt(token) {
      const base64Url = token.split(".")[1];
      const base64 = base64Url.replace(/-/g, "+").replace(/_/g, "/");
      const jsonPayload = decodeURIComponent(
        atob(base64)
          .split("")
          .map(function (c) {
            return "%" + ("00" + c.charCodeAt(0).toString(16)).slice(-2);
          })
          .join("")
      );
      return JSON.parse(jsonPayload);
    }

    async function refreshToken() {
      // Implement your token refresh logic here
      // This function should return a Promise that resolves with the new token
      // If the refresh fails, the Promise should reject
      const refreshToken = localStorage.getItem("refreshToken");

      // Check if a refreshToken is available
      if (!refreshToken) {
        // No refreshToken, reject the Promise
        return Promise.reject(new Error("No refreshToken available"));
      }

      // Make a request to the server to refresh the token
      try {
        const response = await fetch(
          `${import.meta.env.VITE_API_BASE_URL}/auth/refresh-token`,
          {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
            },
            body: refreshToken,
          }
        );
        console.log(response);
        // Assuming the server responds with a new token
        const newToken = response.body.token;

        // Update the local storage with the new token
        localStorage.setItem("token", newToken);
        return newToken;
      } catch (error) {
        // Handle the error, possibly log the user out
        console.error("Token refresh failed:", error);
        throw error; // Propagate the error
      }
    }

    watch(
      () => [auth.isAuthenticated, auth.role],
      ([isAuthenticated, role]) => {
        // Do something when isAuthenticated or role changes
        console.log("isAuthenticated:", isAuthenticated);
        console.log("role:", role);
        console.log("userName:", localStorage.userName);
      }
    );

    // Utilasation des global properties pour accéder à axios avec sa configuration dans toute l'app
    // Via $axios
    app.config.globalProperties.$axios = axiosInstance;
  },
};
