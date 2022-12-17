package com.mysite.gaepoom.services;

import com.mysite.gaepoom.beans.dao.BbsDAO;
import com.mysite.gaepoom.beans.vo.BbsVO;
import com.mysite.gaepoom.beans.vo.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BbsServiceImpl implements BbsService{
    @Autowired
    private BbsDAO bbsDAO;
    @Override
    public void register(BbsVO bbs){
        bbsDAO.register(bbs);
    }
    @Override
    public BbsVO get(Long bno){
        return bbsDAO.get(bno);
    }
    @Override
    public boolean modify(BbsVO bbs){
        return bbsDAO.modify(bbs);
    }
    @Override
    public boolean remove(Long bno){
        return bbsDAO.remove(bno);
    }
    @Override
    public List<BbsVO> getList(){
        return bbsDAO.getList();
    }
    @Override
    public List<BbsVO> getList(Criteria criteria){
        return bbsDAO.getList(criteria);
    }
    @Override
    public List<BbsVO> getFrontList(Criteria criteria) {
        return bbsDAO.getFrontList(criteria);
    }
    @Override
    public List<BbsVO> getBackList(Criteria criteria) {
        return bbsDAO.getBackList(criteria);
    }
    @Override
    public List<BbsVO> getSpringList(Criteria criteria) {
        return bbsDAO.getSpringList(criteria);
    }
    @Override
    public List<BbsVO> getEnBList(Criteria criteria) {
        return bbsDAO.getEnBList(criteria);
    }

    @Override
    public int getTotal(Criteria criteria){
        return bbsDAO.getTotal(criteria);
    }
}
