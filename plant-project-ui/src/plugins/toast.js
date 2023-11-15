import { Toast } from "bootstrap";

function showToast(id, color, message) {
  const toastDiv = document.querySelector("toast-container");
  const toast = new Toast(toastDiv);
  console.log(toastDiv);
  const classes = toastDiv.classList;
  classes.add(color);
  const body = container.querySelector(".toast-body");
  body.textContent = message;
  toast.show();
}

export default {
  install: (app) => {
    app.config.globalProperties.$toast = {
      success: (id, msg) => {
        showToast(id, "text-bg-success", msg);
      },
      error: (id, msg) => {
        showToast(id, "text-bg-danger", msg);
      },
    };
  },
};
