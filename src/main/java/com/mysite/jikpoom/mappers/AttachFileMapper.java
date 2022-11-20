package com.mysite.jikpoom.mappers;

import com.mysite.jikpoom.beans.vo.AttachFileVO;

import java.util.List;

public interface AttachFileMapper {
    void insert(AttachFileVO vo);
    void delete(AttachFileVO vo);
    List<AttachFileVO> findByBno(Long bno);
}
