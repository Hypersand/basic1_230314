package com.ll.basic1;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HomeController {

    private static int count = 0;

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
        return "a + b = " + String.valueOf(sum);
    }

}
