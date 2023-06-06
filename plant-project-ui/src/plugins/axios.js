import axios from "axios";

const ACCEPTED_STATUS = [200, 201, 202, 204, 400];

export default {
  install: (app) => {
    const http = axios.create({
      baseURL: import.meta.env.VITE_API_BASE_URL,
      validateStatus: (status) => {
        return ACCEPTED_STATUS.includes(status);
      },
    });
    app.config.globalProperties.$http = http;
  },
};
