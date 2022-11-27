package com.mysite.jikpoom.beans.dao;

import com.mysite.jikpoom.beans.vo.BbsVO;
import com.mysite.jikpoom.beans.vo.Criteria;
import com.mysite.jikpoom.mappers.BbsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BbsDAO {
    @Autowired

    private BbsMapper mapper;


    public void register(BbsVO bbs){
        mapper.insertSelectKey_bno(bbs);
    }

    public BbsVO get(Long bno) {
        return mapper.read(bno);
    }

    public boolean modify(BbsVO bbs){return mapper.update(bbs) != 0;}
    public boolean remove(Long bno){
        return mapper.delete(bno) != 0;
    }
    public List<BbsVO> getList(){
        return mapper.getList();
    }
    public List<BbsVO> getList(Criteria criteria){
        return  mapper.getListWithPaging(criteria);
    }
    public List<BbsVO> getFrontList(Criteria criteria){
        return mapper.getFrontListWithPaging(criteria);
    }
    public List<BbsVO> getBackList(Criteria criteria){
        return mapper.getBackListWithPaging(criteria);
    }
    public List<BbsVO> getSpringList(Criteria criteria) {
        return mapper.getSpringListWithPaging(criteria);
    }
    public List<BbsVO> getEnBList(Criteria criteria) {
        return mapper.getEnBListWithPaging(criteria);
    }
    public int getTotal(Criteria criteria) {
        return mapper.getTotal(criteria);
    }
}
