package com.example.simpledms.dto;

import lombok.*;

/**
 * packageName : com.example.simpledms.dto.filedb
 * fileName : ReponseMesseageDto
 * author : hyuk
 * date : 2022/11/10
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2022/11/10         hyuk          최초 생성
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessageDto {
//    클라이언트(Vue) 쪽으로 전달한 메시지
    private String message;
}
