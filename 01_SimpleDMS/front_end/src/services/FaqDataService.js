// EmpDataService.js
// 목적 : axios로 CRUD를 하는 공통 함수들 정의


import http from "../http-common";

class FaqDataService{
    // 모든 부서정보 조회 요청 함수
    getAll(){
         // get 방식 통신 요청 👉 @GetMapping("/api/faq")
        return http.get("/faq");
    }

    // 사원번호로 조회 요청 함수
    get(no){
         // get 방식 통신 요청 👉 @GetMapping("/api/faq/{no}")
        return http.get(`/faq/${no}`);
    }

    // 사원정보 생성 요청 함수
    create(data){
        // post 방식 통신 요청 👉 @PostMapping("/api/faq")
        return http.post("/faq", data);
    }

    // 부서정보 수정 요청 함수
    update(no, data){
        // put 방식 통신 요청 👉 @PutMapping("/api/faq/{no}")
        return http.put(`/faq/${no}`, data);
    }

    // 사원정보 삭제 요청 함수
    delete(no){
        // delete 방식 통신 요청 👉 @DeleteMapping("api/faq/deletion/{no}")
        return http.delete(`faq/deletion/${no}`);
    }

    // 사원정보 전체 삭제 요청 함수
    deleteAll(){
        // delete 방식 통신 요청 👉 @DeleteMapping("/faq/all")
        return http.delete("/faq/all");
    }

    // 사원명 검색 요청 함수
    // 쿼리스트링 방식 url : ?변수명1 = 값1 & 변수명2=값2 ... 👉 위에는 전부 PathVariable 방식으로 받지만(파라미터방식) 
    // get 방식 통신 요청 👉 @GetMapping("/emp"), @RequestParam
    findByEname(title){
        return http.get(`/faq?title=${title}`);
    }

}

export default new FaqDataService();