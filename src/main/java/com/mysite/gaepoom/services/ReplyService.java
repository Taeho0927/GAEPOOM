package com.mysite.gaepoom.services;

import com.mysite.gaepoom.beans.vo.Criteria;
import com.mysite.gaepoom.beans.vo.ReplyVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReplyService {
    public int register(ReplyVO reply);
    public ReplyVO get(Long rno);
    public int modify(ReplyVO reply);
    public int remove(Long rno);
    public List<ReplyVO> getList(Criteria criteria,Long bno);
}
