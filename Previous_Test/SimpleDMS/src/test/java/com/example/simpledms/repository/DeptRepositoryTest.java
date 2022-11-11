package com.example.simpledms.repository;

import com.example.simpledms.model.Dept;
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
 * fileName : DeptRepositoryTest
 * author : hyuk
 * date : 2022/11/10
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2022/11/10         hyuk          최초 생성
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DeptRepositoryTest {

    @Autowired
    private DeptRepository deptRepository;

    @Test
    void findAllByDnameContaining() {
        Optional<Dept> optionalDept = Optional.ofNullable(Dept.builder()
                .dno(10)
                .dname("SALES")
                .loc("SEOUL")
                .build());

        Dept dept2 = deptRepository.save(optionalDept.get());

        List<Dept> list = deptRepository.findAllByDnameContaining("SALES");
    }

    @Test
    void save() {
        Optional<Dept> optionalDept = Optional.ofNullable(Dept.builder()
                .dno(10)
                .dname("SALES")
                .loc("SEOUL")
                .build());

        Dept dept2 = deptRepository.save(optionalDept.get());

        assertThat(dept2.getDname()).isEqualTo("SALES");
    }

    @Test
    void deleteAll() {
        Optional<Dept> optionalDept = Optional.ofNullable(Dept.builder()
                .dno(10)
                .dname("SALES")
                .loc("SEOUL")
                .build());

        Dept dept2 = deptRepository.save(optionalDept.get());

        deptRepository.deleteAll();

        assertThat(deptRepository.findAll()).isEqualTo(Collections.emptyList());
    }
}