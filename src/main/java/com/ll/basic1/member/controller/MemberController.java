package com.ll.basic1.member.controller;

import com.ll.basic1.base.Rq;
import com.ll.basic1.base.RsData;
import com.ll.basic1.member.entity.Member;
import com.ll.basic1.member.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @GetMapping("/member/login")
    @ResponseBody
    public RsData showLogin(HttpServletResponse response, @RequestParam String username, @RequestParam String password) {
        if (username == null || username.trim().length() == 0) {
            return RsData.of("F-3", "username(을)를 입력해주세요.");
        }

        if (password == null || password.trim().length() == 0) {
            return RsData.of("F-4", "password(을)를 입력해주세요.");
        }

        Rq rq = new Rq(response);

        RsData rsData = memberService.tryLogin(username, password);
        if (rsData.isSuccess()) {
            Member member = (Member) rsData.getData();
            rq.setCookie(member.getId());
        }

        return rsData;
    }

    @GetMapping("/member/logout")
    @ResponseBody
    public RsData showLogout(HttpServletRequest request, HttpServletResponse response) {
        Rq rq = new Rq(request, response);
        rq.removeCookie("loginMemberId");

        return RsData.of("S-1", "로그아웃 되었습니다.");
    }

    @GetMapping("/member/me")
    @ResponseBody
    public RsData showMe(HttpServletRequest request) {

        Rq rq = new Rq(request);
        String loginMemberId = rq.getLoginMemberId();

        if (loginMemberId == null) {
            return RsData.of("F-1", "로그인 후 이용해주세요.");
        }

        Member member = memberService.findById(Integer.parseInt(loginMemberId));

        return RsData.of("S-1", "당신의 username(은)는 " + member.getUsername() + "입니다.");
    }
}
