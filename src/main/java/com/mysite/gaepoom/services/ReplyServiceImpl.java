package com.mysite.gaepoom.services;

import com.mysite.gaepoom.beans.dao.ReplyDAO;
import com.mysite.gaepoom.beans.vo.Criteria;
import com.mysite.gaepoom.beans.vo.ReplyVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{
    @Autowired
    private final ReplyDAO replyDAO;
    @Override
    public int register(ReplyVO reply) {
        log.info("Service register...."+reply);
        return replyDAO.register(reply);
    }
    @Override
    public ReplyVO get(Long rno) {
        log.info("Service get...."+rno);
        return replyDAO.get(rno);
    }
    @Override
    public int modify(ReplyVO reply) {
        log.info("Service modify..."+reply);
        return replyDAO.modify(reply);
    }
    @Override
    public int remove(Long rno) {
        log.info("Service remove...."+rno);
        return replyDAO.remove(rno);
    }
    @Override
    public List<ReplyVO> getList(Criteria criteria, Long bno) {
        log.info("Service Reply List of a bbs...."+bno);
        return replyDAO.getList(criteria,bno);
    }
}