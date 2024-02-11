function showTooltip(id, clr, msg) {
  const myTooltipEl = document.querySelector(`#${id}`);

  if (!myTooltipEl) {
    // If tooltip element is not found, delay execution
    setTimeout(() => {
      showTooltip(id, clr, msg); // Retry after a short delay
    }, 100);
    return;
  }

  const classes = myTooltipEl.classList;
  classes.add(clr);
  const opacity = "opacity-90";
  classes.add(opacity);
  myTooltipEl.textContent = msg;
  myTooltipEl.addEventListener(
    "hidden.bs.tooltip",
    () => {
      classes.remove(clr);
      classes.remove(opacity);
      msg = "";
    },
    { once: true }
  );
  const tooltip = bootstrap.Tooltip.getOrCreateInstance(myTooltipEl);
  tooltip.show();
}

export default {
  install: (app) => {
    app.config.globalProperties.$tooltip = {
      success: (id, msg) => {
        showTooltip(id, "text-bg-success", msg);
      },
      error: (id, msg) => {
        showTooltip(id, "text-bg-danger", msg);
      },
    };
  },
};
