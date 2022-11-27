package com.mysite.jikpoom.services;

import com.mysite.jikpoom.beans.vo.Criteria;
import com.mysite.jikpoom.beans.vo.BbsVO;

import java.util.List;

public interface BbsService {
    public void register(BbsVO bbs);
    public BbsVO get(Long bno);
    public boolean modify(BbsVO bbs);
    public boolean remove(Long bno);
    public List<BbsVO> getList();
    public List<BbsVO> getList(Criteria criteria);
    public List<BbsVO> getFrontList(Criteria criteria);
    public List<BbsVO> getBackList(Criteria criteria);
    public List<BbsVO> getSpringList(Criteria criteria);
    public List<BbsVO> getEnBList(Criteria criteria);
    public int getTotal(Criteria criteria);

}
