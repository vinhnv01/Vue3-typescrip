import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import "ant-design-vue/dist/reset.css";
import antd from "ant-design-vue";
import { library } from "@fortawesome/fontawesome-svg-core";
import { fas } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";

library.add(fas);

const app = createApp(App);
app.use(antd);
app.component("font-awesome-icon", FontAwesomeIcon);
app.use(router);
app.mount("#app");
