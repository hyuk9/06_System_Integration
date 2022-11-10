package com.example.simpledms.controller;

import com.example.simpledms.dto.ResponseMessageDto;
import com.example.simpledms.dto.gallery.ResponseFileDto;
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

    // ModelMAPPER 사용하기 Model to DTO <-> DTO to Model 자동변환 외부 라이브러리
    ModelMapper modelMapper = new ModelMapper();

    @DeleteMapping("/gallery/deletion/{gid}")
    public ResponseEntity<Object> deleteGallery(@PathVariable int gid) {

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


    // 이미지 업로드 컨트롤러 함수
    @PostMapping("/gallery/upload")
    public ResponseEntity<Object> galleryUploadFile(@RequestParam("galleryTitle") String galleryTitle,
                                                    @RequestParam("galleryFileName") String galleryFileName,
                                                    @RequestParam("gallery") MultipartFile gallery) {
        String message = ""; // 프론트 쪽으로 전송할 메세지

        // 디버깅 출력
        log.debug("galleryTitle : " + galleryTitle);
        log.debug("galleryFileName : " + galleryFileName);
        log.debug("gallery : " + gallery);

        try {

            galleryService.store(galleryTitle, galleryFileName, gallery);

            message = "Upload the File Successfully : " + gallery.getOriginalFilename();

            return new ResponseEntity<>(new ResponseMessageDto(message), HttpStatus.OK);

        } catch (Exception e) {
            message = "Could not upload the file : " + gallery.getOriginalFilename();
            log.debug(e.getMessage());
            return new ResponseEntity<>(new ResponseMessageDto(message), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // 파일 다운로드 함수
    @GetMapping("/gallery/{gid}")
    public ResponseEntity<byte[]> getFile(@PathVariable int gid) {


        // gid로 조회
        Gallery gallery= galleryService.getFile(gid).get();

        // 첨부파일 다운로드 : url + Content-Type 규칙
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + gallery.getGalleryFileName() + "\"")
                .body(gallery.getGalleryData());
    }


    // 이미지 정보를 모두 가져오는 함수
    @GetMapping("/gallery")
    public ResponseEntity<Object> getListGallery(@RequestParam(required = false) String galleryTitle,
                                                 @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "3") int size) {

        try {
//          Pageable객체 정의
            Pageable pageable = PageRequest.of(page, size);

//          Upload 이미지 정보를 가져오는 함수
            // DTO를 쓰는데, MAP을 같이 써서 Map 내부적으로 반복문 작동
            Page<ResponseFileDto> galleryPage =
                    galleryService.findAllByGalleryTitleContaining(galleryTitle, pageable)
                            .map(gallery -> {
                                // 자동적으로 반복문이 실행됨 : .map()의 특징
                                // 1) 다운로드 URL을 만들기
                                // ServletUriComponentsBuilder -> URL 만들어주는 클래스
                                String fileDownloadUri = ServletUriComponentsBuilder
                                        .fromCurrentContextPath() // 이미지 파일 경로
                                        .path("/api/gallery/")
                                        .path(gallery.getGid().toString()) // "/api/fileDb/1"
                                        .toUriString();

                                // modelMapper 로 fileDb -> ResponseFileDto로 변환
                                // modelMapper.map(소스모델, 타겟DTO.class)
                                ResponseFileDto galleryDto = modelMapper.map(gallery, ResponseFileDto.class);

                                // Dto에 2개 남은 속성 처리 : setter 이용 가공된 데이터 저장
                                galleryDto.setFileSize(gallery.getGalleryData().length);
                                galleryDto.setFileUrl(fileDownloadUri);

                                return galleryDto;


                            });


            // MAP에 넣어서 Client에 전송.
            Map<String, Object> response = new HashMap<>();
            response.put("gallery", galleryPage.getContent());
            response.put("currentPage", galleryPage.getNumber());
            response.put("totalItems", galleryPage.getTotalElements());
            response.put("totalPages", galleryPage.getTotalPages());

            if (galleryPage.isEmpty() == false) {
//                데이터 + 성공 메세지 전송
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
//                데이터 없음 메세지 전송(클라이언트)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch(Exception e){
            log.debug(e.getMessage());
            // 서버에러 발생 메세지 전송(클라이언트)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}










