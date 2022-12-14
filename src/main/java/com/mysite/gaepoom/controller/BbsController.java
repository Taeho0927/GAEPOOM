package com.mysite.gaepoom.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.mysite.gaepoom.services.KakaoAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.mysite.gaepoom.beans.vo.BbsVO;
import com.mysite.gaepoom.beans.vo.Criteria;
import com.mysite.gaepoom.beans.vo.PageDTO;
import com.mysite.gaepoom.services.BbsService;

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
        model.addAttribute("list", service.getList(cri));
        model.addAttribute("pageMaker", new PageDTO(cri, service.getTotal(cri)));
    }
    @GetMapping("front")
    public String list2(Criteria cri, Model model) {
        model.addAttribute("list", service.getFrontList(cri));
        model.addAttribute("pageMaker", new PageDTO(cri, service.getTotal(cri)));
        return "/bbs/list";
    }
    @GetMapping("back")
    public String list3(Criteria cri, Model model) {
        model.addAttribute("list", service.getBackList(cri));
        model.addAttribute("pageMaker", new PageDTO(cri, service.getTotal(cri)));
        return "/bbs/list";
    }
    @GetMapping("spring")
    public String list4(Criteria cri, Model model) {
        model.addAttribute("list", service.getSpringList(cri));
        model.addAttribute("pageMaker", new PageDTO(cri, service.getTotal(cri)));
        return "/bbs/list";
    }
    @GetMapping("e&b")
    public String list5(Criteria cri, Model model) {
        model.addAttribute("list", service.getEnBList(cri));
        model.addAttribute("pageMaker", new PageDTO(cri, service.getTotal(cri)));
        return "/bbs/list";
    }
    @GetMapping("login")
    public String doLogin(){
        return "bbs/login";
    }
    @GetMapping("index")
    public String moveIndex(){
        return "bbs/index";
    }
    @RequestMapping(value = "/logout")
    public String logout(HttpSession session){
        String access_Token = (String) session.getAttribute("access_Token");

        if (access_Token != null && !"".equals(access_Token)){
            KakaoAPI.kakaoLogout(access_Token);
            session.removeAttribute("access_Token");
            session.removeAttribute("userId");
            session.removeAttribute("userName");
            System.out.println("??????????????????");
        }
        return "bbs/index";
    }
//    @GetMapping("logout")
//    public String doLogout(){return "bbs/login/logout";}


    @GetMapping("register")
    public String register(RedirectAttributes rttr, HttpServletRequest request){
        rttr.addFlashAttribute("username", request.getSession().getAttribute("userName"));
        return "bbs/register";
    }


    @PostMapping("register")
    public RedirectView register(BbsVO bbs, RedirectAttributes rttr, HttpServletRequest request) {

        bbs.setWriter(request.getSession().getAttribute("userName").toString());
        service.register(bbs);

        // ????????? ????????? ????????? ?????? ???????????? ?????? RedirectAttribute??? ??????
        rttr.addFlashAttribute("bno", bbs.getBno());

        return new RedirectView("list");

    }

    @GetMapping({"get", "modify"})
    public void get(@RequestParam("bno") Long bno, HttpServletRequest request, Model model) {
        String reqURI = request.getRequestURI();
        String reqType = reqURI.substring(reqURI.indexOf(request.getContextPath()) + 7);

        model.addAttribute("bbs", service.get(bno));
    }

    @PostMapping("modify")
    public RedirectView modify(BbsVO bbs, RedirectAttributes rttr) {

        if(service.modify(bbs)) {
            // addAttribute()??? GET???????????? QueryString??? ??????
            // addFlashAttribute()??? Session??? ???????????? ??????
            rttr.addAttribute("result", "success");
            //rttr.addAttribute("board", service.get(board.getBno()));
        }

        return new RedirectView("list");
    }

    // ?????? ?????? ??? result??? "success"??? flash??? ????????? ??????
    // ?????? ?????? ??? result??? "fail"??? flash??? ????????? ??????
    @PostMapping("remove")
    public RedirectView remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
        if(service.remove(bno)) {
            rttr.addFlashAttribute("result", "success");
        }
        else {
            rttr.addFlashAttribute("result", "fail");
        }

        return new RedirectView("list");
    }

}
