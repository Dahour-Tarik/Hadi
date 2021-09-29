package com.auth.authuser.repository;

import com.auth.authuser.model.Doc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface DocRepository extends JpaRepository<Doc, Integer> {
    @Query(value = "select * from doc d where d.company_id=:company_id",nativeQuery = true)
    List<Doc> findByCompanyId(Long company_id);
    List<Doc> findAll();
    @Query(value = "select * from doc d where d.doc_name=:name", nativeQuery = true)
    Optional<Doc> getFileByName(String name);
    Optional<Doc> findAllByIdDoc(Integer fileId);
    @Modifying
    @Query(value = "update doc set repo_id=:destinationId where id_doc=:idDoc", nativeQuery = true)
    @Transactional
    void updatePathDoc(Long destinationId, Integer idDoc);
    @Query(value = "select * from doc d where d.company_id=:userId", nativeQuery = true)
    List<Doc> findAllByUserId(Long userId);
}
