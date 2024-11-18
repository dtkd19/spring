package com.ezen.spring.service;

import com.ezen.spring.domain.CommentVO;
import com.ezen.spring.domain.PagingVO;
import com.ezen.spring.handler.PagingHandler;
import org.apache.ibatis.annotations.Param;

public interface CommentService {
    int post(CommentVO cvo);


    PagingHandler getList(long bno, PagingVO pgvo);

    int delete(long cno);

    int modify(CommentVO cvo);
}
