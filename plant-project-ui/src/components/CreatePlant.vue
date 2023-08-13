<script>
  import { useVuelidate } from "@vuelidate/core";
  import { maxLength, maxValue } from "@vuelidate/validators";
  export default {
    setup() {
      return { v$: useVuelidate() };
    },
  };
</script>

<template>
  <div class="mb-3 p-5">
    <h1 class="fs-4 card-title fw-bold mb-4">Create a plant</h1>
    <form class="row g-3" novalidate @submit.prevent="">
      <div class="col-md-4">
        <label for="input-name" class="form-label" maxlength="100">Name</label>
        <input
          name="input-name"
          type="text"
          class="form-control"
          id="input-name"
          required
        />
        <div id="input-name-helptext" class="fw-light">Plant's name</div>
      </div>
      <div class="col-md-8">
        <label for="latin" class="form-label" maxlength="200">Latin name</label>
        <input
          name="latin"
          type="text"
          class="form-control"
          id="latin"
          required
        />
        <div id="latin-helptext" class="fw-light">Plant's name in latin</div>
      </div>
      <div class="col-md-4">
        <label for="select-water" class="form-label">Water level</label>
        <select name="waterId" class="form-select" id="water" required>
          <option value="">Select water</option>
          <option value="1">1</option>
          <option value="2">2</option>
          <option value="3">3</option>
        </select>
        <div id="water-helptext" class="fw-light">Plant's water level</div>
      </div>
      <div class="col-md-4">
        <label for="select-sun" class="form-label">Sun level</label>
        <select name="sunId" class="form-select" id="sun" required>
          <option value="">Select sun</option>
          <option value="1">1</option>
          <option value="2">2</option>
          <option value="3">3</option>
        </select>
        <div id="sun-helptext" class="fw-light">Plant's sun level</div>
      </div>
      <div class="col-md-4">
        <label for="image" class="form-label" maxlength="100">Image</label>
        <input
          name="image"
          type="file"
          class="form-control"
          id="image"
          required
          accept="image/png,image/gif,image/jpeg"
          @change=""
          @keyup.esc=""
        />
        <div class="form-text text-danger">
          Image size must be less than 500ko
        </div>
        <div class="form-text mb-3">Plant's image</div>
      </div>
      <div class="col-12">
        <label for="description" class="form-label">Description</label>
        <textarea
          name="description"
          class="form-control"
          id="description"
          style="height: 100px"
          minlength="1"
          maxlength="1000"
          required
        ></textarea>
        <div class="form-text text-danger">
          Text with a maximum of 1000 chars.
        </div>
        <div id="description-helptext" class="fw-light">
          Plant's description.
        </div>
      </div>
      <div class="d-grid d-md-flex justify-content-md-end">
        <button type="button" class="btn">Save</button>
      </div>
    </form>
  </div>
</template>
<style>
  .btn,
  button {
    color: white;
    background-color: #355e3b;
    border-color: #355e3b;
  }
  .btn:hover,
  button:hover {
    color: white;
    border-color: white;
    background-color: #355e3b;
    cursor: pointer;
  }
  #input-name:focus,
  #latin:focus,
  #water:focus,
  #sun:focus,
  #image:focus,
  #description:focus {
    border-color: #355e3b;
    box-shadow: 0 0 0 0.2rem #355e3b;
  }
</style>
