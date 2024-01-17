import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import UpdateProductView from "@/views/UpdateProductView.vue";
import AboutView from "@/views/AboutView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: HomeView,
    },
    {
      path: "/about",
      name: "about",
      component: AboutView,
    },
    {
      path: "/update/:id",
      name: "update-product",
      component: UpdateProductView,
      props: true, // Điều này cho phép các thông số tuyến đường được truyền dưới dạng đạo cụ cho thành phần
    },
  ],
});

export default router;
