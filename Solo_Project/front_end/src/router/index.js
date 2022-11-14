import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
  // 첫페이지 == 전체 목록 페이지
  {
    path: "/",
    alias: "/main",
    name: "main",
    component: () => import("../components/main/MainPage.vue"),
  },
  // 커피 대분류
  { 
    path: "/",
    alias: "/coffeelist",
    name: "coffee-list",
    component: () => import("../components/coffee/CoffeeList.vue"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
}); 

export default router;