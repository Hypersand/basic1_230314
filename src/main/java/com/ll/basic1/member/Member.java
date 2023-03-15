package com.ll.basic1.member;


import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Member {

    private final long id;
    private final String username;
    private final String password;


}
