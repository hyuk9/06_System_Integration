package com.example.simpledms.service;

import com.example.simpledms.model.Dept;
import com.example.simpledms.repository.DeptRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * packageName : com.example.simpledms.service
 * fileName : DeptServiceTest
 * author : hyuk
 * date : 2022/11/10
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2022/11/10         hyuk          최초 생성
 */
@ExtendWith(MockitoExtension.class)
class DeptServiceTest {

    @Mock
    private DeptRepository deptRepository;

    @InjectMocks
    private DeptService deptService;

    @DisplayName("findAll() : 서비스 조회 함수")
    @Test
    void findAll() {
        List<Dept> list = new ArrayList<>();

        list.add(Dept.builder()
                .dno(10)
                .dname("SALES")
                .loc("SEOUL")
                .build()
        );
        list.add(Dept.builder()
                .dno(20)
                .dname("ACCOUNTING")
                .loc("BUSAN")
                .build()
        );

        given(deptRepository.findAll()).willReturn(list);

        List<Dept> list2 = deptRepository.findAll();

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

        given(deptRepository.findById(anyInt())).willReturn(optionalDept);

        Optional<Dept> optionalDept2 = deptService.findById(anyInt());

        assertThat(optionalDept2.get().getDname()).isEqualTo("SALES");
    }

    @DisplayName("removeAll() : 서비스 전체 삭제 함수")
    @Test
    void removeAll() {
        deptService.removeAll();

        verify(deptRepository, times(1)).deleteAll();
    }

    @DisplayName("save() : 서비스 추가, 수정 함수")
    @Test
    void save() {
        Dept dept = Dept.builder()
                .dno(10)
                .dname("SALES")
                .loc("SEOUL")
                .build();

        given(deptRepository.save(any()))
                .willReturn(dept);

        Dept dept2 = deptService.save(dept);

        assertThat(dept2.getDname()).isEqualTo(dept.getDname());
    }

    @DisplayName("removeById() : 부서번호로 삭제하는 함수")
    @Test
    void removeById() {
        given(deptRepository.existsById(anyInt())).willReturn(true);

        boolean bSuccessed = deptService.removeById(anyInt());

        verify(deptRepository, times(1)).deleteById(anyInt());
        assertThat(bSuccessed).isEqualTo(true);
    }

    @Test
    void findAllByDnameContaining() {
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

        List<Dept> list2 = deptService.findAllByDnameContaining(any());

        assertThat(list2.get(0).getDname()).isEqualTo("SALES");
    }
}