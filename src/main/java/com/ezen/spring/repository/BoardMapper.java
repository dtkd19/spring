package com.ezen.spring.repository;

import com.ezen.spring.domain.BoardVO;
import com.ezen.spring.domain.PagingVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

//    int registerFile(BoardDTO BoardDTO);

    int register(BoardVO BoardVO);

    List<BoardVO> getList(PagingVO pgvo);

    BoardVO getDetail(long bno);

    int modify(BoardVO boardDTO);

    int delete(int bno);

    int getTotal(PagingVO pgvo);

    long getMaxBno();
}
