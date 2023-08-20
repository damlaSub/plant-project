<template>
  <div class="mb-3 p-5">
    <h1 class="fs-4 card-title fw-bold mb-4">View all plants</h1>
    <div class="d-grid d-md-flex justify-content-md-end">
      <a href="/admin/create"
        ><button role="button" class="btn btn-create">
          <i class="bi bi-plus-circle"></i> Create a plant
        </button></a
      >
    </div>
    <div class="container-xl">
      <div class="row row-title fw-bold py-3">
        <div class="col">Name</div>
        <div class="col">Latin name</div>
        <div class="col">Description</div>
        <div class="col">Image</div>
        <div class="col">Water level</div>
        <div class="col">Sun level</div>
        <div class="col">Action</div>
      </div>
    </div>
    <div class="container-xl">
      <div class="row py-2 border border-light border-top-0 rounded-bottom">
        <div class="col">Monstera</div>
        <div class="col">Monstera deliciosa</div>
        <div class="col">Lorem ipsum...</div>
        <div class="col">Image</div>
        <div class="col">Low</div>
        <div class="col">Part sun</div>
        <div class="col">
          <button type="button" role="button" class="btn btn-update">
            <i class="bi bi-pencil"></i>
          </button>
          <button type="button" class="btn btn-delete">
            <i class="bi bi-trash"></i>
          </button>
        </div>
      </div>
    </div>
    <div class="container-xl">
      <div class="row py-2 border border-light border-top-0 rounded-bottom">
        <div class="col">Monstera</div>
        <div class="col">Monstera deliciosa</div>
        <div class="col">Lorem ipsum...</div>
        <div class="col">Image</div>
        <div class="col">Low</div>
        <div class="col">Part sun</div>
        <div class="col">
          <button type="button" role="button" class="btn btn-update">
            <i class="bi bi-pencil"></i>
          </button>
          <button type="button" class="btn btn-delete">
            <i class="bi bi-trash"></i>
          </button>
        </div>
      </div>
    </div>
  </div>
</template>
<style>
  .btn-create {
    color: white;
    background-color: #355e3b;
    border-color: #355e3b;
  }
  .btn-create:hover {
    color: white;
    border-color: white;
    background-color: #355e3b;
    cursor: pointer;
  }
  .row-title {
    background-color: #f9f5f1;
  }
  .btn-update,
  .btn-update:hover,
  .btn-update:focus {
    color: green;
  }
  .btn-delete,
  .btn-delete:hover,
  .btn-delete:focus {
    color: red;
  }
</style>
