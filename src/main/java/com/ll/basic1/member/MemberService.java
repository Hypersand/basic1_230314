package com.ll.basic1.member;

import com.ll.basic1.base.RsData;
import org.springframework.stereotype.Service;

@Service
public class MemberService {


    public RsData tryLogin(String username, String password) {

        if (username.equals("user1") && password.equals("1234")) {
            return new RsData("S-1", username + "님 환영합니다.");
        }

        if (username.equals("user1")) {
            return new RsData("F-1", "비밀번호가 일치하지 않습니다.");
        }


        return new RsData("F-2", username + "(은)는 존재하지 않는 회원입니다.");
    }
}
