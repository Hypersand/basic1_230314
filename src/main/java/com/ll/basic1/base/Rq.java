package com.ll.basic1.base;

import com.ll.basic1.member.entity.Member;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Rq {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public Rq(HttpServletRequest request) {
        this.request = request;
    }

    public Rq(HttpServletResponse response) {
        this.response = response;
    }

    public void setCookie(Long id) {
        response.addCookie(new Cookie("loginMemberId", id + ""));
    }

    public String getLoginMemberId() {

        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("loginMemberId")) {
                    return cookie.getValue();
                }

            }
        }

        return null;
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
