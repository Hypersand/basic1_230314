package com.ll.basic1.base;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class RsData {
    private final String resultCode;
    private final String msg;
}