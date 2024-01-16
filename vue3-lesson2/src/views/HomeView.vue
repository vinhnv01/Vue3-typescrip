<script setup lang="ts">
  import { ref } from "vue";
  import ProductInterface from "../types/Product";

  /**
   * Product
   *
   * id - string
   * url - string
   * name - string
   * status - string ( 'Kinh doanh ' | 'Không kinh doanh')
   * price - number
   * note - string
   **/

  const listProduct = ref<ProductInterface[]>([]);
  const newProduct = ref<ProductInterface>({});

  function addProduct() {
    listProduct.value.push({
      id: Date.now().toString(),
      name: newProduct.value.name,
      url: newProduct.value.url,
      price: newProduct.value.price,
      note: newProduct.value.note,
      status: newProduct.value.status,
    });
  }
</script>

<template>
  <main>
    <h1>Quản lý sản phẩm</h1>
    <form @submit.prevent="addProduct">
      <div class="mb-3 row">
        <label for="url" class="col-sm-4 col-form-label">Ảnh sản phẩm</label>
        <div class="col-sm-10">
          <input
            type="text"
            class="form-control"
            id="url"
            v-model="newProduct.url"
          />
        </div>
        <label for="name" class="col-sm-4 col-form-label">Tên sản phẩm</label>
        <div class="col-sm-10">
          <input
            type="text"
            class="form-control"
            id="name"
            v-model="newProduct.name"
          />
        </div>
        <label for="price" class="col-sm-4 col-form-label">Giá bán</label>
        <div class="col-sm-10">
          <input
            type="number"
            class="form-control"
            id="price"
            v-model="newProduct.price"
          />
        </div>
        <label for="note" class="col-sm-4 col-form-label">Mô tả</label>
        <div class="col-sm-10">
          <input
            type="text"
            class="form-control"
            id="note"
            v-model="newProduct.note"
          />
        </div>
        <label for="status" class="col-sm-4 col-form-label">Trạng thái</label>
        <div class="col-sm-10">
          <select
            class="form-select"
            name="status"
            id="status"
            v-model="newProduct.status"
          >
            <option selected>Chọn trạng thái</option>
            <option value="KICH_HOAT">Kích doanh</option>
            <option value="KHONG_KICH_HOAT">Không kinh doanh</option>
          </select>
        </div>
      </div>
      <button type="submit" class="btn btn-primary">Submit</button>

      <!-- hiển thị danh sách -->
      <table class="table table-striped table-hover">
        <thead>
          <tr>
            <th scope="col">ID</th>
            <th scope="col">Ảnh</th>
            <th scope="col">Tên sản phẩm</th>
            <th scope="col">Giá bán</th>
            <th scope="col">Mô tả</th>
            <th scope="col">Trạng thái</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="product in listProduct" :key="product.id">
            <th scope="row">{{ product.id }}</th>
            <td><img :src="product.url" class="image-product" /></td>
            <td>{{ product.name }}</td>
            <td>{{ product.price }}</td>
            <td>{{ product.note }}</td>
            <td>{{ product.status }}</td>
          </tr>
        </tbody>
      </table>
    </form>
  </main>
</template>
<style>
  .image-product {
    width: 70px;
    height: 70px;
    border-radius: 10px;
  }
</style>
