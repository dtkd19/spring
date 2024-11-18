package com.ezen.spring.domain;


import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CommentVO {
    private long cno;
    private long bno;
    private String writer;
    private String content;
    private String regDate;
}
