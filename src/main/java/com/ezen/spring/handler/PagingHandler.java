package com.ezen.spring.handler;

import com.ezen.spring.domain.CommentVO;
import com.ezen.spring.domain.PagingVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PagingHandler {
    private int startPage;
    private int endPage;
    private int realEndPage;
    private boolean prev, next;

    private int totalCount;
    private PagingVO pgvo;

    private List<CommentVO> cmtList;

    public PagingHandler(PagingVO pgvo, int totalCount){
        this.pgvo = pgvo;
        this.totalCount = totalCount;

        // 1~10 => 10 / 11~20 => 20 / 21~30 => 30 ...
        // (현재 페이지번호 / 10) 올림 => * 10
        this.endPage = (int)Math.ceil(pgvo.getPageNo() / (double)pgvo.getQty()) * 10;
        this.startPage = endPage - (pgvo.getQty()-1);

        this.realEndPage = (int)Math.ceil(totalCount / (double)pgvo.getQty());

        if(realEndPage < endPage){
            this.endPage = realEndPage;
        }

        this.prev = this.startPage > 1;  // 1 11 21
        this.next = this.endPage < realEndPage;

    }
    public PagingHandler(PagingVO pgvo, int totalCount, List<CommentVO> cmtList) {
        this(pgvo, totalCount);
        this.cmtList = cmtList;
    }

}
