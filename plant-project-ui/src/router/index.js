import { createRouter, createWebHistory } from "vue-router";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: () => import("../views/HomeView.vue"),
    },
    {
      path: "/login",
      name: "login",
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import("../views/LoginView.vue"),
    },
    {
      path: "/signup",
      name: "signup",
      component: () => import("../views/SignupView.vue"),
    },
    {
      path: "/admin/plants/create",
      name: "create",
      component: () => import("../views/CreatePlantView.vue"),
    },
    {
      path: "/admin/plants",
      name: "edit",
      component: () => import("../views/EditPlantsView.vue"),
    },
    {
      path: "/admin/plants/:id/update",
      name: "update",
      component: () => import("../views/UpdatePlantView.vue"),
    },
  ],
});

export default router;
