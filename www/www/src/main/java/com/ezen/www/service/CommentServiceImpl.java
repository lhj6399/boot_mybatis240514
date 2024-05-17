package com.ezen.www.service;

import com.ezen.www.domain.CommentVO;
import com.ezen.www.domain.PagingVO;
import com.ezen.www.handler.PagingHandler;
import com.ezen.www.repository.CommentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
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
        // totalCount
        int totalCount = commentMapper.bnoTotalCount(bno);
        // CommentList
        List<CommentVO> cmtList = commentMapper.getList(bno, pgvo);
        return new PagingHandler(pgvo, totalCount, cmtList);
    }
}
