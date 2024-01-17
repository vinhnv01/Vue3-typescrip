<script setup lang="ts">
  import { ProductAPI } from "@/api/ProductAPI";
  import { ProductInterface } from "@/types/ProductInterface";
  import { onMounted, ref } from "vue";
  import { useRoute, useRouter } from "vue-router";

  const router = useRouter();
  const route = useRoute();
  const productId = ref<string>("");

  // https://learnvue.co/articles/vue-lifecycle-hooks-guide
  onMounted(() => {
    // Truy cập ID sản phẩm từ Route.params
    productId.value = route.params.id;

    detailProduct(productId.value);
  });
  console.log(productId);

  const newProduct = ref<ProductInterface>({});

  function detailProduct(id: string) {
    ProductAPI.getOne(id)
      .then((data) => {
        newProduct.value = data.data;
      })
      .catch((error) => {
        console.error("Error deleting product:", error);
      });
  }

  function updateProduct() {
    const update = { ...newProduct.value, id: productId.value };
    ProductAPI.update(update)
      .then(() => {
        router.push({ name: "home" });
      })
      .catch((error) => {
        console.error("Error deleting product:", error);
      });
  }
</script>
<template>
  <div>
    <h1>Cập nhập sản phẩm</h1>
    <form @submit.prevent="updateProduct">
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
            type="tetx"
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
    </form>
  </div>
</template>
