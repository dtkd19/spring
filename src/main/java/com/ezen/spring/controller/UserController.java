package com.ezen.spring.controller;


import com.ezen.spring.domain.UserVO;
import com.ezen.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/user/*")
@RequiredArgsConstructor
public class UserController {
    private final UserService usv;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public void register(){}

    @GetMapping("/login")
    public String login(){
        return "/user/login";
    }

    @PostMapping("/register")
    public String register(UserVO userVO){
        userVO.setPwd(passwordEncoder.encode(userVO.getPwd()));
        int isOk = usv.register(userVO);

        log.info(" userVO >>> {} ", userVO);

        return "/index";
    }

    @GetMapping("/list")
    public void list(Model m) {
        List<UserVO> userList = usv.getList();

        m.addAttribute("userList", userList);
    }
    @GetMapping("/modify")
    public void modify() {}

    @PostMapping("/modify")
    public String modify(UserVO userVO) {

        log.info(">>> modfiy userVO >> {}" ,userVO);

        int isOk = 0;

        if(userVO.getPwd() == null || userVO.getPwd().isEmpty()) {
            // 비번 없이 업데이트 진행
            isOk = usv.modifyNoPwd(userVO);
        }else {
            // 비번 암호화 후 업데이트 진행
            userVO.setPwd(passwordEncoder.encode(userVO.getPwd()));
            isOk = usv.modify(userVO);
        }
        
        return "redirect:/user/logout";
    }

    @GetMapping("/user/delete")
    public String delete(Principal principal) {

        String email = principal.getName();

        int isOk = usv.delete(email);

        return "redirect:/user/logout";
    }

    @GetMapping("/user/lastLogin")
    public String lastLogin(Principal principal){

        String email = principal.getName();

        int isOk = usv.lastLogin(email);

        return "redirect:/user/logout";
    }



}
