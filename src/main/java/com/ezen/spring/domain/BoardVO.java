package com.ezen.spring.domain;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BoardVO {
    private long bno;
    private String title;
    private String writer;
    private String content;
    private String regDate;
}
