<template>
  <div>
    <h1 style="font-size: 25px; text-align: center; font-weight: bold">
      Thêm người dùng
    </h1>

    <a-form
      :model="formState"
      name="basic"
      :label-col="{ span: 8 }"
      :wrapper-col="{ span: 16 }"
      autocomplete="off"
      @finish="onFinish"
    >
      <a-row style="justify-content: end">
        <a-form-item>
          <a-button type="primary" html-type="submit">Hoàn tất</a-button>
        </a-form-item>
      </a-row>
      <a-row style="justify-content: center">
        <a-col span="8">
          <a-card
            style="
              display: flex;
              align-items: center;
              justify-content: center;
              height: 300px;
            "
          >
            <h1 style="font-size: 18px; text-align: center">Ảnh</h1>
            <img
              src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTTrku1byXzqV3rAtstGGrZ6H-8BuRwYgpKVxXExsNz25ASBSop_yumFYddcTeEArLkBws&usqp=CAU"
              alt=""
              style="width: 200px; height: 200px; border-radius: 50%"
            />
          </a-card>
        </a-col>
        <a-col span="15" style="margin-left: 10px">
          <a-card style="height: 300px">
            <h1 style="font-size: 18px; text-align: center">
              Thông tin người dùng
            </h1>
            <a-row>
              <!-- cột 1 -->
              <a-col span="12">
                <a-form-item
                  label="Avatar"
                  name="avatar"
                  :rules="[
                    { required: true, message: 'Vui lòng nhập họ và tên!' },
                  ]"
                >
                  <a-input v-model:value="formState.avatar" />
                </a-form-item>
                <a-form-item
                  name="dateOfBirth"
                  label="Ngày sinh"
                  :rules="[
                    { required: true, message: 'Vui lòng chọn ngày sinh!' },
                  ]"
                >
                  <a-date-picker
                    v-model:value="formState.dateOfBirth"
                    value-format="DD-MM-YYYY"
                    style="width: 100%"
                  />
                </a-form-item>
                <a-form-item
                  label="Địa chỉ"
                  name="address"
                  :rules="[
                    { required: true, message: 'Vui lòng nhập địa chỉ!' },
                  ]"
                >
                  <a-input v-model:value="formState.address" />
                </a-form-item>
              </a-col>

              <!-- cột 2 -->
              <a-col span="12">
                <a-form-item
                  label="Họ Và Tên"
                  name="name"
                  :rules="[
                    { required: true, message: 'Vui lòng nhập họ và tên!' },
                  ]"
                >
                  <a-input v-model:value="formState.name" />
                </a-form-item>
                <a-form-item
                  label="Giới tính"
                  name="gender"
                  :rules="[
                    { required: true, message: 'Vui lòng chọn giới tính!' },
                  ]"
                >
                  <a-radio-group v-model:value="formState.gender">
                    <a-radio value="NAM">Nam</a-radio>
                    <a-radio value="NU">Nữ</a-radio>
                  </a-radio-group>
                </a-form-item>
                <a-form-item
                  label="Trạng thái"
                  name="status"
                  :rules="[
                    { required: true, message: 'Vui lòng chọn trạng thái!' },
                  ]"
                >
                  <a-select
                    v-model:value="formState.status"
                    placeholder="Vui lòng chọn trạng thái!"
                  >
                    <a-select-option value="DANG_HOAT_DONG"
                      >Đang hoạt động</a-select-option
                    >
                    <a-select-option value="KHONG_HOAT_DONG"
                      >Không hoạt động</a-select-option
                    >
                  </a-select>
                </a-form-item>
              </a-col>
            </a-row>
          </a-card>
        </a-col>
      </a-row>
    </a-form>
  </div>
</template>

<script setup lang="ts">
  import { UserInterface } from "@/types/UserInterface";
  import { UserAPI } from "@/api/UserAPI";
  import { useRouter } from "vue-router";
  import { ref } from "vue";

  const router = useRouter();
  const formState = ref<UserInterface>({});

  const onFinish = (values: UserInterface) => {
    console.log("Success:", values);
    UserAPI.create(formState.value).then(() => {
      router.push("/view-user");
    });
  };
</script>

<style scoped></style>
