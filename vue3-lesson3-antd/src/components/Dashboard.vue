<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <a-layout>
    <a-layout-sider v-model:collapsed="collapsed" :trigger="null" collapsible>
      <div
        class="logo"
        style="justify-content: center; display: flex; text-align: center"
      >
        <img
          src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTnWrrWixCYcGqyNtoANCVyGqbWjj5iK1HeZA&usqp=CAU"
          alt=""
          style="width: 50%; height: 50%; border-radius: 50%; margin: 20px 0px"
        />
      </div>
      <a-menu
        v-model:selectedKeys="state.selectedKeys"
        theme="dark"
        mode="inline"
        :open-keys="state.openKeys"
        :items="items"
        @openChange="onOpenChange"
        @click="handleMenuClick"
      ></a-menu>
    </a-layout-sider>
    <a-layout>
      <a-layout-header
        style="background: #fff; padding: 0px 10px; font-size: 25px"
      >
        <menu-unfold-outlined
          v-if="collapsed"
          class="trigger"
          @click="() => (collapsed = !collapsed)"
        />
        <menu-fold-outlined
          v-else
          class="trigger"
          @click="() => (collapsed = !collapsed)"
        />
      </a-layout-header>
      <a-layout-content
        :style="{
          margin: '24px 16px',
          padding: '24px',
          background: '#fff',
          minHeight: '90vh',
        }"
      >
        <router-view />
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>
<script lang="ts" setup>
  import { VueElement, h, reactive, ref } from "vue";
  import {
    MailOutlined,
    AppstoreOutlined,
    SettingOutlined,
    MenuUnfoldOutlined,
    MenuFoldOutlined,
  } from "@ant-design/icons-vue";
  import { ItemType } from "ant-design-vue";
  import { useRouter } from "vue-router";

  function getItem(
    label: VueElement | string,
    key: string,
    icon?: any,
    children?: ItemType[],
    type?: "group"
  ): ItemType {
    return {
      key,
      icon,
      children,
      label,
      type,
    } as ItemType;
  }

  const items: ItemType[] = reactive([
    getItem("Người dùng", "view-user", () => h(MailOutlined)),
    getItem("Navigation Two", "sub2", () => h(AppstoreOutlined), [
      getItem("Option 5", "5"),
      getItem("Option 6", "6"),
    ]),
    getItem("Cài đặt", "sub4", () => h(SettingOutlined), [
      getItem("Đổi mật khẩu", "view-setting"),
      getItem("Option 10", "10"),
      getItem("Option 11", "11"),
      getItem("Option 12", "12"),
    ]),
  ]);

  const state = reactive({
    rootSubmenuKeys: ["sub1", "sub2", "sub4"],
    openKeys: [""],
    selectedKeys: [],
  });
  const onOpenChange = (openKeys: string[]) => {
    const latestOpenKey = openKeys.find(
      (key) => state.openKeys.indexOf(key) === -1
    );
    if (state.rootSubmenuKeys.indexOf(latestOpenKey) === -1) {
      state.openKeys = openKeys;
    } else {
      state.openKeys = latestOpenKey ? [latestOpenKey] : [];
    }
  };
  const collapsed = ref<boolean>(false);

  // chuyển theo đường link
  const router = useRouter();
  const handleMenuClick = (item: ItemType) => {
    if (item.key) {
      router.push(`/${item.key}`);
    }
  };
</script>

<style>
  #components-layout-demo-custom-trigger .trigger {
    font-size: 18px;
    line-height: 64px;
    padding: 0 24px;
    cursor: pointer;
    transition: color 0.3s;
  }

  #components-layout-demo-custom-trigger .trigger:hover {
    color: #1890ff;
  }

  #components-layout-demo-custom-trigger .logo {
    height: 32px;
    background: rgba(255, 255, 255, 0.3);
    margin: 16px;
  }

  .site-layout .site-layout-background {
    background: #fff;
  }
</style>
