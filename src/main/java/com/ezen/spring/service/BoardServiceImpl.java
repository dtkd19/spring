package com.ezen.spring.service;


import com.ezen.spring.domain.BoardVO;
import com.ezen.spring.domain.PagingVO;
import com.ezen.spring.repository.BoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{
    private final BoardMapper boardMapper;

    @Override
    public int register(BoardVO boardVO) {

        return boardMapper.register(boardVO);
    }

    @Override
    public List<BoardVO> getList(PagingVO pgvo) {

        return boardMapper.getList(pgvo);
    }

    @Override
    public BoardVO getDetail(int bno) {

        return boardMapper.getDetail(bno);
    }

    @Override
    public int modify(BoardVO boardVO) {
        return boardMapper.modify(boardVO);
    }

    @Override
    public int delete(int bno) {
        return boardMapper.delete(bno);
    }

    @Override
    public int getTotal(PagingVO pgvo) {
        return boardMapper.getTotal(pgvo);
    }
}
