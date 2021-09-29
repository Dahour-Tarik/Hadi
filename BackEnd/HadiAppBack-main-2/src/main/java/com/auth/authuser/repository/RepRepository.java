package com.auth.authuser.repository;

import com.auth.authuser.model.Repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RepRepository extends JpaRepository<Repo, Long> {

    @Query( value = "select * from repo r where r.user_id=:idUser", nativeQuery = true)
    List<Repo> findByUserId(Long idUser);
    List<Repo> findAll();
    //@Query(value = "select r from Repo r where r.rep_id like ?1", nativeQuery = true)
    //Repo findRepoEnfantsById(Long id);
    @Modifying
    @Query(value = "update repo set parents_rep_id=:newFolderId where id=:folderId", nativeQuery = true)
    @Transactional
    void moveFolder(Long folderId, Long newFolderId);
}
