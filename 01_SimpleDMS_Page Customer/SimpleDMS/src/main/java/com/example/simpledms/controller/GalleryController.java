package com.example.simpledms.controller;

import com.example.simpledms.dto.ResponseMessageDto;
import com.example.simpledms.dto.filedb.ResponseFileDto;
import com.example.simpledms.dto.gallery.ResponseGalleryDto;
import com.example.simpledms.model.Gallery;
import com.example.simpledms.service.GalleryService;
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

/**
 * packageName : com.example.jpaexam.controller.exam07
 * fileName : Gallery07Controller
 * author : ds
 * date : 2022-10-21
 * description : 부서 컨트롤러 @RestController, ResponseEntity, getGalleryAll()
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
public class GalleryController {

    @Autowired
    GalleryService galleryService; // @Autowired : 스프링부트가 가동될때 생성된 객체를 하나 받아오기

//  Model to Dto 자동변환, Dto to Model 자동변환 외부 라이브러리
    ModelMapper modelMapper = new ModelMapper();

//    이미지 업로드 컨트롤러 함수


    @PostMapping("/gallery/upload")
    public ResponseEntity<Object> galleryUploadFile (@RequestParam String galleryTitle,
                                                    @RequestParam MultipartFile gallery) {
//        디버깅 출력

        String message = ""; // front-end 전송할 메시지
        log.debug("galleryTitle : " + galleryTitle);
        log.debug("gallery : " + gallery);

        try {
//            db 저장 함수 호출
            galleryService.store(galleryTitle, gallery);

            message = "Upload the file successfully : " + gallery.getOriginalFilename();

            return new ResponseEntity<>(new ResponseMessageDto(message), HttpStatus.OK);

        } catch (Exception e) {
            log.debug(e.getMessage());
            message = "Could not upload the file successfully : " + gallery.getOriginalFilename();
            return new ResponseEntity<>(new ResponseMessageDto(message), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //    모든 이미지 정보 가져오기 함수
    @GetMapping("/gallery")
    public ResponseEntity<Object> getListFiles(@RequestParam(required = false) String galleryTitle,
                                               @RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "3") int size
    ) {

        try {
            //            Pageable 객체 정의 ( page, size 값 설정 )
            Pageable pageable = PageRequest.of(page, size);

//            Upload 이미지 정보를 가져오는 함수
            Page<ResponseGalleryDto> galleryPage = galleryService.findAllByGalleryTitleContaining(galleryTitle, pageable)
                                                            .map(gallery -> {
//                                                                자동적으로 반복문이 실행됨 : .map()
//                                                                1) 다운로드 URL 만들기
                                                                String fileDownloadUri = ServletUriComponentsBuilder
//                                                                        .fromCurrentRequest() // 이미지 파일 경로
                                                                        .fromCurrentContextPath()
                                                                        .path("/api/gallery/")
                                                                        .path(gallery.getGid().toString()) // "/api/gallery/1"
                                                                        .toUriString(); // 마지막에 호출(URL 완성됨)

//                                                                modelMapper 로 gallery == fileDB 👉 ResponseFileDto 변환
//                                                                modelMapper.map(소스모델, 타겟 DTO.class);
                                                                ResponseGalleryDto galleryDto = modelMapper.map(gallery, ResponseGalleryDto.class);


//                                                                DTO에 2개 남은 속성 처리 : setter 이용 가공된 데이터 저장
                                                                galleryDto.setFileSize(gallery.getGalleryData().length);
                                                                galleryDto.setFileUrl(fileDownloadUri);

                                                                return galleryDto;
            });

            //            맵 자료구조에 넣어서 전송
            Map<String, Object> response = new HashMap<>();
            response.put("gallery", galleryPage.getContent());
            response.put("currentPage", galleryPage.getNumber());
            response.put("totalItems", galleryPage.getTotalElements());
            response.put("totalPages", galleryPage.getTotalPages());

            if (galleryPage.isEmpty() == false) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //    파일 다운로드 함수 : url/api/gallery/1
    @GetMapping("/gallery/{gid}")
    public ResponseEntity<byte[]> getFile(@PathVariable int gid) {
//        id로 조회 함수
        Gallery gallery = galleryService.getFile(gid).get();


//        첨부파일 다운로드 : url Content-Type 규칙
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + gallery.getGalleryFileName() + "\"")
                .body(gallery.getGalleryData());

    }





    @DeleteMapping("/gallery/deletion/{gid}")
    public ResponseEntity<Object> deleteId(@PathVariable int gid) {
        try {
             boolean bSuccess = galleryService.removeById(gid);

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










