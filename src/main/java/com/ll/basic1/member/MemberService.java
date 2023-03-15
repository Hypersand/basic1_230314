package com.ll.basic1.member;

import com.ll.basic1.base.RsData;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public RsData tryLogin(String username, String password) {


        Member member = memberRepository.findByUsername(username);

        if (member == null) {
            return new RsData("F-2", username + "(은)는 존재하지 않는 회원입니다.");
        }

        if (!member.getPassword().equals(password)) {
            return new RsData("F-1", "비밀번호가 일치하지 않습니다.");
        }

        return new RsData("S-1", username + "님 환영합니다.");

    }
}
