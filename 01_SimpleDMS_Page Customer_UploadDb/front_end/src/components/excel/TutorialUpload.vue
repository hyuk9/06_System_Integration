<template>
  <div>
        <!-- 이미지명(fileTitle) 라벨 박스 시작 -->
    <div class="mb-3 col-md-5">
      <label for="fileTitle" class="form-label">엑셀 선택상자</label>
    </div>
    <!-- 이미지명 입력 박스 끝 -->

        <!-- <!— 이미지 선택상자 시작 —> -->
    <div class="mb-3 col-md-5">
      <label class="btn btn-default p-0">
        <!-- <!— 파일 선택상자 —> -->
        <input type="file" ref="file" @change="selectImage" />
      </label>
    </div>
    <!-- <!— 이미지 선택상자 끝 —> -->

    <!-- <!— upload 버튼 : insert 실행 시작 —> -->
    <div class="mb-3">
      <!-- <!— 서버에 insert 문 호출 —> -->
      <button
        class="btn btn-success btn-sm float-left"
        :disabled="!currentImage"
        @click="upload"
      >
        Upload
      </button>

      <button
        class="btn btn-danger btn-sm float-left ms-3"
        :disabled="!tutorial"
        @click="download"
      >
        Download
      </button>
    </div>
    <!-- <!— upload 버튼 : insert 실행 끝 —> -->
    <!-- <!— upload 끝 —> -->

    <!-- <!— 서버 에러 메세지가 있을경우 아래 출력 시작 —> -->
    <div v-if="message" class="alert alert-secondary" role="alert">
      {{ message }}
    </div>
    <!-- <!— 서버 에러 메세지가 있을경우 아래 출력 끝 —> -->

    <!-- db 테이블 데이터를 화면에 출력 시작 -->
    <table class="table">
      <thead>
        <tr>
          <th scope="col">Id</th>
          <th scope="col">Title</th>
          <th scope="col">Description</th>
          <th scope="col">Published</th>
        </tr>
      </thead>
      <tbody v-for="(data, index) in tutorial" :key="index">
        <tr>
          <td>{{ data.id }}</td>
          <td>{{ data.title }}</td>
          <td>{{ data.description }}</td>
          <td>{{ data.published }}</td>
        </tr>
      </tbody>
    </table>
    <!-- db 테이블 데이터를 화면에 출력 끝 -->
  </div>
  
</template>

<script>
import TutorialExcelDataService from "../../services/TutorialExcelDataService";
export default {
    data(){
        return{
            currentImage: undefined,
            message: "",
            tutorial : [],
        };
    },
    methods: {
        // 조회 함수
        retrieveExcel() {
        TutorialExcelDataService.getFiles()
            // 성공하면 .then() 결과가 전송됨
            .then((response) => {
            this.tutorial = response.data; // 스프링부트에서 전송한 데이터
            // 디버깅 콘솔에 정보 출력
            console.log(response.data);
            })
            // 실패하면 .catch() 에 에러가 전송됨
            .catch((e) => {
            console.log(e);
            });
        },
        // 파일 선택상자에서 선택한 엑셀을 저장하는 함수
        selectImage() {
        // this.$refs : $refs 속성이 있는 컨트롤이 선택됨
        this.currentImage = this.$refs.file.files.item(0);
        this.message = "";
        },
        upload(){
            TutorialExcelDataService.upload(this.currentImage)
            .then((response) => {
                this.message = response.data.message;
                // 재조회
                this.retrieveExcel();
            })
            .catch(e => {
                console.log(e);
                this.message = "Could not upload the file!" + e;
                this.currentImage = undefined; // 엑셀파일 초기화
            })
        },
        // 어려움 !!
        download(){
            TutorialExcelDataService.download() // backend download 엑셀파일 요청
            // backend 성공하면 .then() 결과가 전송됨 (response.data)
            .then((response) => {
                console.log(response);
                // 첨부파일(엑셀, pdf, etc) 다운로드 기능 구현
                // 이미지 다운로드 : <img src="이미지URL">, 또는 <a href="이미지URL">이미지 URL</a>
                // 첨부파일 다운로드 : <a href="엑셀파일URL">...URL</a>
                // js 에서 강제로 a 태그를 생성하고 a 태그 강제 클릭 시킴 a 태그 삭제

                // blob 형태로 다운로드 가능
                // 1) blob 엑셀파일 생성 : blob
                // 2) 생성된 blob 엑셀파일이 있는 경로 (URL) 생성 : blobUrl
                const blob = new Blob([response.data], {
                  type: "application/vnd.ms-excel",
                });
                const blobUrl = URL.createObjectURL(blob);

                // 1) js 에서 강제로 a 태그 생성
                const link = document.createElement("a"); // <a> 생성
                link.href = blobUrl;
                link.download="tutorial.xlsx";
                // 2) js 에서 강제로 a태그 click 실행
                link.click(); // 클릭 이벤트 실행
                // 3) 필요없어진 blobURL 삭제
                URL.revokeObjectURL(blobUrl);

                // 성공 메시지
                this.message = "file download successfully!";
            })
            .catch(e => {
                console.log(e);
                this.message = "Could not download the file!" + e;
                this.currentImage = undefined; // 엑셀파일 초기화
            })
          
        }
    },
    // 화면 뜨자마자 실행 이벤트
    mounted(){
        // 전체 조회 함수 실행
        this.retrieveExcel();
    }
}
</script>

<style>

</style>