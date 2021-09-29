package com.auth.authuser.repository;

import com.auth.authuser.model.ClientDoc;
import com.auth.authuser.model.FactureRecurrente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FactureRecRepository extends JpaRepository<FactureRecurrente, String> {

    List<ClientDoc> findByCompanyId(@Param("userId") Long userId);
    @Query(value = "select *,0 AS clazz_ from client_doc c join users u on c.company_id=u.id where c.company_id=:userId AND c.nom_client_doc like %:docClientName%",nativeQuery = true)
    List<ClientDoc> getDocClient(@Param("userId") Long userId, @Param("docClientName") String docClientName);

}
