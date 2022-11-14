package com.example.simpledms.service;

import com.example.simpledms.model.Dept;
import com.example.simpledms.repository.DeptRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * packageName : com.example.simpledms.service
 * fileName : DeptServiceTest
 * author : hyuk
 * date : 2022/11/04
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2022/11/04         hyuk          최초 생성
 */
@ExtendWith(MockitoExtension.class)
class DeptServiceTest {

    @Mock
    private DeptRepository deptRepository; // @Mock 붙이면 가짜 repository

    @InjectMocks
    private DeptService deptService; // @InjectMocks 붙이면 가짜 repository를 사용해서 서비스를 쓸 수 있게 만듦


    @DisplayName("findAll() : 서비스 조회 함수 ")
    @Test
    void findAll() {
        List<Dept> list = new ArrayList<>();

        list.add(Dept.builder()
                .dno(10)
                .dname("SALES")
                .loc("SEOUL")
                .build() // .build() 마지막에 무조건 호출해서 객체가 생성됨
        );
        list.add(Dept.builder()
                .dno(20)
                .dname("ACCOUNTING")
                .loc("BUSAN")
                .build() // .build() 마지막에 무조건 호출해서 객체가 생성됨
        );

//        ➡️ 1) given() : 기댓값 설정
        given(deptRepository.findAll()).willReturn(list);

//        ➡️ 2) 테스트 실행
        List<Dept> list2 = deptRepository.findAll();

//        ➡️ 3) 결과 검증(검토)   : assert()(사용법 복잡), assertThat()(사용법 간소함) 등
//        assertThat(테스트 실행값).비교함수(기댓값) : 일치하면 테스트 통과, 불일치하면 실패
        assertThat(list2.get(0).getDname()).isEqualTo(list.get(0).getDname());
        assertThat(list2.get(1).getDname()).isEqualTo(list.get(1).getDname());
    }

    @DisplayName("findById() : 서비스 부서번호로 조회하는 함수")
    @Test
    void findById() {
        Optional<Dept> optionalDept = Optional.ofNullable(Dept.builder()
                .dno(10)
                .dname("SALES")
                .loc("SEOUL")
                .build());

//        given(deptRepository.findById(anyInt())).willReturn(optionalDept);

//        ➡️ 2) 기대값이 서비스 함수가 실행되면서 _해킹이 붙어있음
        Optional<Dept> optionalDept3 = Optional.ofNullable(Dept.builder()
                .dno(10)
                .dname("SALES_해킹asdf")
                .loc("SEOUL")
                .build());

        given(deptRepository.findById(anyInt())).willReturn(optionalDept);


//        2) 테스트 실행
        Optional<Dept> optionalDept2 = deptService.findById(anyInt());

//        3) 결과 검토/검증
        assertThat(optionalDept2.get().getDname()).isEqualTo("SALES");
    }

    @DisplayName("removeAll() : 서비스 전체 삭제 함수")
    @Test
    void removeAll() {
//        1) 테스트 실행
        deptService.removeAll();

//        2) 위의 함수가 몇번 실행되었는지 확인
//        verify(repository 객체ㅡ, times(몇번)) : 함수 실행 횟수 점검
        verify(deptRepository, times(1)).deleteAll();
    }

    @DisplayName("save() : 서비스 추가, 수정 함수")
    @Test
    void save() {
//        1) 기댓값 설정 : deptRepository 객체 이용
        Dept dept = Dept.builder()
                .dno(10)
                .dname("SALES")
                .loc("SEOUL")
                .build();

        given(deptRepository.save(any()))
                .willReturn(dept);
//        2) 테스트 실행
        Dept dept2 = deptService.save(dept);

//        3) 결과 검증 (검토) 실행
        assertThat(dept2.getDname()).isEqualTo(dept.getDname());

    }

    @DisplayName("removeById() : 부서번호로 삭제하는 함수")
    @Test
    void removeById() {
//        1) given() : 가정, 전제, 기본값 설정
        given(deptRepository.existsById(anyInt())).willReturn(true);

//        2) 테스트 실행
        boolean bSuccessed = deptService.removeById(anyInt()); // 서비스 함수 삭제 실행

//        3) 몇번 실행되었는지 검토 / true가 나오는지 검토
        verify(deptRepository, times(1)).deleteById(anyInt());
        assertThat(bSuccessed).isEqualTo(true);
    }

    @Test
    void findAllByDnameContaining() {
//        1) 기댓값 설정

        List<Dept> list = new ArrayList<>();

        list.add(Dept.builder().dno(10)
                .dname("SALES")
                .loc("SEOUL")
                .build()
        );

        list.add(Dept.builder().dno(10)
                .dname("ACCOUNTING")
                .loc("PUSAN")
                .build()
        );

        given(deptRepository.findAllByDnameContaining(any())).willReturn(list);

//        2) 테스트 실행
        List<Dept> list2 = deptService.findAllByDnameContaining(any());

//        3) 테스트 검증
        assertThat(list2.get(0).getDname()).isEqualTo("SALES");

    }
}