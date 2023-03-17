package com.ll.basic1.member.repository;

import com.ll.basic1.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

    Optional<Member> findByUsername(String username);

    Optional<Member> findById(long id);
}
