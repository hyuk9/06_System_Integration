// GalleryDataService.js
// 목적 : Customer 정보를 axios 로 CRUD 를 하는 공통 함수들 정의
import http from "../http-common";

class GalleryDataService {
    // 함수명   : upload() 함수 ( DB 저장 함수 )
    // 매개변수  title : 제목
    //          content : 내용
    //          gallery : 업로드 대상 이미지
    upload(title, filename, gallery) {
        // json 객체 사용하지 않음 (x)
        // form 태그로 전송하는 방식을 이용함
        // html <form></form> == js FormData 객체로 사용가능
        //                       .append(속성명, 값) 함수 : 데이터를 저장 
        // axios 함수 : .post()
        // 헤더 : "Content-Type" : "multipart/form-data"
        let formData = new FormData();

        formData.append("galleryTitle", title);
        formData.append("galleryFileName", filename);
        formData.append("gallery", gallery);

        return http.post("/gallery/upload", formData, {
            headers: {
                "Content-Type" : "multipart/form-data"
            }
        })
    }

    // 이미지 제목 검색 요청 함수
    getFiles(galleryTitle, page, size) {
        console.log(galleryTitle);
        console.log(page);
        console.log(size);
        return http.get(`/gallery?galleryTitle=${galleryTitle}&page=${page}&size=${size}`)
    }

    // 이미지 삭제 요청 함수
    delete(gid) {
        return http.delete(`/gallery/deletion/${gid}`);
    }
}

export default new GalleryDataService();