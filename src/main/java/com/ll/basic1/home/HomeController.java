package com.ll.basic1;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


@Controller
public class HomeController {

    private static int count = 0;
    private static List<Person> personList = new ArrayList<>();

    @GetMapping("/home/main")
    @ResponseBody // 리턴값을 응답으로 삼는다.
    public String showMain() {
        return "안녕하세요!";
    }

    @GetMapping("/home/main2")
    @ResponseBody
    public String showMain2() {
        return "반갑습니다.";
    }

    @GetMapping("/home/main3")
    @ResponseBody
    public String showMain3() {
        return "즐거웠습니다.";
    }

    @GetMapping("/home/increase")
    @ResponseBody
    public String showIncrease() {
        return "응답 : " + count++;
    }

    @GetMapping("/home/plus")
    @ResponseBody
    public String showPlus(@RequestParam int a, @RequestParam int b) {
        int sum = a + b;
        return "a + b = " + sum;
    }


    @GetMapping("/home/addPerson")
    @ResponseBody
    public String addPerson(@RequestParam String name, @RequestParam int age) {
        Person person = new Person(++count, name, age);
        personList.add(person);

        return count + "번 사람이 추가되었습니다.";
    }

    @GetMapping("/home/people")
    @ResponseBody
    public List<Person> listPerson() {
        return personList;
    }

    @GetMapping("/home/removePerson")
    @ResponseBody
    public String removeList(@RequestParam int id) {
        Person person = findById(id);
        if (person == null) {
            return "응답 : " + id + "번 사람이 존재하지 않습니다.";
        }

        personList.remove(person);

        return "응답 : " + id + "번 사람이 삭제되었습니다.";
    }

    @GetMapping("/home/modifyPerson")
    @ResponseBody
    public String modifyList(@RequestParam int id, @RequestParam String name, @RequestParam int age) {
        Person person = findById(id);
        if (person == null) {
            return "응답 : " + id + "번 사람이 존재하지 않습니다.";
        }

        person.setName(name);
        person.setAge(age);

        return "응답 : " + id + "번 사람이 수정되었습니다.";
    }

    @GetMapping("/home/cookie/increase")
    @ResponseBody
    public String showCookieCount(HttpServletRequest request, HttpServletResponse response) {
        int cookieCount = 0;

        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("count")) {
                    cookieCount = Integer.parseInt(cookie.getValue());
                }
            }
        }

        int newCookieCount = cookieCount + 1;
        response.addCookie(new Cookie("count", newCookieCount + ""));

        return "쿠키 count : " + newCookieCount;
    }


    @GetMapping("/member/login")
    @ResponseBody
    public Message showLogin(@RequestParam String username, @RequestParam String password) {

        if (username.equals("user1") && password.equals("1234")) {
            return new Message("S-1", username + "님 환영합니다.");
        }

        if (username.equals("user1")) {
            return new Message("F-1", "비밀번호가 일치하지 않습니다.");
        }

        return new Message("F-2", username + "(은)는 존재하지 않는 회원입니다.");
    }

    private static Person findById(int id) {
        for (Person person : personList) {
            if (person.id == id) {
                return person;
            }
        }
        return null;
    }

    @AllArgsConstructor
    @Data
    private static class Person {
        private int id;
        private String name;
        private int age;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    private static class Message {
        private String resultCode;
        private String msg;
    }


}

