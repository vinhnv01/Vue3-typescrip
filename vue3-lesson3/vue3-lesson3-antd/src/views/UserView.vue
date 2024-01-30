<template>
  <div>
    <a-card style="border-top: 3px solid red">
      <font-awesome-icon icon="filter" style="font-size: 30px" />
      <h1 style="font-size: 23px; font-weight: bold">Bộ lọc</h1>
      <a-form
        :model="formState"
        name="basic"
        :label-col="{ span: 8 }"
        :wrapper-col="{ span: 16 }"
        autocomplete="off"
        @finish="onFinishSeach"
      >
        <a-row style="justify-content: center">
          <!-- cột 1 -->
          <a-col span="12">
            <a-form-item label="Họ và tên" name="username">
              <a-input v-model:value="formState.username" />
            </a-form-item>
          </a-col>

          <!-- cột 2 -->
          <a-col span="12">
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
        <a-row style="justify-content: center">
          <a-button style="background-color: #0099ff; margin: 0px 15px"
            >Tìm kiếm</a-button
          >
          <a-button style="background-color: #deae1f">Làm mới</a-button>
        </a-row>
      </a-form>
    </a-card>
    <a-card style="margin-top: 30px; border-top: 3px solid red">
      <font-awesome-icon icon="rectangle-list" style="font-size: 30px" />
      <a-row>
        <a-col span="19">
          <h1 style="font-size: 23px; font-weight: bold">Danh sách</h1>
        </a-col>
        <a-col span="3">
          <a-button type="primary" @click="addNewUser">
            <font-awesome-icon icon="file-import" /> Upload File
          </a-button>
        </a-col>
        <a-col span="2">
          <a-button type="primary" @click="addNewUser">
            <font-awesome-icon icon="plus" /> Thêm
          </a-button>
        </a-col>
      </a-row>
      <a-table
        :columns="columns"
        :data-source="dataUsers"
        :pagination="{ pageSize: 2 }"
      >
        <!-- custom lại cột theo mong muốn -->
        <template #bodyCell="{ column, record }">
          <template v-if="column.key === 'avatar'">
            <img :src="record.avatar" alt="" class="avatar" />
          </template>
          <template v-if="column.key === 'gender'">
            {{ record.gender === "NAM" ? "Nam" : "Nữ" }}
          </template>
          <template v-if="column.key === 'status'">
            <span>
              <a-tag
                :color="
                  record.status === 'DANG_HOAT_DONG' ? 'green' : 'volcano'
                "
                style="font-size: 15px; width: 130px; text-align: center"
              >
                {{
                  record.status === "DANG_HOAT_DONG"
                    ? "Đang hoạt động"
                    : "Không hoạt động"
                }}
              </a-tag>
            </span>
          </template>
          <template v-else-if="column.key === 'action'">
            <a-button style="background-color: #ffcc33">
              <font-awesome-icon icon="eye" />
            </a-button>
            <a-button
              style="background-color: #0099ff; margin: 0px 15px"
              @click="handleUpdateClick(record.id)"
            >
              <font-awesome-icon icon="pen-to-square" />
            </a-button>
            <a-button
              type="primary"
              danger
              @click="
                () =>
                  showConfirm('Có chắc bạn muốn xóa?', () =>
                    deleteUser(record.id)
                  )
              "
            >
              <font-awesome-icon icon="trash" />
            </a-button>

            <!-- bật modal -->
            <contextHolder />
          </template>
        </template>
      </a-table>
    </a-card>
  </div>
</template>

<script lang="ts" setup>
  import { h, onMounted, ref } from "vue";
  import { UserInterface } from "@/types/UserInterface";
  import { UserAPI } from "@/api/UserAPI";
  import { ExclamationCircleOutlined } from "@ant-design/icons-vue";
  import { Modal } from "ant-design-vue";
  import { useRouter } from "vue-router";

  interface FormStateSearch {
    username: string;
    status: string;
  }
  const formState = ref<FormStateSearch>({
    username: "",
    status: "",
  });
  const onFinishSeach = (values: FormStateSearch) => {
    console.log("Success:", values);
  };

  const router = useRouter();

  const dataUsers = ref<UserInterface[]>([]);
  function loadData() {
    UserAPI.fetchAll().then((data) => {
      dataUsers.value = data.data;
    });
  }

  // modal xác nhận
  const [modal, contextHolder] = Modal.useModal();
  const showConfirm = (text: string, handlClick: any) => {
    modal.confirm({
      title: "Thông báo xác nhận",
      icon: h(ExclamationCircleOutlined),
      content: h("h4", text),
      okText: "Đồng ý",
      cancelText: "Hủy",
      onOk() {
        handlClick();
      },
      class: "test",
    });
  };

  // xóa người dùng
  function deleteUser(id: string) {
    UserAPI.delete(id)
      .then(() => {
        const index = dataUsers.value.findIndex(
          (item: UserInterface) => item.id === id
        );
        if (index !== -1) {
          dataUsers.value.splice(index, 1);
        }
      })
      .catch((error) => {
        console.error("Error deleting product:", error);
      });
  }

  // update người dùng
  function handleUpdateClick(id: string) {
    router.push(`/update-user/${id}`);
  }

  // chuyển trang thêm người dùng
  function addNewUser() {
    router.push(`/add-user`);
  }

  // Khi gọi onMounted và truyền một hàm callback vào đó, hàm này sẽ được thực thi khi component được mount.
  onMounted(() => {
    loadData();
  });

  const columns = [
    {
      title: "Ảnh",
      dataIndex: "avatar",
      key: "avatar",
    },
    {
      title: "Họ và tên",
      dataIndex: "name",
      key: "name",
    },
    {
      title: "Ngày sinh",
      dataIndex: "dateOfBirth",
      key: "dateOfBirth",
    },
    {
      title: "Giới tính",
      dataIndex: "gender",
      key: "gender",
    },
    {
      title: "Địa chỉ",
      dataIndex: "address",
      key: "address",
    },
    {
      title: "Trạng thái",
      key: "status",
      dataIndex: "status",
    },
    {
      title: "Hành dộng",
      key: "action",
    },
  ];
</script>

<style scoped>
  .avatar {
    width: 100px;
    height: 100px;
    border-radius: 10px;
  }
</style>
