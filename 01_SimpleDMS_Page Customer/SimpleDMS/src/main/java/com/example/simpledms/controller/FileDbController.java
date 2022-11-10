package com.example.simpledms.controller;

import com.example.simpledms.dto.ResponseMessageDto;
import com.example.simpledms.dto.filedb.ResponseFileDto;
import com.example.simpledms.model.FileDb;
import com.example.simpledms.service.FileDbService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * packageName : com.example.jpaexam.controller.exam07
 * fileName : FileDb07Controller
 * author : ds
 * date : 2022-10-21
 * description : 부서 컨트롤러 @RestController, ResponseEntity, getFileDbAll()
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2022-10-21         ds          최초 생성
 */
// @RestController : return 값이 json 데이터 형태로 출력됨
@Slf4j
// CORS 보안 : 기본적으로 한사이트에서 포트를 달리 사용 못함
// @CrossOrigin(허용할_사이트주소(vue 사이트주소 : 포트)) : CORS 보안을 허용해주는 어노테이션
//@CrossOrigin(origins = "http://localhost")
@RestController
@RequestMapping("/api")
public class FileDbController {

    @Autowired
    FileDbService fileDbService; // @Autowired : 스프링부트가 가동될때 생성된 객체를 하나 받아오기

//  Model to Dto 자동변환, Dto to Model 자동변환 외부 라이브러리
    ModelMapper modelMapper = new ModelMapper();

//    이미지 업로드 컨트롤러 함수


    @PostMapping("/fileDb/upload")
    public ResponseEntity<Object> fileDbUploadFile (@RequestParam String fileTitle,
                                                    @RequestParam String fileContent,
                                                    @RequestParam MultipartFile fileDb) {
//        디버깅 출력

        String message = ""; // front-end 전송할 메시지
        log.debug("fileTitle : " + fileTitle);
        log.debug("fileContent : " + fileContent);
        log.debug("fileDb : " + fileDb);

        try {
//            db 저장 함수 호출
            fileDbService.store(fileTitle, fileContent, fileDb);

            message = "Upload the file successfully : " + fileDb.getOriginalFilename();

            return new ResponseEntity<>(new ResponseMessageDto(message), HttpStatus.OK);

        } catch (Exception e) {
            log.debug(e.getMessage());
            message = "Could not upload the file successfully : " + fileDb.getOriginalFilename();
            return new ResponseEntity<>(new ResponseMessageDto(message), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //    모든 이미지 정보 가져오기 함수
    @GetMapping("/fileDb")
    public ResponseEntity<Object> getListFiles(@RequestParam(required = false) String fileTitle,
                                               @RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "3") int size
    ) {

        try {
            //            Pageable 객체 정의 ( page, size 값 설정 )
            Pageable pageable = PageRequest.of(page, size);

//            Upload 이미지 정보를 가져오는 함수
            Page<ResponseFileDto> fileDbPage = fileDbService.findAllByFileTitleContaining(fileTitle, pageable)
                                                            .map(dbfile -> {
//                                                                자동적으로 반복문이 실행됨 : .map()
//                                                                1) 다운로드 URL 만들기
                                                                String fileDownloadUri = ServletUriComponentsBuilder
//                                                                        .fromCurrentRequest() // 이미지 파일 경로
                                                                        .fromCurrentContextPath()
                                                                        .path("/api/fileDb/")
                                                                        .path(dbfile.getFid().toString()) // "/api/fileDb/1"
                                                                        .toUriString(); // 마지막에 호출(URL 완성됨)

//                                                                modelMapper 로 fileDb == fileDB 👉 ResponseFileDto 변환
//                                                                modelMapper.map(소스모델, 타겟 DTO.class);
                                                                ResponseFileDto fileDto = modelMapper.map(dbfile, ResponseFileDto.class);


//                                                                DTO에 2개 남은 속성 처리 : setter 이용 가공된 데이터 저장
                                                                fileDto.setFileSize(dbfile.getFileData().length);
                                                                fileDto.setFileUrl(fileDownloadUri);

                                                                return fileDto;
            });

            //            맵 자료구조에 넣어서 전송
            Map<String, Object> response = new HashMap<>();
            response.put("fileDb", fileDbPage.getContent());
            response.put("currentPage", fileDbPage.getNumber());
            response.put("totalItems", fileDbPage.getTotalElements());
            response.put("totalPages", fileDbPage.getTotalPages());

            if (fileDbPage.isEmpty() == false) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //    파일 다운로드 함수 : url/api/fileDb/1
    @GetMapping("/fileDb/{fid}")
    public ResponseEntity<byte[]> getFile(@PathVariable int fid) {
//        id로 조회 함수
        FileDb fileDb = fileDbService.getFile(fid).get();


//        첨부파일 다운로드 : url Content-Type 규칙
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDb.getFileName() + "\"")
                .body(fileDb.getFileData());

    }





    @DeleteMapping("/fileDb/deletion/{fid}")
    public ResponseEntity<Object> deleteId(@PathVariable int fid) {

        try {
             boolean bSuccess = fileDbService.removeById(fid);

            if (bSuccess == true) {
//                데이터 + 성공 메세지 전송
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
//                데이터 없음 메세지 전송(클라이언트)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.debug(e.getMessage());
            // 서버에러 발생 메세지 전송(클라이언트)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}










