package com.ezen.www.service;

import com.ezen.www.domain.MemberVO;
import com.ezen.www.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemverServiceImpl implements MemberService{
    private final MemberMapper memberMapper;

    @Override
    public int insert(MemberVO mvo) {
        int isOk = memberMapper.insert(mvo);

        return (isOk>0 ? memberMapper.insertAuth(mvo.getEmail()) : 0);
    }

    @Override
    public List<MemberVO> getList() {
        List<MemberVO> list = memberMapper.getList();
        for(MemberVO mvo : list){
            mvo.setAuthList(memberMapper.selectAuth(mvo.getEmail()));
        }
        log.info("list>>>{}",list);
        return list;
    }

    @Override
    public int modify(MemberVO mvo) {
        return 0;
    }

    @Override
    public int modifyNoPwd(MemberVO mvo) {
        return 0;
    }
}
