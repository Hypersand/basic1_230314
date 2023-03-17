package com.ll.basic1.member.service;

import com.ll.basic1.base.RsData;
import com.ll.basic1.member.entity.Member;
import com.ll.basic1.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    public RsData tryLogin(String username, String password) {

        Member member = memberRepository.findByUsername(username).orElse(null);

        if (member == null) {
            return RsData.of("F-2", username + "(은)는 존재하지 않는 회원입니다.");
        }

        if (!member.getPassword().equals(password)) {
            return RsData.of("F-1", "비밀번호가 일치하지 않습니다.");
        }

        return RsData.of("S-1", username + "님 환영합니다.", member);

    }


    public Member findById(long id) {
        Member member = memberRepository.findById(id).orElse(null);

        return member;
    }

}
