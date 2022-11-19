package com.mysite.jikpoom.services;

import com.mysite.jikpoom.beans.dao.BbsDAO;
import com.mysite.jikpoom.beans.vo.BbsVO;
import com.mysite.jikpoom.beans.vo.Criteria;
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
    public int getTotal(Criteria criteria){
        return bbsDAO.getTotal(criteria);
    }
}
