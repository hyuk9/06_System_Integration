// GalleryDataService.js
// 목적 : galleryDb 정보를 axios 로 CRUD 를 하는 공통 함수들 정의
import http from "../http-common";

class GalleryDataService {
  // upload() 함수 -> DB 저장 함수(이미지를 DB에 저장)
  // title : 제목
  // content : 내용
  // fildDb : 업로드 대상 이미지

  upload(galleryTitle, galleryFileName, gallery) {
    // json 객체 사용 X
    // form을 이용 전송
    // html <form></form> -> JS formData 객체로 사용 가능
    //                          .append(속성명, 값)함수 : 데이터 저장
    // axios 함수 : post()
    // 업로드도 일종의 post 개념으로 봐야함.
    // 헤더 "Content-Type" : "multipart/form-data"

    let formData = new FormData();
    formData.append("galleryTitle", galleryTitle);
    formData.append("galleryFileName", galleryFileName);
    formData.append("gallery", gallery);

    return http.post("/gallery/upload", formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });
  }

  // 이미지 검색 함수(이미지 제목 검색 - filetitle, page, size)
  getFiles(galleryTitle, page, size) {
    // 함수의 매개변수가 잘 넘어오는지 콘솔 먼저 확인.
    console.log(galleryTitle);
    console.log(page);
    console.log(size);

    return http.get(
      `/gallery?galleryTitle=${galleryTitle}&page=${page}&size=${size}`
    );
  }

  // 이미지 삭제 함수
  deleteFiles(gid) {
    return http.delete(`/gallery/deletion/${gid}`);
  }
}
export default new GalleryDataService();