package com.example.simpledms.service;

import com.example.simpledms.helper.DeptHelper;
import com.example.simpledms.helper.ExcelHelper;
import com.example.simpledms.model.Dept;
import com.example.simpledms.repository.DeptRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

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
public class DeptExcelService {

    @Autowired
    DeptRepository deptRepository;

//    ➡️ 엑셀 👉 테이블로 바꾸는 함수 (upload)
    public void save(MultipartFile file){
        try{
            List<Dept> list = DeptHelper.excelToDept(file.getInputStream());

            deptRepository.saveAll(list);
        } catch (IOException e){
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

//    ➡️ 테이블 👉 엑셀로 바꾸는 함수 (download)
    public ByteArrayInputStream load() {
        List<Dept> list = deptRepository.findAll();

        ByteArrayInputStream in = DeptHelper.DeptToExcel(list);

        return in;
    }


//    ➡️ 테이블 데이터 전체 조회 함수
    public List<Dept> getAllDept(){
        return deptRepository.findAll();
    }


}








