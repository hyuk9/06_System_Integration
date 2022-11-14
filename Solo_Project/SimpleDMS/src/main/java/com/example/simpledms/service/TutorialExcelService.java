package com.example.simpledms.service;

import com.example.simpledms.helper.ExcelHelper;
import com.example.simpledms.model.FileDb;
import com.example.simpledms.model.Tutorial;
import com.example.simpledms.repository.FileDbRepository;
import com.example.simpledms.repository.TutorialRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.jpaexam.service.exam01
 * fileName : FileDbService
 * author : ds
 * date : 2022-10-20
 * description : 부서 업무 서비스 클래스
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2022-10-20         ds          최초 생성
 */
@Service
@Slf4j
public class TutorialExcelService {

    @Autowired
    TutorialRepository tutorialRepository;

//    ➡️ 엑셀 👉 테이블로 바꾸는 함수 (upload)
    public void save(MultipartFile file){
        try{
            List<Tutorial> tutorialList = ExcelHelper.excelToTutorials(file.getInputStream());

            tutorialRepository.saveAll(tutorialList);
        } catch (IOException e){
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

//    ➡️ 테이블 👉 엑셀로 바꾸는 함수 (download)
    public ByteArrayInputStream load() {
        List<Tutorial> tutorialList = tutorialRepository.findAll();

        ByteArrayInputStream in = ExcelHelper.tutorialsToExcel(tutorialList);

        return in;
    }


//    ➡️ 테이블 데이터 전체 조회 함수
    public List<Tutorial> getAllTutorial(){
        return tutorialRepository.findAll();
    }


}








