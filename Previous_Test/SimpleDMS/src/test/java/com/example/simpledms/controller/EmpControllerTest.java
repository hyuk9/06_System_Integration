package com.example.simpledms.controller;

import com.example.simpledms.model.Emp;
import com.example.simpledms.service.EmpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * packageName : com.example.simpledms.controller
 * fileName : EmpControllerTest
 * author : hyuk
 * date : 2022/11/10
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2022/11/10         hyuk          최초 생성
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = EmpController.class)
class EmpControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmpService empService;

    ObjectMapper objectMapper = new ObjectMapper();

    @DisplayName("getEmpAll() : 모든 부서 조회 함수 테스트")
    @Test
    void getEmpAll() throws Exception{
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

        given(empService.findAll())
                .willReturn(list);

        mockMvc.perform(get("/api/emp"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @DisplayName("getEmpID() : 부서 검색하는 함수 테스트")
    @Test
    void getEmpID() throws Exception{
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

        given(empService.findById(anyInt()))
                .willReturn(optionalEmp);

        mockMvc.perform(get("/api/emp/10"))
                .andExpect((status().isOk()))
                .andDo(print());
    }

    @Test
    void removeAll() throws Exception{
        willDoNothing().given(empService).removeAll();

        mockMvc.perform(delete("/api/emp/all"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void createEmp() throws Exception{
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

        mockMvc.perform(post("/api/emp")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(emp)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void updateEmp() throws Exception{
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

        mockMvc.perform(put("/api/emp/7369")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(emp)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void deleteId() throws Exception{
        given(empService.removeById(anyInt()))
                .willReturn(true);

        mockMvc.perform(delete("/api/emp/deletion/7369")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
}