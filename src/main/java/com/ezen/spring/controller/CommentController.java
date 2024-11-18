package com.ezen.spring.controller;

import com.ezen.spring.domain.CommentVO;
import com.ezen.spring.domain.PagingVO;
import com.ezen.spring.handler.PagingHandler;
import com.ezen.spring.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Slf4j
@RequestMapping("/comment/*")
@RestController
public class CommentController {
    private final CommentService csv;

    @PostMapping("/post")
    public ResponseEntity<String> post(@RequestBody CommentVO cvo){
        int isOk = csv.post(cvo);

        return isOk > 0 ?
                new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "/{bno}/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <PagingHandler> list(@PathVariable("bno") long bno, @PathVariable("page") int page ) {

        PagingVO pgvo = new PagingVO(page, 5);  // DB에 설정할 값 설정 limit 0,5
        PagingHandler ph = csv.getList(bno,pgvo);

        log.info(">>> ph > {}", ph);

        return new ResponseEntity<PagingHandler>(ph, HttpStatus.OK);
    }


    @ResponseBody
    @DeleteMapping("/delete/{cno}")
    public String delete(@PathVariable("cno") long cno){

        int isOk = csv.delete(cno);

        return isOk > 0 ? "1" : "0";
    }

    @ResponseBody
    @PutMapping("/modify")
    public String modify(@RequestBody CommentVO cvo){

        int isOK = csv.modify(cvo);

        return isOK > 0 ? "1" : "0";
    }



}
