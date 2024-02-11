<script>
  export default {
    props: {
      id: {
        type: String,
        required: true,
        default: null,
      },
    },
    data() {
      return {
        visible: false,
      };
    },
    mounted() {
      // Automatically hide tooltip after a certain duration
      setTimeout(() => {
        this.visible = false;
      }, 5000);
    },
  };
</script>
<template>
  <div>
    <div :id="id" class="tooltip" role="tooltip">
      <div class="tooltip-arrow"></div>
      <div class="tooltip-inner"></div>
    </div>
  </div>
</template>
<style>
  #tooltip-global.text-bg-danger {
    display: block;
    margin: 5px;
    font-size: 0.875rem;
    border-radius: 0.25rem;
    width: 100%;
    padding: 0.375rem 0.75rem;
    font-size: 1rem;
    line-height: 1.5;
    color: white;
    background-color: red;
    background-clip: padding-box;
    -webkit-appearance: none;
    -moz-appearance: none;
    appearance: none;
    border-radius: 0.375rem;
    transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
  }
</style>
