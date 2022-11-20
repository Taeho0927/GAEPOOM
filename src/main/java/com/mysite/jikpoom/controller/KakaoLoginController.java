package com.mysite.jikpoom.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.mysite.jikpoom.services.KakaoAPI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
@Slf4j
@RequestMapping("bbs/login/*")
public class KakaoLoginController {
    @Autowired
    private KakaoAPI kakao;
    @RequestMapping(value = "/kakao")
    public String login(@RequestParam("code")String code, HttpServletRequest req){
        String access_Token = kakao.getKaKaoAccessToken(code);
        HashMap<String, Object> userInfo = kakao.getUserInfo(access_Token);
        System.out.println("login Controller :"+userInfo);

        // 클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰을 등록
        HttpSession session = req.getSession();

        if (userInfo.get("email") != null) {
            session.setAttribute("userName",userInfo.get("nickname"));
            session.setAttribute("userId", userInfo.get("email"));
            session.setAttribute("access_Token", access_Token);
            System.out.println(session.getAttribute("userName")+"님 반갑습니다");
        }

        return "bbs/login";
    }
}
