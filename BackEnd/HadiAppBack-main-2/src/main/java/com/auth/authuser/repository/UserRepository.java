package com.auth.authuser.repository;

import com.auth.authuser.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);
    Optional<User> findById(Long id);

    @Query(value = "select nature_company_forme_juridique from users where id=:userId", nativeQuery = true)
    String getForme(Long userId);

    @Modifying
    @Query(value = "update users set password=:passwordCrp where id=:idUser", nativeQuery = true)
    @Transactional
    void updateUserPassword(String passwordCrp, Long idUser);

    @Modifying
    @Query(value = "update users set nature_company_forme_juridique=:formeJuridique where id=:idUser", nativeQuery = true)
    @Transactional
    void updateForm(String formeJuridique, Long idUser);

}