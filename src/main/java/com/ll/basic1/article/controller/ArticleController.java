package com.ll.basic1.article.controller;

import com.ll.basic1.article.entity.Article;
import com.ll.basic1.article.repository.ArticleRepository;
import com.ll.basic1.article.service.ArticleService;
import com.ll.basic1.base.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/write")
    @ResponseBody
    public RsData write(@RequestParam String title, @RequestParam String body) {

        Article article = articleService.write(title, body);

        return RsData.of("S-1", "1번 글이 생성되었습니다.", article);
    }


}
