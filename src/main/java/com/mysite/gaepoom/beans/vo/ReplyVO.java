package com.mysite.gaepoom.beans.vo;

import lombok.Data;

@Data
public class ReplyVO {
    private Long rno;
    private Long bno;

    private String reply;
    private String replier;
    private String replyDate;
    private String updateDate;

}
