package com.ezen.spring.controller;

import com.ezen.spring.domain.BoardDTO;
import com.ezen.spring.domain.BoardVO;
import com.ezen.spring.domain.FileVO;
import com.ezen.spring.domain.PagingVO;
import com.ezen.spring.handler.FileDeleteHandler;
import com.ezen.spring.handler.FileHandler;
import com.ezen.spring.handler.PagingHandler;
import com.ezen.spring.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RequestMapping("/board/*")
@RequiredArgsConstructor
@Slf4j
@Controller
public class BoardController {
    private final BoardService bsv;
    private final FileHandler fh;

    @GetMapping("/register")
    public String register(){

        return "/board/register";
    }

    @PostMapping("/register")
    public String register(BoardVO boardVO,
                           @RequestParam(name = "files", required = false)MultipartFile[] files){
        log.info(">>> boardVO >> {}", boardVO);
        List<FileVO> flist = null;

        if( files != null && files[0].getSize() > 0){
            flist = fh.uploadFiles(files);
            log.info(">>> flist >> {}", flist);
        }

        int isOk = bsv.register(new BoardDTO(boardVO,flist));

        return "index";
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
    public void detail(Model m, @RequestParam("bno") long bno){
        // 전체 게시글 수 가져오기

        BoardDTO boardDTO = bsv.getDetail(bno);

        log.info(" bdto >> {} ", boardDTO);

        m.addAttribute("bdto", boardDTO);
    }

   @PostMapping("/modify")
    public String modify(BoardVO boardVO, RedirectAttributes redirectAttributes, @RequestParam(value = "files", required = false) MultipartFile[] files){
       List<FileVO> flist = null;

       if( files != null && files[0].getSize() > 0 ){
           flist = fh.uploadFiles(files);
       }
       int isOk = bsv.modify(new BoardDTO(boardVO,flist));

       redirectAttributes.addAttribute("bno",boardVO.getBno());

        return "redirect:/board/detail";
    }

    @GetMapping("/delete")
    public  String delete(@RequestParam("bno") int bno){
        int isOk = bsv.delete(bno);
        return "redirect:/board/list";
    }

    @ResponseBody
    @DeleteMapping(value = "/file/{uuid}")
    public String fileDelete(@PathVariable("uuid") String uuid){

        FileVO fvo = bsv.getFile(uuid);

        log.info(" fvo >> {} >>  " ,fvo);

        // 파일 삭제
        FileDeleteHandler fileDeleteHandler = new FileDeleteHandler();

        int delOk = fileDeleteHandler.deleteFile(fvo.getSaveDir(),fvo.getUuid(),fvo.getFileName());

        log.info(" delOk >> {} ", delOk);

        int isOk = bsv.fileDelete(uuid);

        return isOk > 0 ? "1" : "0";
    }

}
