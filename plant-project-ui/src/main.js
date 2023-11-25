import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import axios from "./plugins/axios";
import toast from "./plugins/toast";

import "/index.css";

const app = createApp(App);

app.use(router);
app.use(axios);
app.use(toast);
app.mount("#app");
