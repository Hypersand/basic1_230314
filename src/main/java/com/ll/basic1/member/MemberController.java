package com.ll.basic1.member;

import com.ll.basic1.base.Message;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @GetMapping("/member/login")
    @ResponseBody
    public Message showLogin(@RequestParam String username, @RequestParam String password) {
        return memberService.tryLogin(username, password);
    }


}
