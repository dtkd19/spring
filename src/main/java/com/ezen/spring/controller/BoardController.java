package com.ezen.spring.controller;

import com.ezen.spring.domain.BoardVO;
import com.ezen.spring.domain.PagingVO;
import com.ezen.spring.handler.PagingHandler;
import com.ezen.spring.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String list(Model m, PagingVO pgvo){

        int totalCount = bsv.getTotal(pgvo);

        PagingHandler ph = new PagingHandler(pgvo,totalCount);

        m.addAttribute("list", bsv.getList(pgvo));
        m.addAttribute("ph",ph);

        return "/board/list";
    }
    @GetMapping("/detail")
    public void detail(Model m, @RequestParam("bno") int bno){
        // 전체 게시글 수 가져오기

        BoardVO boardVO = bsv.getDetail(bno);

        log.info(" bvo >> {} ", boardVO);

        m.addAttribute("bvo", boardVO);
    }

   @PostMapping("/modify")
    public String modify(BoardVO boardVO){
        int isOk = bsv.modify(boardVO);
        return "redirect:/board/detail?bno=" + boardVO.getBno();
    }

    @GetMapping("/delete")
    public  String delete(@RequestParam("bno") int bno){
        int isOk = bsv.delete(bno);
        return "redirect:/board/list";
    }

}
