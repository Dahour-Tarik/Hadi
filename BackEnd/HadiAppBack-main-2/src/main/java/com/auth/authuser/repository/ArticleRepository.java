package com.auth.authuser.repository;

import com.auth.authuser.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Long> {

    List<Article> findByCompanyId(@Param("userId") Long userId);
    @Query(value = "select *,0 AS clazz_ from article a join users u on a.company_id=u.id where a.company_id=:userId AND a.designation like %:articleName%",nativeQuery = true)
    List<Article> getArticles(@Param("userId") Long userId, @Param("articleName") String articleName);
    Article findByIdArticle(Long idArticle);
}
