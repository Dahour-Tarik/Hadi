package com.auth.authuser.repository;

import com.auth.authuser.model.Catalogue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CatalogueRepository extends JpaRepository<Catalogue,Long> {
    @Modifying
    @Query(value="update catalogue set nb_article=nb_article+1 where id_catalogue=:idCatalogue", nativeQuery = true)
    @Transactional
    void increaseNbArticle(@Param("idCatalogue") Long idCatalogue);
    @Modifying
    @Query(value="update catalogue set nb_article=nb_article-1 where id_catalogue=:idCatalogue", nativeQuery = true)
    @Transactional
    void decreaseNbArticle(@Param("idCatalogue") Long idCatalogue);
}
