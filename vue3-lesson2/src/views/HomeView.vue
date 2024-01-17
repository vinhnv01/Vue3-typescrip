<script setup lang="ts">
  import { ProductInterface } from "@/types/ProductInterface";
  import { onMounted, ref } from "vue";
  import { RouterLink } from "vue-router";

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

  function loadData() {
    fetch("http://localhost:3000/product")
      .then((response) => response.json())
      .then((data) => {
        // lấy dữ liệu
        listProduct.value = data;
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
      });
  }

  onMounted(() => {
    loadData();
  });

  function addProduct() {
    const add = { ...newProduct.value, id: Date.now().toString() };

    fetch("http://localhost:3000/product", {
      method: "POST",
      body: JSON.stringify(add),
    })
      .then((response) => response.json())
      .then((data) => {
        listProduct.value.push(data);
        newProduct.value = {};
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
      });
  }

  function deleteProduct(id: string) {
    fetch(`http://localhost:3000/product/${id}`, {
      method: "DELETE",
    })
      .then(() => {
        const index = listProduct.value.findIndex((item) => item.id === id);
        if (index !== -1) {
          listProduct.value.splice(index, 1);
        }
      })
      .catch((error) => {
        console.error("Error deleting product:", error);
      });
  }

  function detailProduct(id: string) {
    newProduct.value = { ...listProduct.value.find((item) => item.id === id) };
  }
</script>

<template>
  <div>
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
      <button type="submit" class="btn btn-primary">ADD</button>

      <!-- hiển thị danh sách -->
      <table class="table table-striped">
        <thead>
          <tr>
            <th scope="col">ID</th>
            <th scope="col">Ảnh</th>
            <th scope="col">Tên sản phẩm</th>
            <th scope="col">Giá bán</th>
            <th scope="col">Mô tả</th>
            <th scope="col">Trạng thái</th>
            <th scope="col">Hành động</th>
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
            <td>
              <button
                type="button"
                @click="detailProduct(product.id)"
                class="btn btn-warning"
                style="margin-left: 15px"
              >
                Detail
              </button>
              <RouterLink
                type="button"
                :to="{ name: 'update-product', params: { id: product.id } }"
                class="btn btn-secondary"
                style="margin-left: 15px"
                >Update</RouterLink
              >
              <button
                type="button"
                @click="deleteProduct(product.id)"
                class="btn btn-danger"
                style="margin-left: 15px"
              >
                Delete
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </form>
  </div>
</template>
<style>
  .image-product {
    width: 70px;
    height: 70px;
    border-radius: 10px;
  }
</style>
