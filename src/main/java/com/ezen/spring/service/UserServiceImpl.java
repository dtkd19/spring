package com.ezen.spring.service;

import com.ezen.spring.domain.UserVO;
import com.ezen.spring.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserService{
    private final UserMapper userMapper;

    @Transactional
    @Override
    public int register(UserVO userVO) {
        userMapper.register(userVO);
        return userMapper.insertAuth(userVO.getEmail());
    }
}
