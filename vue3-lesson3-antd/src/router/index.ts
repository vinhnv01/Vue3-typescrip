import Dashboard from "@/components/Dashboard.vue";
import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";
import UserView from "../views/UserView.vue";
import SettingView from "../views/SettingView.vue";
import AddUser from "@/views/AddUser.vue";
import UpdateUser from "@/views/UpdateUser.vue";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    name: "home",
    component: Dashboard,
    children: [
      {
        path: "/view-user",
        name: "view-user",
        component: UserView,
      },
      {
        path: "/add-user",
        name: "add-user",
        component: AddUser,
      },
      {
        path: "/update-user/:id",
        name: "update-user",
        component: UpdateUser,
      },
      {
        path: "/view-setting",
        name: "view-setting",
        component: SettingView,
      },
    ],
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
