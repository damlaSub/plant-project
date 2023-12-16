import { createRouter, createWebHistory } from "vue-router";

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
        navbar: () => import("../components/commons/AdminHeader.vue"),
        default: () => import("../views/CreatePlantView.vue"),
      },
      meta: { requiresAuth: true, role: "ADMIN" },
    },
    {
      path: "/admin/plants",
      name: "edit",
      components: {
        navbar: () => import("../components/commons/AdminHeader.vue"),
        default: () => import("../views/EditPlantsView.vue"),
      },
      meta: { requiresAuth: true, role: "ADMIN" },
    },
    {
      path: "/admin/plants/:id/update",
      name: "update",
      components: {
        navbar: () => import("../components/commons/AdminHeader.vue"),
        default: () => import("../views/UpdatePlantView.vue"),
      },
      meta: { requiresAuth: true, role: "ADMIN" },
    },
  ],
});

router.beforeEach((to, from, next) => {
  if (to.meta.requiresAuth && localStorage.isAuthenticated == undefined) {
    // Redirect to sign in if trying to access a protected route without authentication
    next("/signin");
    // Redirect to landing page if role_user trying to access an  protected role_admin route
  } else if (to.meta.role == "ADMIN" && localStorage.role.includes("_USER")) {
    next("/");
  } else {
    // Continue to the requested route
    next();
  }
});

export default router;
