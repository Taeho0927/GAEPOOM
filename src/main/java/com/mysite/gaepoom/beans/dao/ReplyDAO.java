package com.mysite.gaepoom.beans.dao;

import com.mysite.gaepoom.beans.vo.Criteria;
import com.mysite.gaepoom.beans.vo.ReplyVO;
import com.mysite.gaepoom.mappers.ReplyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ReplyDAO {
    @Autowired
    private final ReplyMapper mapper;

    public int register(ReplyVO reply) {
        log.info("register...."+reply);
        return mapper.insert(reply);
    }
    public ReplyVO get(Long rno){
        log.info("get......."+rno);
        return mapper.read(rno);
    }
    public int modify(ReplyVO reply) {
        log.info("modify....."+reply);
        return mapper.update(reply);
    }
    public int remove(Long rno) {
        log.info("remove...."+rno);
        return mapper.delete(rno);
    }
    public List<ReplyVO> getList(Criteria criteria, Long bno){
        log.info("Get Reply List of Board : "+ bno);
        return mapper.getListWithPaging(criteria,bno);
    }
}
