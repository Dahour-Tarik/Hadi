package com.auth.authuser.repository;

import com.auth.authuser.model.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AdresseRepository extends JpaRepository<Adresse, Long> {

    @Query(value = "select * from adresse where user_id=:id", nativeQuery = true)
    Optional<Adresse> findUserAdresse(Long id);

    @Modifying
    @Query(value = "update adresse set adresse=:adresseP,code_postal=:codePosal,compement_adresse=:compAdresse,ville=:v where id_adresse=:idAdresse", nativeQuery = true)
    @Transactional
    void updateAdresse(String adresseP, String codePosal,String compAdresse, String v, Long idAdresse);

}
