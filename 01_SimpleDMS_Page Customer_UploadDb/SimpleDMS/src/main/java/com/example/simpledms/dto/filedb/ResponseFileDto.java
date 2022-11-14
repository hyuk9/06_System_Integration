package com.example.simpledms.dto.filedb;

import lombok.*;

import javax.persistence.Column;

/**
 * packageName : com.example.simpledms.dto.filedb
 * fileName : ResponseFileDto
 * author : hyuk
 * date : 2022/11/10
 * description : FileDb Dto
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2022/11/10         hyuk          최초 생성
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseFileDto {
    private Integer fid;

    private String fileTitle;

    private String fileContent;

    private String fileName;

    private String fileType;


//    가공된 속성 (모델엔 없음)

    private Integer fileSize; // 이미지 크기
    private String fileUrl; // 이미지 다운로드 URL
}
