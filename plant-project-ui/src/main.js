import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import axios from "./plugins/axios";
import i18n from "./i18n/i18n.js";
import toast from "./plugins/toast";

import "/index.css";

const app = createApp(App);

app.use(router);
app.use(axios);
app.use(i18n);
app.use(toast);
app.mount("#app");
