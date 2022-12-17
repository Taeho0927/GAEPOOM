package com.mysite.jikpoom.beans.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class BbsVO {
    private Long bno;
    private String title;
    private String content;
    private String writer;
    private String regdate;
    private String updatedate;
    private String category;
    // input 태그의 name
    // attachList[i].fileName
    // attachList[i].uuid
    // attachList[i].uploadPath
    // attachList[i].fileType
    // 방식으로 submit 하면 자동으로 List 에 add
    //private List<SmarteditorVO> attachList;
    public BbsVO(){}
}
