package com.example.simpledms.controller;

import com.example.simpledms.model.Dept;
import com.example.simpledms.model.Emp;
import com.example.simpledms.service.DeptService;
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
 * fileName : DeptControllerTest
 * author : ds
 * date : 2022-11-03
 * description : Controller 테스트 (Junit)
 * 요약 :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2022-11-03         ds          최초 생성
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = EmpController.class)
class EmpControllerTest {

    //    가짜 객체 받기
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmpService empService; // 서비스에 가짜객체 넣기


//    잭슨 객체 생성하기 : 객체(모델) to Json, Json to 객체(모델) 자동 변환시켜주는 라이브러리
    ObjectMapper objectMapper = new ObjectMapper();

    @DisplayName("getEmpAll() : 부서 모두 조회 함수 테스트")
    @Test
    void getEmpAll() throws Exception {
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

//        2) given 설정 : 가짜데이터를 결과로 미리 예측
        given(empService.findAll())
                .willReturn(list);
//        3) when 설정  : 테스팅 실행 -> 결과  == 결과로 미리 예측 확인 ( 동일 : OK, 틀리면 : 에러 )
//        1) url 점검
        mockMvc.perform(get("/api/emp"))
//                2) 실행 후 ok 메시지가 나오는가?
                .andExpect(status().isOk())
////                3) content-type이 appiication_json인가?
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
////                4) json 객체의 0번 데이터의 dname이 sales인가?
//                .andExpect(jsonPath("$.[0].dname").value("SALES"))
////                5) json 객체의 1번 데이터의 dname이 accounting인가?
//                .andExpect(jsonPath("$.[1].dname").value("ACCOUNTING"))
                .andDo(print());
    }

    @DisplayName("removeAll : 부서정보 전체 삭제하는 함수 테스트")
    @Test
    void removeAll() throws Exception{
        willDoNothing().given(empService).removeAll();

        mockMvc.perform(delete("/api/emp/all"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @DisplayName("createEmp : 부서 생성하는 함수 테스트")
    @Test
    void createEmp() throws Exception {
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
//                .content("{ \"dno\" : 10, \"dname\" : \"SALES\", \"loc\": \"SEOUL\"}")) 👉 수작업으로 넣는 방식
//                objectMapper.writeValueAsString(객체)) : 객체 to Json 변환 👉 문자열 또 반환
                .content(objectMapper.writeValueAsString(emp)))
                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.dname").value("SALES"))
                .andDo(print()); // print() 테스트 과정을 화면에 출력하는 함수
    }

    @DisplayName("getEmpId : 부서 검색하는 함수 테스트")
    @Test
    void getEmpId() throws Exception {
//        Optional 객체 :
//             1) Optional 객체에 넣기 함수 : Optional.ofNullable(객체);
//             2) Optional 객체에 빼기 함수 : 옵셔널객체.get()
//             3) Optional 객체에 있는지 확인하는 함수 : 옵셔널객체.isPresent()

        //        1) 가짜 데이터 설정
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

//        2) given 설정 : 가짜데이터를 결과로 미리 예측
        given(empService.findById(anyInt()))
                .willReturn(optionalEmp);
//        3) when 설정  : 테스팅 실행 -> 결과  == 결과로 미리 예측 확인 ( 동일 : OK, 틀리면 : 에러 )
//        1) url 점검
        mockMvc.perform(get("/api/emp/10"))
//                2) 실행 후 ok 메시지가 나오는가?
                .andExpect(status().isOk())
////                3) content-type이 appiication_json인가?
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
////                4) json 객체의 0번 데이터의 dname이 sales인가?
//                .andExpect(jsonPath("$.dname").value("SALES"))
                .andDo(print());
    }

    @Test
    @DisplayName("updateEmp() : 부서번호로 수정 함수 테스트 ")
    void updateEmp() throws Exception {
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
//                        .content("{ \"dno\" : 10, \"dname\" : \"SALES2\", \"loc\": \"SEOUL2\"}"))
                        .content(objectMapper.writeValueAsString(emp)))
                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.dname").value("SALES2"))
                .andDo(print()); // print() 테스트 과정을 화면에 출력하는 함수
    }

    @Test
    @DisplayName("deleteEmp() : 부서번호로 삭제 함수 테스트 ")
    void deleteEmp() throws Exception{

        given(empService.removeById(anyInt()))
                .willReturn(true);

        mockMvc.perform(delete("/api/emp/deletion/7369")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
}