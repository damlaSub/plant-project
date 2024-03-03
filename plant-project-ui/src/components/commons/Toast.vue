<script>
  export default {
    props: {
      id: {
        type: String,
        default: null,
      },
    },
  };
</script>
<template>
  <div :id="id" class="toast-container p-3 top-0 start-50 translate-middle-x">
    <div class="toast shadow-sm">
      <div class="d-flex">
        <div class="toast-body"></div>
        <button
          type="button"
          class="btn-close me-2 m-auto"
          data-bs-dismiss="toast"
        ></button>
      </div>
    </div>
  </div>
</template>
