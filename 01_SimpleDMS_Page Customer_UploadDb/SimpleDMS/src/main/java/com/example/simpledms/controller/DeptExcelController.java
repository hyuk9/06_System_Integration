package com.example.simpledms.controller;

import com.example.simpledms.dto.ResponseMessageDto;
import com.example.simpledms.helper.ExcelHelper;
import com.example.simpledms.model.Dept;
import com.example.simpledms.model.Tutorial;
import com.example.simpledms.service.DeptExcelService;
import com.example.simpledms.service.TutorialExcelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * packageName : com.example.jpaexam.controller.exam07
 * fileName : Dept07Controller
 * author : ds
 * date : 2022-10-21
 * description : 부서 컨트롤러 쿼리 메소드
 * 요약 :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2022-10-21         ds          최초 생성
 */
@Slf4j
@RestController
@RequestMapping("/api/excel")
public class DeptExcelController {

    @Autowired
    DeptExcelService deptExcelService;

    //    엑셀 업로드 컨트롤러 함수
    @PostMapping("/dept/upload")
    public ResponseEntity<Object> uploadFile(@RequestParam MultipartFile file) {

        String message = ""; // front-end 전송할 메세지

//        엑셀 파일인지 체크
        if(ExcelHelper.hasExcelFormat(file)) {
            try {
                deptExcelService.save(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseMessageDto(message));

            } catch (Exception e) {
                message = "Could not upload the file : " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ResponseMessageDto(message));
            }
        }

        message = "please upload an excel file : ";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResponseMessageDto(message));
    }

    //    전체 조회 함수
    @GetMapping("/dept")
    public ResponseEntity<Object> getAllTutorial() {

        try {
            List<Dept> list = deptExcelService.getAllDept();

            if(list.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);

        } catch(Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //    ✅ 파일 다운로드 함수 : url/api/gallery/1
    @GetMapping("/dept/download")
    public ResponseEntity<Resource> getFile() {

        String filename = "dept.xlsx"; // 엑셀 이 다운로드될 이름 정의

//        ✅ 테이블 데이터 👉 엑셀로 변환 👉 파일로 저장
        InputStreamResource file = new InputStreamResource(deptExcelService.load());

//        ✅ 첨부파일 다운로드 : url Content-Type 규칙 application/vnd.ms-excel (.xls) : 과거버전 엑셀형태
//                                              (참고)           application/vnd.openxmlformats-officedocument.spreadsheetml.sheet (.xlsx) : 최신엑셀
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);

    }

}









