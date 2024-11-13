package com.ezen.spring.controller;

import com.ezen.spring.domain.BoardVO;
import com.ezen.spring.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/board/*")
@RequiredArgsConstructor
@Slf4j
@Controller
public class BoardController {
    private final BoardService bsv;

    @GetMapping("/register")
    public String register(){

        return "/board/register";
    }

    @PostMapping("/register")
    public String register(BoardVO boardVO){
        log.info(">>> boardVO >> {}", boardVO);

        int isOk = bsv.register(boardVO);

        return "/board/register";
    }
    @GetMapping("/list")
    public String list(Model m){

        List<BoardVO> list = bsv.getList();

        m.addAttribute("list",list);

        log.info(">>> list > {}" ,list);

        return "/board/list";
    }

}
