package com.mysite.jikpoom.controller;

import com.mysite.jikpoom.beans.vo.Criteria;
import com.mysite.jikpoom.beans.vo.ReplyVO;
import com.mysite.jikpoom.services.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

// ViewResolver 를 사용하지 않고 리턴 값을 그대로 사용한다. 내부 메서드는 REST 방식으로만 사용가능
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("replies/*")
public class ReplyController {
    private final ReplyService replyService;

    // 댓글 등록
    // 브라우저에서 JSON 타입으로 데이터를 전송하고
    // 서버에서는 댓글의 처리 결과에 따라 문자열로 결과 전송
    // consumes : Ajax 를 통해 전달받은 데이터의 타입
    // produces : Ajax 의 success:function(result)에 있는 result 로 전송할 데이터의 타입
    // @ResponseBody : @Controller 에서 Body 를 응답하기 위해(ViewResolver 를 가지 않게 하기 위해)

    // 문자열 전달 시 한글이 깨지는 것을 막기 위해 text/plain; charset=utf-8 을 작성한다.
    @PostMapping(value = "/new", consumes = "application/json", produces = "text/plain; charset=utf-8")
    // ResponseEntity : 서버의 상태코드, 응답 메시지 등을 담을 수 있는 타입
    // @RequestBody 를 적용하여 JSON 데이터를 ReplyVO 타입으로 변환하도록 지정
    public ResponseEntity<String> create(@RequestBody ReplyVO reply) throws UnsupportedEncodingException{
        int insertCnt = 0;
        log.info("ReplyVO : "+ reply);
        insertCnt = replyService.register(reply);

        if (insertCnt == 1){
            return new ResponseEntity<>(new String("댓글 등록 성공".getBytes(),"UTF-8"), HttpStatus.OK);
        }else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 에러
    }
    // 게시글 댓글 전체 조회
    @GetMapping("/pages/{bno}/{page}")
    public List<ReplyVO> getList(@PathVariable("bno") Long bno,@PathVariable("page") int page){
        log.info("getList......");
        Criteria criteria = new Criteria(page,10);
        log.info(criteria.toString());
        return replyService.getList(criteria,bno);
    }
    // 댓글 조회
    @GetMapping("{rno}")
    public ReplyVO get(@PathVariable("rno") Long rno){
        log.info("get......");
        return replyService.get(rno);
    }
    // 댓글 수정
    @PatchMapping(value = {"/{rno}","/{rno}/{replier}"}, consumes = "application/json", produces = "text/plain; charset=utf-8")
    public String modify(@RequestBody ReplyVO reply
            , @PathVariable(value = "replier",required = false) String replier
            , @PathVariable("rno")Long rno) {
        reply.setRno(rno);

        log.info("rno : " + rno);
        log.info("modify : " + reply);

        if (reply.getReplier() == null) { // json 검증
            reply.setReplier("anonymous");
        }
        return replyService.modify(reply)==1 ? "댓글 수정 성공" : "댓글 수정 실패";
    }
    @DeleteMapping(value = "{rno}", produces = "text/plain; charset=utf-8")
    public ResponseEntity<String> remove(@PathVariable ("rno")Long rno) throws UnsupportedEncodingException{
        log.info("remove : "+rno);

        return replyService.remove(rno)==1 ? new ResponseEntity<>(new String("댓글 삭제 성공".getBytes(),"UTF-8"),HttpStatus.OK)
                :
                new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
