package com.example.simpledms.service;

import com.example.simpledms.model.Customer;
import com.example.simpledms.model.Dept;
import com.example.simpledms.repository.CustomerRepository;
import com.example.simpledms.repository.DeptRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * packageName : com.example.jpaexam.service.exam01
 * fileName : DeptService
 * author : ds
 * date : 2022-10-20
 * description : 부서 업무 서비스 클래스
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2022-10-20         ds          최초 생성
 */
@Service
@Slf4j
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository; // JPA CRUD 함수가 있는 인터페이스

    //    ✅ 전체 조회 함수 findAll() 👉 변경 findAll(Pageable pageable)
    public Page<Customer> findAll(Pageable pageable) {
        Page<Customer> page = customerRepository.findAll(pageable);

        return page;
    }

//    ✅ id로 조회하는 함수

    public Optional<Customer> findById(int cid) {
//        findById(기본키)
        Optional<Customer> optionalCustomer = customerRepository.findById(cid);

//        데이터 가공
        Customer customer = optionalCustomer.get();
        customer.setEmail(customer.getEmail() + "_해킹");

//        가공 데이터를 다시 optional 넣기
        optionalCustomer = Optional.ofNullable(customer);
        log.debug(optionalCustomer.get().toString());
        return optionalCustomer;
    }


    //    ✅ 전체 삭제 함수
    public void removeAll() {
        customerRepository.deleteAll();
    }


    //       ✅ 부서 정보 저장 함수
    public Customer save(Customer customer) {
        Customer customer2 = customerRepository.save(customer);
        return customer2;
    }

    //       ✅ 부서 정보 삭제 함수
    public boolean removeById(int cid) {
//        existById(기본키) 있으면 삭제 실행 + true 리턴
        if (customerRepository.existsById(cid) == true) {
            customerRepository.deleteById(cid);
            return true;
        }
//        없으면 그냥 false 리턴
        return false;
    }

    //    ✅ dname like 검색 함수 ( 페이징 처리 추가 )
    public Page<Customer> findAllByEmailContaining(String email, Pageable pageable) {
        Page<Customer> list = customerRepository.findAllByEmailContaining(email, pageable);

        return list;
    }

}








