package com.mysite.gaepoom.mappers;

import com.mysite.gaepoom.beans.vo.BbsVO;
import com.mysite.gaepoom.beans.vo.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BbsMapper {
    // 게시글 목록
    List<BbsVO> getList();
    List<BbsVO> getListWithPaging(Criteria criteria);
    List<BbsVO> getFrontListWithPaging(Criteria criteria);
    List<BbsVO> getBackListWithPaging(Criteria criteria);
    List<BbsVO> getSpringListWithPaging(Criteria criteria);
    List<BbsVO> getEnBListWithPaging(Criteria criteria);

    // 게시글 등록
    void insert(BbsVO bbs);

    // 게시글 등록(PK 가져오기)
    void insertSelectKey_bno(BbsVO bbs);

    // 게시글 보기
    BbsVO read(Long bno);

    // 게시글 삭제
    // 성공 시 삭제한 건수 를 리턴, 실패 시 0 리턴
    int delete(Long bno);

    // 게시글 수정
    // 수정완료 시 수정한 건수 리턴, 실팻시 0 리턴
    int update(BbsVO bbs);

    // 게시글 총 개수
    int getTotal(Criteria criteria);
}
