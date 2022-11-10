// EmpDataService.js
// 목적 : axios로 CRUD를 하는 공통 함수들 정의


import http from "../http-common";

class EmpDataService{
    // 모든 부서정보 조회 요청 함수
    getAll(){
         // get 방식 통신 요청 👉 @GetMapping("/api/emp")
        return http.get("/emp");
    }

    // 사원번호로 조회 요청 함수
    get(eno){
         // get 방식 통신 요청 👉 @GetMapping("/api/eno/{eno}")
        return http.get(`/emp/${eno}`);
    }

    // 사원정보 생성 요청 함수
    create(data){
        // post 방식 통신 요청 👉 @PostMapping("/api/emp")
        return http.post("/emp", data);
    }

    // 부서정보 수정 요청 함수
    update(eno, data){
        // put 방식 통신 요청 👉 @PutMapping("/api/emp/{eno}")
        return http.put(`/emp/${eno}`, data);
    }

    // 사원정보 삭제 요청 함수
    delete(eno){
        // delete 방식 통신 요청 👉 @DeleteMapping("api/emp/deletion/{eno}")
        return http.delete(`emp/deletion/${eno}`);
    }

    // 사원정보 전체 삭제 요청 함수
    deleteAll(){
        // delete 방식 통신 요청 👉 @DeleteMapping("/emp/all")
        return http.delete("/emp/all");
    }

    // 사원명 검색 요청 함수
    // 쿼리스트링 방식 url : ?변수명1 = 값1 & 변수명2=값2 ... 👉 위에는 전부 PathVariable 방식으로 받지만(파라미터방식) 
    // get 방식 통신 요청 👉 @GetMapping("/emp"), @RequestParam
    findByEname(ename){
        return http.get(`/emp?ename=${ename}`);
    }

}

export default new EmpDataService();