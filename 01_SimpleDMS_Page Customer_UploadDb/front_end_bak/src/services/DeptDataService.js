// DeptDataService.js
// 목적 : axios로 CRUD를 하는 공통 함수들 정의

import http from "../http-common";

class DeptDataService {
    // 모든 부서정보 조회 요청 함수
    getAll() {
        // get 방식 통신 요청 👉 @GetMapping("/api/dept")
        return http.get("/dept");
    }

    // 부서번호로 조회 요청 함수
    get(dno) {
        // get 방식 통신 요청 👉 @GetMapping("/api/dept/{dno}")
        return http.get(`/dept/${dno}`);
    }

    // 부서정보 생성 요청 함수
    create(data) {
        // post 방식 통신 요청 👉 @PostMapping("/api/dept")
        return http.post("/dept", data);
    }

    // 부서정보 수정 요청 함수
    update(dno, data) {
        // put 방식 통신 요청 👉 @PutMapping("/api/dept/{dno}")
        return http.put(`/dept/${dno}`, data);
    }

    // 부서정보 삭제 요청 함수
    delete(dno) {
        // delete 방식 통신 요청 👉 @DeleteMapping("api/dept/deletion/{dno}")
        return http.delete(`dept/deletion/${dno}`);
    }

    // 부서정보 전체 삭제 요청 함수
    deleteAll() {
        // delete 방식 통신 요청 👉 @DeleteMapping("/dept/all")
        return http.delete("/dept/all");
    }

    // 부서명 검색 요청 함수
    // 쿼리스트링 방식 url : ?변수명1 = 값1 & 변수명2=값2 ... 👉 위에는 전부 PathVariable 방식으로 받지만(파라미터방식) 
    // get 방식 통신 요청 👉 @GetMapping("/dept"), @RequestParam
    findByDname(dname) {
        return http.get(`/dept?dname=${dname}`);
    }

}

export default new DeptDataService();