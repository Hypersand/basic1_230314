package com.ll.basic1.member.repository;

import com.ll.basic1.member.entity.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class MemberRepository {
    private static List<Member> memberList = new ArrayList<>();
    private static long lastId = 0;

    public MemberRepository() {
        memberList.add(new Member(lastId++,"user1", "1234"));
        memberList.add(new Member(lastId++,"abc", "12345"));
        memberList.add(new Member(lastId++,"test", "12346"));
        memberList.add(new Member(lastId++,"love", "12347"));
        memberList.add(new Member(lastId++,"like", "12348"));
        memberList.add(new Member(lastId++,"giving", "12349"));
        memberList.add(new Member(lastId++,"thanks", "123410"));
        memberList.add(new Member(lastId++,"hello", "123411"));
        memberList.add(new Member(lastId++,"good", "123412"));
        memberList.add(new Member(lastId++,"peace", "123413"));
    }

    public Member findByUsername(String username) {
        for (Member member : memberList) {
            if (member.getUsername().equals(username)) {
                return member;
            }
        }
        return null;
    }

    public Member findById(long id) {
        for (Member member : memberList) {
            if (member.getId() == id) {
                return member;
            }
        }
        return null;
    }

}
