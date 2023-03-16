package com.ll.basic1.base;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Rq {

    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public void setCookie(Long id) {
        response.addCookie(new Cookie("loginMemberId", id + ""));
    }

    public int getLoginMemberId() {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("loginMemberId")) {
                    return Integer.parseInt(cookie.getValue());
                }

            }
        }
        return -1;
    }

    public void removeCookie(String key) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(key)) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
    }

}
