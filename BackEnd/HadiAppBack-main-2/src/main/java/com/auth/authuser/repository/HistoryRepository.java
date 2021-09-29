package com.auth.authuser.repository;

import com.auth.authuser.model.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface HistoryRepository extends JpaRepository<History, Long> {
    @Query(value = "select * from history h where h.user_id=:userId",nativeQuery = true)
    List<History> findAllByUserId(Long userId);
    Optional<History> findById(Long id);
    @Modifying
    @Query(value = "insert into history (change_date, media_extension, media_name, media_size, media_state, media_type,user_id,doc_id_doc,repo_id) VALUES (:date,:docType,:docName,:docSize,:state,:docType,:userId,:idDoc,:repId)", nativeQuery = true)
    @Transactional
    void addCreateHistory(@Param("idDoc") Integer idDoc, @Param("docName") String docName,@Param("docSize") Long docSize, @Param("docType") String docType, @Param("repId") Long repId,@Param("userId") Long userId, @Param("state") String state,@Param("date") Date date);
    @Modifying
    @Query(value = "insert into history (change_date, media_extension, media_name, media_size, media_state, media_type,user_id,repo_id) VALUES (:date,:docType,:docName,:docSize,:state,:docType,:userId,:repId)", nativeQuery = true)
    @Transactional
    void addDeleteHistory( @Param("docName") String docName,@Param("docSize") Long docSize, @Param("docType") String docType, @Param("repId") Long repId,@Param("userId") Long userId, @Param("state") String state,@Param("date") Date date);
}
