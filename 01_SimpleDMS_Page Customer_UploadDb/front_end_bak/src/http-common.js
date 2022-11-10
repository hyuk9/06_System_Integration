// http-common.js
// 목적 springboot와 axios 통신을 위한 기본 설정
import axios from "axios";

export default axios.create({
    baseURL: "http://localhost:8000/api",
    header: {
        "Content-type": "application/json",
    }
})