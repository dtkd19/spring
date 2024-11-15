package com.ezen.spring.service;


import com.ezen.spring.domain.BoardDTO;
import com.ezen.spring.domain.BoardVO;
import com.ezen.spring.domain.FileVO;
import com.ezen.spring.domain.PagingVO;
import com.ezen.spring.repository.BoardMapper;
import com.ezen.spring.repository.FileMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{
    private final BoardMapper boardMapper;
    private final FileMapper fileMapper;


    @Transactional
    @Override
    public int register(BoardDTO boardDTO) {

        int isOk = boardMapper.register(boardDTO.getBvo());

        if(boardDTO.getFlist() == null){
            return isOk;
        }

        if(isOk > 0 && boardDTO.getFlist().size() > 0 ){
            // 파일저장
            // board의 bno를 가져오기 => 가장 큰 bno
            long bno = boardMapper.getMaxBno();
            for(FileVO fvo : boardDTO.getFlist()){
                fvo.setBno(bno);
                isOk *= fileMapper.insertFile(fvo);
            }
        }

        return isOk;
    }

    @Override
    public List<BoardVO> getList(PagingVO pgvo) {

        return boardMapper.getList(pgvo);
    }

    @Override
    public BoardDTO getDetail(long bno) {
        // fileList가져와서 DTO 생성
        BoardDTO bdto = new BoardDTO(boardMapper.getDetail(bno),fileMapper.getFileList(bno));
        return bdto;
    }

    @Override
    public int modify(BoardDTO boardDTO) {

        int isOk = boardMapper.modify(boardDTO.getBvo());

        if(boardDTO.getFlist() == null){
            return isOk;
        }
        if(isOk > 0 && !boardDTO.getFlist().isEmpty()){
            for(FileVO fvo : boardDTO.getFlist()){
                fvo.setBno((boardDTO.getBvo().getBno()));
                isOk *= fileMapper.insertFile(fvo);
            }
        }
        return isOk;
    }

    @Override
    public int delete(int bno) {
        return boardMapper.delete(bno);
    }

    @Override
    public int getTotal(PagingVO pgvo) {
        return boardMapper.getTotal(pgvo);
    }

    @Override
    public int fileDelete(String uuid) {
        return fileMapper.fileDelete(uuid);
    }

    @Override
    public FileVO getFile(String uuid) {
        return fileMapper.getFile(uuid);
    }
}
