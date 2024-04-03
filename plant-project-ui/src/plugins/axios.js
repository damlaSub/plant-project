import axios from "axios";
import Base64 from "base-64";

const ACCEPTED_STATUS = [200, 201, 202, 204];

export default {
  install: (app) => {
    const axiosInstance = createAxiosInstance();

    setupAxiosInterceptors(axiosInstance);

    app.config.globalProperties.$axios = axiosInstance;
  },
};

function createAxiosInstance() {
  return axios.create({
    baseURL: import.meta.env.VITE_API_BASE_URL,
    validateStatus: (status) => ACCEPTED_STATUS.includes(status),
  });
}

function setupAxiosInterceptors(axiosInstance) {
  axiosInstance.interceptors.response.use(handleResponse, handleErrorResponse);

  axiosInstance.interceptors.request.use(handleRequest, handleRequestError);
}

async function handleResponse(response) {
  const status = response.status;
  const data = response.data;
  const body = data !== "" ? data : null;

  if (response.data.token) {
    updateLocalStorage(data);
  }

  return { status, body };
}

function handleErrorResponse(error) {
  if (
    (error.response && error.response.status === 401) ||
    error.response.status === 403
  ) {
    handleAuthError();
  }
  return Promise.reject(error);
}

async function handleRequest(config) {
  const token = localStorage.getItem("token");
  const refreshToken = localStorage.getItem("refreshToken");

  if (token && isTokenExpired(token) && refreshToken) {
    try {
      const newToken = await refreshJWToken();
      config.headers.Authorization = `Bearer ${newToken}`;
    } catch (error) {
      console.error("RefreshToken failed:", error);
      handleAuthError();
      return Promise.reject(error);
    }
  } else if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }

  return config;
}

function handleRequestError(error) {
  return Promise.reject(error);
}

function updateLocalStorage(data) {
  const { token, refreshToken, role, firstName } = data;
  localStorage.clear();
  localStorage.setItem("token", token);
  localStorage.setItem("refreshToken", refreshToken);
  localStorage.setItem("isAuthenticated", true);
  localStorage.setItem("role", role);
  localStorage.setItem("userName", firstName);
}

function isTokenExpired(token) {
  const expirationTime = getExpirationTime(token);
  return expirationTime < Date.now();
}

function getExpirationTime(token) {
  const [header, payload] = token.split(".").slice(0, 2);
  const decodedPayload = JSON.parse(Base64.decode(payload));
  const expirationTime = decodedPayload.exp;
  return new Date(expirationTime * 1000);
}

async function refreshJWToken() {
  const refreshToken = localStorage.getItem("refreshToken");

  if (!refreshToken) {
    return Promise.reject(new Error("No refreshToken available"));
  }

  try {
    const response = await fetch(
      `${import.meta.env.VITE_API_BASE_URL}/auth/refresh-token`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ refreshToken }),
      }
    );

    const responseData = await response.json();
    const newToken = responseData.token;

    localStorage.setItem("token", newToken);
    return newToken;
  } catch (error) {
    console.error("Token refresh failed:", error);
    handleAuthError();
  }
}

function handleAuthError() {
  localStorage.clear();
  $router.push("/signin");
}
