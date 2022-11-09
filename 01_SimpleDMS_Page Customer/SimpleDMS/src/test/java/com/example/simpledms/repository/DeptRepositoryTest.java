package com.example.simpledms.repository;

import com.example.simpledms.model.Dept;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
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
 * fileName : DeptRepositoryTest
 * author : hyuk
 * date : 2022/11/04
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2022/11/04         hyuk          최초 생성
 */
//  @ExtendWith(SpringExtension.class) : 테스트 시 스프링 함수 또는 기능을 제공해주는 어노테이션
@ExtendWith(SpringExtension.class)
//  @DataJpaTest : repository를 테스트 하기 위한 어노테이션, 자동 롤백
//                 DB가 필요, 테스트 후 자동 롤백시켜줌 (데이터를 insert/update/delete 했으면 명령 취소)
@DataJpaTest
//  H데이터베이스 ; 간이 DB ( 스프링부트 있음 )
//  아래 옵션은 외장 DB(오라클DB)로 테스트 진행한다는 옵션
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DeptRepositoryTest {

    @Autowired
    private DeptRepository deptRepository;

    @Test
    void findAllByDnameContaining() {

//        1) 가짜 데이터 설정
        Optional<Dept> optionalDept = Optional.ofNullable(Dept.builder()
                .dno(10)
                .dname("SALES")
                .loc("SEOUL")
                .build());

//        임시로 가짜 데이터를 insert
        Dept dept2 = deptRepository.save(optionalDept.get());

//        2) 테스트 실행
        List<Dept> list = deptRepository.findAllByDnameContaining("SALES");

//        3) 테스트 검증
        assertThat(list.get(0).getDname()).isEqualTo("SALES");

    }

//    save()
    @Test
    void save(){
        //        1) 가짜 데이터 설정
        Optional<Dept> optionalDept = Optional.ofNullable(Dept.builder()
                .dno(10)
                .dname("SALES")
                .loc("SEOUL")
                .build());

//        임시로 가짜 데이터를 insert
        Dept dept2 = deptRepository.save(optionalDept.get());

//        3) 테스트 검증
        assertThat(dept2.getDname()).isEqualTo("SALES");

    }


    @Test
    void deleteAll(){
        //        1) 가짜 데이터 설정
        Optional<Dept> optionalDept = Optional.ofNullable(Dept.builder()
                .dno(10)
                .dname("SALES")
                .loc("SEOUL")
                .build());

        Dept dept2 = deptRepository.save(optionalDept.get());

//        2) 테스트 실행 : 모두 삭제
        deptRepository.deleteAll();


//        3) 테스트 검증
        assertThat(deptRepository.findAll()).isEqualTo(Collections.emptyList());


    }
}