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
  <div
    :id="id"
    class="toast-container align-items-center text-white bg-success border-0"
    role="alert"
    aria-live="assertive"
    aria-atomic="true"
  >
    <div class="toast">
      <div class="d-flex">
        <div class="toast-body">Message</div>
        <button
          type="button"
          class="btn-close btn-close-white me-2 m-auto"
          data-bs-dismiss="toast"
          aria-label="Close"
        ></button>
      </div>
    </div>
  </div>
</template>
