const navigationGuard = (to, from, next) => {
  const isAuthenticated = localStorage.isAuthenticated;
  const role = localStorage.role;
  const admin = "ADMIN";
  const user = "USER";
  if (to.meta.requiresAuth && isAuthenticated == undefined) {
    // Redirect to sign in if trying to access a protected route without authentication
    next("/signin");
  } else if (
    (to.meta.requiresAuth == false && isAuthenticated && role.includes(user)) ||
    (to.meta.role == admin && role.includes(user))
  ) {
    next("/user/home");
  } else if (
    (to.meta.requiresAuth == false &&
      isAuthenticated &&
      role.includes(admin)) ||
    (to.meta.role == user && role.includes(admin))
  ) {
    next("/admin/plants");
  } else {
    next();
  }
};
export default navigationGuard;
