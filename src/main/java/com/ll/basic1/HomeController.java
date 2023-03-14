package com.ll.basic1;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
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
    public int showCookieIncrease(HttpServletRequest request, HttpServletResponse response) {
        int countCookie = 0;

        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("count")) {
                    countCookie = Integer.parseInt(cookie.getValue());
                }
            }
        }

        int newCountCookie = countCookie + 1;

        response.addCookie(new Cookie("count", newCountCookie + ""));

        return newCountCookie;
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

}

