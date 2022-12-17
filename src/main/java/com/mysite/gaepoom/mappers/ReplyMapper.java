package com.mysite.gaepoom.mappers;

import com.mysite.gaepoom.beans.vo.Criteria;
import com.mysite.gaepoom.beans.vo.ReplyVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplyMapper {
    // 댓글 등록
    public int insert(ReplyVO reply);
    // 댓글 1개 조회
    public ReplyVO read(Long rno);
    // 댓글 삭제
    public int delete(Long rno);
    // 댓글 수정
    public int update(ReplyVO reply);
    // 댓글 목록
    public List<ReplyVO> getListWithPaging(@Param("criteria")Criteria criteria, @Param("bno") Long bno);
}
