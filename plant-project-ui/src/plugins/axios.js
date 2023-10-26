import axios from "axios";

const ACCEPTED_STATUS = [200, 201, 202, 204, 400];

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
        return { status: status, body: body };
      },
      (error) => {
        return Promise.reject(error);
      }
    );
    // Utilasation des global properties pour accéder à axios avec sa configuration dans toute l'app
    // Via $axios
    app.config.globalProperties.$axios = axiosInstance;
  },
};
