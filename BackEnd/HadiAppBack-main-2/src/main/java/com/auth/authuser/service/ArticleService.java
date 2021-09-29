package com.auth.authuser.service;

import com.auth.authuser.model.Article;
import com.auth.authuser.repository.ArticleRepository;
import com.auth.authuser.repository.CatalogueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CatalogueRepository catalogueRepository;

    public List<Article> getArticles(Long idUser, String articleName){
        return articleRepository.getArticles(idUser,articleName);
    }

    public List<Article> getAll(Long idUser){
        return articleRepository.findByCompanyId(idUser);
    }


    public Article addArticle(Article article){
        return articleRepository.save(article);
    }

    public Article updateArticle(Article article){
        return articleRepository.save(article);
    }

    public void deleteArticle(Long id){
        boolean exist = articleRepository.existsById(id);
        if(!exist){
            throw new IllegalStateException("Folder with id = "+id+" does not exist");
        }
        articleRepository.deleteById(id);
    }

    public void increaseNbArticle(Long idCatalogue){
        catalogueRepository.increaseNbArticle(idCatalogue);
    }

    public Article getArticle(Long idArticle){
        return articleRepository.findByIdArticle(idArticle);
    }

    public void decreaseNbArticle(Long idCatalogue){
        catalogueRepository.decreaseNbArticle(idCatalogue);
    }

}
