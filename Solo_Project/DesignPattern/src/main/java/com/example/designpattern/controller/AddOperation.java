package com.example.designpattern.controller;

import com.example.designpattern.model.AbstractOperationTarget;

/**
 * packageName : com.example.designpattern.controller
 * fileName : AddOperation
 * author : hyuk
 * date : 2022/11/01
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2022/11/01         hyuk          최초 생성
 */
public class AddOperation extends AbstractOperationTarget {
    @Override
    public int operate(int first, int second) {
        return first+second;
    }
}
