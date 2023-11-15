import { createApp } from "vue";
import App from "./App.vue";
import router from "./router"; // Import the router configuration
import axios from "./plugins/axios";
import toast from "./plugins/toast";

const app = createApp(App);

app.use(router); // Add the router
app.use(axios);
app.use(toast);
app.mount("#app");
