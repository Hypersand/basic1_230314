package com.ll.basic1.base;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;


@AllArgsConstructor
@Component
@RequestScope // 요청이 발생할때마다 Bean 객체가 생성되어 자동으로 주입된다.
public class Rq {

    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public void setCookie(Long id) {
        response.addCookie(new Cookie("loginMemberId", id + ""));
    }

    public int getCookie() {
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

    public boolean canRemoveCookie(String key) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setSession(String name, Long value) {
        HttpSession session = request.getSession();
        session.setAttribute(name, value);
    }

    public long getSession(String name) {
        Long value = (Long)request.getSession().getAttribute(name);
        if (value == null) {
            return -1;
        }

        return value;
    }

    public void removeSession(String name) {
        HttpSession session = request.getSession();
        session.removeAttribute(name);
    }

    public boolean canRemoveSession(String name) {
        HttpSession session = request.getSession();

        if (session.getAttribute(name) == null) {
            return false;
        }

        return true;
    }
}
