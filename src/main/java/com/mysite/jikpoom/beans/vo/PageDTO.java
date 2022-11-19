package com.mysite.jikpoom.beans.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class PageDTO {
    private int startPage;
    private int endPage;
    private int realEnd;
    private boolean prev, next;

    private int total;
    private Criteria criteria;

    public PageDTO(){};
    public PageDTO(Criteria criteria, int total){
        this.criteria = criteria;
        this.total = total;
        this.endPage = (int)(Math.ceil(criteria.getPageNum()/10.0))*10;
        this.startPage=endPage-9;

        realEnd = (int)(Math.ceil((total * 1.0)/ criteria.getAmount()));
        if(realEnd < this.endPage){
            this.endPage = realEnd == 0 ? 1 : realEnd;
        }
        this.prev = this.startPage > 1;
        this.next = this.endPage < realEnd;
    }
}
