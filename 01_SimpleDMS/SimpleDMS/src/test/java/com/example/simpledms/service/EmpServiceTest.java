package com.example.simpledms.service;

import com.example.simpledms.model.Dept;
import com.example.simpledms.model.Emp;
import com.example.simpledms.repository.EmpRepository;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * packageName : com.example.simpledms.service
 * fileName : EmpServiceTest
 * author : hyuk
 * date : 2022/11/04
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2022/11/04         hyuk          최초 생성
 */
@ExtendWith(MockitoExtension.class)
class EmpServiceTest {

    @Mock
    private EmpRepository empRepository;

    @InjectMocks
    private EmpService empService;

    @DisplayName("findAll() : 서비스 조회 함수 ")
    @Test
    void findAll() {
        List<Emp> list = new ArrayList<>();

        list.add(Emp.builder()
                .eno(7369)
                .ename("SMITH")
                .job("CLERK")
                .manager(7902)
                .hiredate("19801217")
                .salary(800)
                .commission(null)
                .dno(20)
                .build()
        );

        list.add(Emp.builder()
                .eno(7499)
                .ename("ALLEN")
                .job("SALESMAN")
                .manager(7698)
                .hiredate("19810220")
                .salary(1600)
                .commission(300)
                .dno(30)
                .build()
        );

        given(empRepository.findAll()).willReturn(list);

        List<Emp> list2 = empRepository.findAll();

        assertThat(list2.get(0).getEname()).isEqualTo("SMITH");
        assertThat(list2.get(1).getEname()).isEqualTo("ALLEN");
    }

    @DisplayName("findById() : 서비스 부서번호로 조회하는 함수")
    @Test
    void findById() {
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

//        given(deptRepository.findById(anyInt())).willReturn(optionalDept);

//        ➡️ 2) 기대값이 서비스 함수가 실행되면서 _해킹이 붙어있음
        Optional<Emp> optionalEmp3 = Optional.ofNullable(Emp.builder()
                .eno(7369)
                .ename("SMITH")
                .job("CLERK")
                .manager(7902)
                .hiredate("19801217")
                .salary(800)
                .commission(null)
                .dno(20)
                .build());

        given(empRepository.findById(anyInt())).willReturn(optionalEmp);


//        2) 테스트 실행
        Optional<Emp> optionalDept2 = empService.findById(anyInt());

//        3) 결과 검토/검증
        assertThat(optionalDept2.get().getEname()).isEqualTo("SMITH");
    }


    @DisplayName("removeAll() : 서비스 전체 삭제 함수")
    @Test
    void removeAll() {
        empService.removeAll();

        verify(empRepository, times(1)).deleteAll();;
    }

    @DisplayName("save() : 서비스 추가, 수정 함수")
    @Test
    void save() {
        Emp emp = Emp.builder()
                .eno(7369)
                .ename("SMITH")
                .job("CLERK")
                .manager(7902)
                .hiredate("19801217")
                .salary(800)
                .commission(null)
                .dno(20)
                .build();

        given(empService.save(any()))
                .willReturn(emp);

        Emp emp2 = empService.save(emp);

        assertThat(emp2.getEname()).isEqualTo(emp.getEname());
    }

    @DisplayName("removeById() : 부서번호로 삭제하는 함수")
    @Test
    void removeById() {
        given(empRepository.existsById(anyInt())).willReturn(true);

        boolean bSuccessed = empService.removeById(anyInt()); // 서비스 함수 삭제 실행

        verify(empRepository, times(1)).deleteById(anyInt());
        assertThat(bSuccessed).isEqualTo(true);
    }

    @Test
    void findAllByEnameContaining() {
        List<Emp> list = new ArrayList<>();

        list.add(Emp.builder()
                .eno(7369)
                .ename("SMITH")
                .job("CLERK")
                .manager(7902)
                .hiredate("19801217")
                .salary(800)
                .commission(null)
                .dno(20)
                .build()
        );

        list.add(Emp.builder()
                .eno(7499)
                .ename("ALLEN")
                .job("SALESMAN")
                .manager(7698)
                .hiredate("19810220")
                .salary(1600)
                .commission(300)
                .dno(30)
                .build()
        );

        given(empRepository.findAllByEnameContaining(any())).willReturn(list);

//        2) 테스트 실행
        List<Emp> list2 = empService.findAllByEnameContaining(any());

//        3) 테스트 검증
        assertThat(list2.get(0).getEname()).isEqualTo("SMITH");
        assertThat(list2.get(1).getEname()).isEqualTo("ALLEN");


    }
}