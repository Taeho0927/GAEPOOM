package com.mysite.jikpoom.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.mysite.jikpoom.beans.vo.BbsVO;
import com.mysite.jikpoom.beans.vo.Criteria;
import com.mysite.jikpoom.beans.vo.PageDTO;
import com.mysite.jikpoom.services.BbsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("bbs/*")
public class BbsController {
    @Autowired
    private final BbsService service;

    @GetMapping("list")
    public void list(Criteria cri, Model model) {
        log.info("-------------------------------------");
        log.info("list called");
        log.info("-------------------------------------");
        model.addAttribute("list", service.getList(cri));
        model.addAttribute("pageMaker", new PageDTO(cri, service.getTotal(cri)));
    }
    @GetMapping("login")
    public String doLogin(){
        return "bbs/login";
    }


    @GetMapping("register")
    public void register(){}


    @PostMapping("register")
    public RedirectView register(BbsVO bbs, RedirectAttributes rttr) {
        log.info("-------------------------------------");
        log.info("register called : " + bbs);
        log.info("-------------------------------------");

        service.register(bbs);

        // 새롭게 등록된 번호와 같이 전달하기 위해 RedirectAttribute를 이용
        rttr.addFlashAttribute("bno", bbs.getBno());

        return new RedirectView("list");

    }

    @GetMapping({"get", "modify"})
    public void get(@RequestParam("bno") Long bno, HttpServletRequest request, Model model) {
        String reqURI = request.getRequestURI();
        String reqType = reqURI.substring(reqURI.indexOf(request.getContextPath()) + 7);

        log.info("-------------------------------------");
        log.info("get called : " + bno);
        log.info("-------------------------------------");
        log.info(reqType);

        model.addAttribute("bbs", service.get(bno));
    }

    @PostMapping("modify")
    public RedirectView modify(BbsVO bbs, RedirectAttributes rttr) {
        log.info("modify : " + bbs);

        if(service.modify(bbs)) {
            // addAttribute()는 GET방식으로 QueryString에 전달
            // addFlashAttribute()는 Session에 저장되어 전달
            rttr.addAttribute("result", "success");
            //rttr.addAttribute("board", service.get(board.getBno()));
        }

        return new RedirectView("list");
    }

    // 삭제 성공 시 result에 "success"를 flash에 담아서 전달
    // 삭제 실패 시 result에 "fail"를 flash에 담아서 전달
    @PostMapping("remove")
    public RedirectView remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
        log.info("remove : " + bno);

        if(service.remove(bno)) {
            rttr.addFlashAttribute("result", "success");
        }
        else {
            rttr.addFlashAttribute("result", "fail");
        }

        return new RedirectView("list");
    }

}
