package com.ezen.spring.service;

import com.ezen.spring.domain.CommentVO;
import com.ezen.spring.domain.PagingVO;
import com.ezen.spring.handler.PagingHandler;
import com.ezen.spring.repository.CommentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class CommentServiceImpl implements CommentService{
    private final CommentMapper commentMapper;

    @Override
    public int post(CommentVO cvo) {
        return commentMapper.post(cvo);
    }

    @Transactional
    @Override
    public PagingHandler getList(long bno, PagingVO pgvo) {
        List<CommentVO> cmtList =  commentMapper.getList(bno,pgvo);
        int totalCount = commentMapper.getTotalCount(bno);
        PagingHandler ph = new PagingHandler(pgvo,totalCount,cmtList);
        return ph;
    }

    @Override
    public int delete(long cno) {
        return commentMapper.delete(cno);
    }

    @Override
    public int modify(CommentVO cvo) {
        return commentMapper.modify(cvo);
    }

}
