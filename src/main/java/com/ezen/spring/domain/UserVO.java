package com.ezen.spring.domain;

import lombok.*;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserVO {

    private String email;
    private String pwd;
    private String nickName;
    private String regDate;
    private String lastLogin;
    private List<AuthVO> authList;

}
