package com.auth.authuser.repository;

import com.auth.authuser.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Long> {

    @Query(value = "select *,0 AS clazz_ from client c where c.company_id=:userId",nativeQuery = true)
    List<Client> findClientByUserId(@Param("userId") Long userId);
    @Query(value = "select *,0 AS clazz_ from client c join users u on c.company_id=u.id where c.company_id=:userId AND c.nom_client like %:clientName%",nativeQuery = true)
    List<Client> getClients(@Param("userId") Long userId, @Param("clientName") String clientName);
}
