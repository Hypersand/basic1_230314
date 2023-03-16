package com.ll.basic1.member.controller;

import com.ll.basic1.base.Rq;
import com.ll.basic1.base.RsData;
import com.ll.basic1.member.entity.Member;
import com.ll.basic1.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final Rq rq;


    @GetMapping("/member/login")
    public String attemptLogin() {
        boolean checkLogin = rq.checkLogin("loginMemberId");
        if (checkLogin) {
            return "login/alreadyLoginPage";
        }
        return "login/loginPage";
    }

    @PostMapping("/member/login")
    @ResponseBody
    public RsData showLogin(@RequestParam String username, @RequestParam String password) {
        if (username == null || username.trim().length() == 0) {
            return RsData.of("F-3", "username(을)를 입력해주세요.");
        }

        if (password == null || password.trim().length() == 0) {
            return RsData.of("F-4", "password(을)를 입력해주세요.");
        }


        RsData rsData = memberService.tryLogin(username, password);
        if (rsData.isSuccess()) {
            Member member = (Member) rsData.getData();
            rq.setSession("loginMemberId", member.getId());
        }

        return rsData;
    }

    @GetMapping("/member/logout")
    @ResponseBody
    public RsData showLogout() {
        if (rq.canRemoveSession("loginMemberId")) {
            rq.removeSession("loginMemberId");
            return RsData.of("S-1", "로그아웃 되었습니다.");
        }

        return RsData.of("S-2", "이미 로그아웃 되었습니다.");
    }

    @GetMapping("/member/me")
    public String showMe(Model model) {

        long loginMemberId = rq.getSession("loginMemberId");
        Member member = memberService.findById(loginMemberId);
        model.addAttribute("member", member);

        return "login/loginInfo";
    }

}
