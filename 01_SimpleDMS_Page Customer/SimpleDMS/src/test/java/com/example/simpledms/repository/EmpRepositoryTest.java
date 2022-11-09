package com.example.simpledms.repository;

import com.example.simpledms.model.Dept;
import com.example.simpledms.model.Emp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName : com.example.simpledms.repository
 * fileName : EmpRepositoryTest
 * author : hyuk
 * date : 2022/11/04
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2022/11/04         hyuk          최초 생성
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmpRepositoryTest {

    @Autowired
    private EmpRepository empRepository;

    @Test
    void findAllByEnameContaining() {
//        1) 가짜 데이터 설정
        Optional<Emp> empOptional = Optional.ofNullable(Emp.builder()
                .ename("홍길동")
                .job("SALES")
                .manager(8888)
                .hiredate("1982-01-23 00:00:00")
                .salary(1300)
                .build());

//        임시로 가짜 데이터를 insert
        Emp emp2 = empRepository.save(empOptional.get());

//        2) 테스트 실행
        List<Emp> list = empRepository.findAllByEnameContaining("홍길동");

//        3) 테스트 검증
        assertThat(list.get(0).getEname()).isEqualTo("홍길동");
    }

    //    save()
    @Test
    void save(){

        Optional<Emp> optionalEmp = Optional.ofNullable(Emp.builder()
                .eno(7369)
                .ename("SMITH")
                .job("CLERK")
                .manager(7902)
                .hiredate("19801217")
                .salary(800)
                .commission(null)
                .dno(20)
                .build());

//        임시로 가짜 데이터를 insert
        Emp emp2 = empRepository.save(optionalEmp.get());

//        3) 테스트 검증
        assertThat(emp2.getEname()).isEqualTo("SMITH");

    }


    @Test
    void deleteAll(){
        Optional<Emp> optionalEmp = Optional.ofNullable(Emp.builder()
                .eno(7369)
                .ename("SMITH")
                .job("CLERK")
                .manager(7902)
                .hiredate("19801217")
                .salary(800)
                .commission(null)
                .dno(20)
                .build());

        Emp emp2 = empRepository.save(optionalEmp.get());


//        2) 테스트 실행 : 모두 삭제
        empRepository.deleteAll();


//        3) 테스트 검증
        assertThat(empRepository.findAll()).isEqualTo(Collections.emptyList());

    }
}