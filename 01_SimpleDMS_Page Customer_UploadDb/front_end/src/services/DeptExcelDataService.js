// TutorialExcelDataService.js
// 목적 : Customer 정보를 axios 로 CRUD 를 하는 공통 함수들 정의
import http from "../http-common";

class DeptExcelDataService {

    upload(file) {

        let formData = new FormData();

        formData.append("file", file);

        return http.post("/excel/dept/upload", formData, {
            headers: {
                "Content-Type" : "multipart/form-data"
            }
        })
    }

    // 엑셀 업로드된 데이터(db 테이블) 조회 함수
    getFiles() {
        return http.get(`/excel/dept`)
    }

    // 엑셀 다운로드 함수
    download(){
        return http.get("/excel/dept/download", {
            responseType:"blob", // 형식을 blob 받아야 엑셀(pdf등) 다운로드가 가능함
        })
    }
}




export default new DeptExcelDataService();