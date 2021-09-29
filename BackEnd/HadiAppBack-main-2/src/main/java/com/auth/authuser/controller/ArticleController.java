package com.auth.authuser.controller;

import com.auth.authuser.model.Article;
import com.auth.authuser.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/articles")
public class ArticleController {


    @Autowired
    private ArticleService articleService;

    @GetMapping("/{userId}/{nomArticle}")
    public ResponseEntity<List<Article>> getArticles(@PathVariable Long userId, @PathVariable String nomArticle){
        List<Article> articleList = null;
        if(nomArticle != null){
            articleList = articleService.getArticles(userId,nomArticle);
        }
        else{
            articleList = articleService.getAll(userId);
        }
        return new ResponseEntity<>(articleList, HttpStatus.OK);
    }

    @PostMapping("/addArticle")
    public ResponseEntity<Article> addArticle(@RequestBody Article article){
        Article article1 = articleService.addArticle(article);
        articleService.increaseNbArticle(article.getCatalogue().getIdCatalogue());
        return new ResponseEntity<>(article1, HttpStatus.CREATED);
    }

    @PutMapping("/updateArticle")
    public ResponseEntity<Article> updateClient(@RequestBody Article article){
        Article article1 = articleService.updateArticle(article);
        return new ResponseEntity<>(article1, HttpStatus.OK);
    }

    @DeleteMapping("/deleteArticle/{articleId}")
    public void deleteClient(@PathVariable Long articleId){
        Article article = articleService.getArticle(articleId);
        articleService.deleteArticle(articleId);
        articleService.decreaseNbArticle(article.getCatalogue().getIdCatalogue());
    }

}
