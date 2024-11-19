package com.ezen.spring.service;

import com.ezen.spring.domain.UserVO;
import com.ezen.spring.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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

    @Transactional
    @Override
    public List<UserVO> getList() {

        List<UserVO> userList = userMapper.getList();

        for(UserVO userVO : userList) {
            userVO.setAuthList(userMapper.selectAuths(userVO.getEmail()));
        }
        return userList;
    }

    @Override
    public int modifyNoPwd(UserVO userVO) {
        return userMapper.modifyNoPwd(userVO);
    }

    @Override
    public int modify(UserVO userVO) {
        return userMapper.modify(userVO);
    }

    @Override
    public int delete(String email) {
        int isOk = userMapper.deleteAuth(email);
        return userMapper.delete(email);
    }

    @Override
    public int lastLogin(String email) {
        return userMapper.lastLogin(email);
    }

}
