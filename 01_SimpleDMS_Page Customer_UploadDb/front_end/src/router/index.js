import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
  // 첫페이지 == 전체 목록 페이지
  {
    path: "/",
    alias: "/dept",
    name: "dept",
    component: () => import("../components/dept/DeptList.vue"),
  },
  // 부서 추가 페이지
  {
    path: "/add/dept",
    name: "add-dept",
    component: () => import("../components/dept/AddDept.vue"),
  },
  // 부서 상세 페이지
  {
    path: "/dept/:dno",
    name: "dept-detail",
    component: () => import("../components/dept/DeptDetail.vue"),
  },

  // 전체 사원 목록 페이지
  {
    path: "/emp",
    name: "emp",
    component: () => import("../components/emp/EmpList.vue"),
  },
  // 사원 추가 페이지
  {
    path: "/add/emp",
    name: "add-emp",
    component: () => import("../components/emp/AddEmp.vue"),
  },
  // 사원 상세 페이지
  {
    path: "/emp/:eno",
    name: "emp-detail",
    component: () => import("../components/emp/EmpDetail.vue"),
  },
  // 전체 Faq 목록 페이지
  {
    path: "/faq",
    name: "faq",
    component: () => import("../components/faq/FaqList.vue"),
  },
  // Faq 추가 페이지
  {
    path: "/add/faq",
    name: "add-faq",
    component: () => import("../components/faq/AddFaq.vue"),
  },
  // Faq 상세 페이지
  {
    path: "/faq/:no",
    name: "faq-detail",
    component: () => import("../components/faq/FaqDetail.vue"),
  },
  // 전체 Customer 목록 페이지
  {
    path: "/customer",
    name: "customer",
    component: () => import("../components/customer/CustomerList.vue"),
  },
  // Customer 추가 페이지
  {
    path: "/add/customer",
    name: "add-customer",
    component: () => import("../components/customer/AddCustomer.vue"),
  },
  // Customer 상세 페이지
  {
    path: "/customer/:cid",
    name: "customer-detail",
    component: () => import("../components/customer/CustomerDetail.vue"),
  },
  // 전체 Qna 목록 페이지
  {
    path: "/qna",
    name: "qna",
    component: () => import("../components/qna/QnaList.vue"),
  },
  // Qna 추가 페이지
  {
    path: "/add/qna",
    name: "add-qna",
    component: () => import("../components/qna/AddQna.vue"),
  },
  // Qna 상세 페이지
  {
    path: "/qna/:qno",
    name: "qna-detail",
    component: () => import("../components/qna/QnaDetail.vue"),
  },
  // FileDb Upload 페이지
  {
    path: "/fileDb",
    name: "fileDb",
    // vue 경로 표기법 : @ == /src
    // component: () => import('@/components/fileDb/FileDbUpload.vue')
    component: () => import("../components/fileDb/FileDbUpload.vue"),
  },
  // Gallery Upload 페이지
  {
    path: "/gallery",
    name: "gallery",
    // vue 경로 표기법 : @ == /src
    // component: () => import('@/components/fileDb/FileDbUpload.vue')
    component: () => import("../components/gallery/GalleryUpload.vue"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;