package com.example.simpledms.controller;

import com.example.simpledms.model.Dept;
import com.example.simpledms.service.DeptService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * packageName : com.example.simpledms.controller
 * fileName : DeptControllerTest
 * author : hyuk
 * date : 2022/11/10
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2022/11/10         hyuk          최초 생성
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = DeptController.class)
class DeptControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeptService deptService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getDeptAll() throws Exception{
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

        given(deptService.findAll())
                .willReturn(list);

        mockMvc.perform(get("/api/dept"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getDeptId() throws Exception{
        Optional<Dept> optionalDept = Optional.ofNullable(Dept.builder()
                .dno(10)
                .dname("SALES")
                .loc("SEOUL")
                .build());

        given(deptService.findById(anyInt()))
                .willReturn(optionalDept);

        mockMvc.perform(get("/api/dept/10"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void removeAll() throws Exception{
        willDoNothing().given(deptService).removeAll();

        mockMvc.perform(delete("/api/dept/all"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void createDept() throws Exception{
        Dept dept = Dept.builder()
                .dno(10)
                .dname("SALES")
                .loc("SEOUL")
                .build();

        given(deptService.save(any()))
                .willReturn(dept);

        mockMvc.perform(post("/api/dept")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dept)))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    void updateDept() throws Exception{
        Dept dept = Dept.builder()
                .dno(10)
                .dname("SALES2")
                .loc("SEOUL2")
                .build();

        given(deptService.save(any()))
                .willReturn(dept);

        mockMvc.perform(put("/api/dept/10")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dept)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void deleteId() throws Exception{
        given(deptService.removeById(anyInt()))
                .willReturn(true);

        mockMvc.perform(delete("/api/dept/deletion/10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
}