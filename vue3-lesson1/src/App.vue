<template>
  <div id="app">
    <AppHearder />
    <AppFilters :active-filter="activeFilter" @set-filter="setFilter" />
    <main class="app-main">
      <AppTodoList
        :todos="filterdTodos"
        @remove-todo="removeTodo"
        @toggle-todo="toggleTodo"
      />
      <AppAddTodo @add-todo="addTodo" />
    </main>
    <AppFooter />
  </div>
</template>

<script lang="ts">
  import AppFilters from "./components/AppFilters.vue";
  import AppHearder from "./components/AppHearder.vue";
  import AppTodoList from "./components/AppTodoList.vue";
  import AppFooter from "./components/AppFooter.vue";
  import AppAddTodo from "./components/AppAddTodo.vue";
  import { Todo } from "./types/todo";
  import { Filter } from "./types/filter";

  // tạo 1 interface để trả về đúng kiểu dữ liệu -> ở đây là trả về 1 list
  interface State {
    todos: Todo[];
    activeFilter: Filter;
  }

  export default {
    components: {
      AppHearder,
      AppFilters,
      AppFooter,
      AppTodoList,
      AppAddTodo,
    },
    // gọi interface để check dữ liệu
    data(): State {
      return {
        todos: [
          {
            id: 0,
            text: "Learn the basics of Vue 0",
            completed: true,
          },
          {
            id: 1,
            text: "Learn the basics of Typescript 1",
            completed: false,
          },
          {
            id: 2,
            text: "Subscribe to the channel 2",
            completed: false,
          },
        ],
        activeFilter: "ALL",
      };
    },
    computed: {
      filterdTodos(): Todo[] {
        switch (this.activeFilter) {
          case "ALL":
            return this.todos;
          case "DONE":
            return this.todos.filter((todo) => todo.completed);
          case "ACTIVE":
            return this.todos.filter((todo) => !todo.completed);
        }
      },
    },
    methods: {
      addTodo(todo: Todo) {
        console.log(todo);
        this.todos.push(todo);
      },
      toggleTodo(id: number) {
        console.log(id);
        const targetTodo = this.todos.find((todo: Todo) => todo.id === id);

        if (targetTodo) {
          targetTodo.completed = !targetTodo.completed;
        }
      },
      removeTodo(id: number) {
        this.todos = this.todos.filter((todo: Todo) => todo.id !== id);
      },
      setFilter(filter: Filter) {
        this.activeFilter = filter;
      },
    },
  };
</script>
