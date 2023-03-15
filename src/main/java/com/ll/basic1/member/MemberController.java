package com.ll.basic1.member;

import com.ll.basic1.base.RsData;
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
    public RsData showLogin(@RequestParam String username, @RequestParam String password) {
        if ( username == null || username.trim().length() == 0 ) {
            return new RsData("F-3", "username(을)를 입력해주세요.");
        }

        if ( password == null || password.trim().length() == 0 ) {
            return new RsData("F-4", "password(을)를 입력해주세요.");
        }

        return memberService.tryLogin(username, password);
    }


}
