package com.example.simpledms.service;

import com.example.simpledms.model.Faq;
import com.example.simpledms.repository.FaqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.jpaexam.service.exam01
 * fileName : FaqService
 * author : ds
 * date : 2022-10-20
 * description : 부서 업무 서비스 클래스
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2022-10-20         ds          최초 생성
 */
@Service
public class FaqService {

    @Autowired
    FaqRepository faqRepository; // JPA CRUD 함수가 있는 인터페이스

    //    ✅ 전체 조회 함수
    public List<Faq> findAll() {
        List<Faq> list = faqRepository.findAll();

        return list;
    }

//    ✅ id로 조회하는 함수

    public Optional<Faq> findById(int dno) {
//        findById(기본키)
        Optional<Faq> optionalFaq = faqRepository.findById(dno);
        return optionalFaq;
    }


    //    ✅ 전체 삭제 함수
    public void removeAll() {
        faqRepository.deleteAll();
    }


    //       ✅ 부서 정보 저장 함수
    public Faq save(Faq faq) {
        Faq faq2 = faqRepository.save(faq);
        return faq2;
    }

    //       ✅ 부서 정보 삭제 함수
    public boolean removeById(int no) {
//        existById(기본키) 있으면 삭제 실행 + true 리턴
        if (faqRepository.existsById(no) == true) {
            faqRepository.deleteById(no);
            return true;
        }
//        없으면 그냥 false 리턴
        return false;
    }

    //    ✅ dname like 검색 함수
    public List<Faq> findAllByTitleContaining(String title) {
        List<Faq> list = faqRepository.findAllByTitleContaining(title);

        return list;
    }

}








