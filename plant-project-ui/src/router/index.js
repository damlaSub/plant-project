import { createRouter, createWebHistory } from "vue-router";
import navigationGuard from "./navigationGuard ";

const admin = "ADMIN";
const user = "USER";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      components: {
        navbar: () => import("../components/commons/Header.vue"),
        default: () => import("../views/HomeView.vue"),
      },
      meta: { requiresAuth: false },
    },
    {
      path: "/plants/:id/detail",
      name: "plant-detail",
      components: {
        navbar: () => import("../components/commons/Header.vue"),
        default: () => import("../views/PlantDetailView.vue"),
      },
      meta: { requiresAuth: false },
    },
    {
      path: "/signin",
      name: "signin",
      component: () => import("../views/SigninView.vue"),
    },
    {
      path: "/signup",
      name: "signup",
      component: () => import("../views/SignupView.vue"),
    },
    {
      path: "/admin/plants/create",
      name: "create",
      components: {
        navbar: () => import("../components/plants/admin/AdminHeader.vue"),
        default: () => import("../views/CreatePlantView.vue"),
      },
      meta: { requiresAuth: true, role: admin },
    },
    {
      path: "/admin/plants",
      name: "edit",
      components: {
        navbar: () => import("../components/plants/admin/AdminHeader.vue"),
        default: () => import("../views/EditPlantsView.vue"),
      },
      meta: { requiresAuth: true, role: admin },
    },
    {
      path: "/admin/plants/:id/update",
      name: "update",
      components: {
        navbar: () => import("../components/plants/admin/AdminHeader.vue"),
        default: () => import("../views/UpdatePlantView.vue"),
      },
      meta: { requiresAuth: true, role: admin },
    },
    {
      path: "/dashboard",
      name: "dashboard",
      components: {
        navbar: () => import("../components/plants/user/UserHeader.vue"),
        default: () => import("../views/UserDashboardView.vue"),
      },
      meta: { requiresAuth: true, role: user },
    },
    {
      path: "/user/home",
      name: "user-home",
      components: {
        navbar: () => import("../components/plants/user/UserHeader.vue"),
        default: () => import("../views/UserHomeView.vue"),
      },
      meta: { requiresAuth: true, role: user },
    },
    {
      path: "/user/plants/:id",
      name: "user-plant-detail",
      components: {
        navbar: () => import("../components/plants/user/UserHeader.vue"),
        default: () => import("../views/PlantDetailView.vue"),
      },
      meta: { requiresAuth: true, role: user },
    },
    {
      path: "/:catchAll(.*)",
      component: () => import("../components/commons/NotFound.vue"),
    },
  ],
});

router.beforeEach(navigationGuard);
export default router;
