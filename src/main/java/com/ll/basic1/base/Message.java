package com.ll.basic1.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class Message {
    private final String resultCode;
    private final String msg;
}