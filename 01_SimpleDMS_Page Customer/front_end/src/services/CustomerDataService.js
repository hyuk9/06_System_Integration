// FaqDataService.js
// 목적 : Faq 정보를 axios 로 CRUD 를 하는 공통 함수들 정의
import http from "../http-common";

class CustomerDataService {
    // 모든 부서정보 조회 요청 함수
    getAll(email, page, size) {
        // get 방식 통신 요청 -> @GetMapping("/api/faq")
        return http.get(`/customer?email=${email}&page=${page}&size=${size}`); 
    }

    // 부서번호로 조회 요청 함수
    // get 방식 통신 요청 -> @GetMapping("/api/faq/{no}"), @PathVariable
    get(cid) {
        return http.get(`/customer/${cid}`)
    }

    // 부서정보 생성(insert) 요청 함수
    // post 방식 통신 요청 -> @PostMapping("/api/faq"), @RequestBody
    create(data) {
        console.log(data);
        return http.post("/customer", data);
    }

    // 부서정보 수정(update) 요청 함수
    // put 방식 통신 요청 -> @PutMapping("/api/faq/{no}"), @RequestBody
    update(cid, data) {
        return http.put(`/customer/${cid}`, data);
    }

    // 부서정보 삭제(delete) 요청 함수
    // delete 방식 통신 요청 -> @DeleteMapping("/api/faq/deletion/{no}")
    //                        , @PathVariable  
    delete(cid) {
        return http.delete(`/customer/deletion/${cid}`);
    }

    // 부서정보 전체 삭제 요청 함수
    // delete 방식 통신 요청 -> @DeleteMapping("/api/faq/all")
    deleteAll() {
        return http.delete("/customer/all")
    }

    // // 부서명 검색 요청 함수
    // // 쿼리스트링 방식 url : ?변수명=값&변수명2=값2....
    // // get 방식 통신 요청 -> @GetMapping("/api/faq") , @RequestParam
    // findByTitle(title) {
    //     return http.get(`/faq?title=${title}`);
    // }

}

export default new CustomerDataService();