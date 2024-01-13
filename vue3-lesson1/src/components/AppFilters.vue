<template>
  <aside class="app-filters">
    <section class="toggle-group">
      <button
        class="button"
        v-for="filter in filters"
        :key="filter"
        :class="{ 'button--primary': activeFilter === filter }"
        @click="setFilter(filter)"
      >
        {{ filter }}
      </button>
    </section>
  </aside>
</template>

<script lang="ts">
  import { PropType } from "vue";
  import { Filter } from "@/types/filter";

  interface State {
    filters: Filter[];
  }
  export default {
    props: {
      activeFilter: {
        type: String as PropType<Filter>,
        required: true,
      },
    },
    data(): State {
      return { filters: ["ALL", "ACTIVE", "DONE"] };
    },
    methods: {
      setFilter(filter: Filter) {
        this.$emit("setFilter", filter);
      },
    },
    emits: {
      setFilter: (filter: Filter) => filter,
    },
  };
</script>
