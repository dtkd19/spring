package com.ezen.spring.repository;

import com.ezen.spring.domain.BoardVO;
import com.ezen.spring.domain.PagingVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    int register(BoardVO boardVO);

    List<BoardVO> getList(PagingVO pgvo);

    BoardVO getDetail(int bno);

    int modify(BoardVO boardVO);

    int delete(int bno);

    int getTotal(PagingVO pgvo);
}
