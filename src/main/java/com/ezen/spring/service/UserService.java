package com.ezen.spring.service;

import com.ezen.spring.domain.UserVO;

import java.util.List;

public interface UserService {
    int register(UserVO userVO);

    List<UserVO> getList();

    int modifyNoPwd(UserVO userVO);

    int modify(UserVO userVO);

    int delete(String email);

    int lastLogin(String email);
}
