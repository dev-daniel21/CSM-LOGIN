package com.csm.csmlogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginAuthenticationJPARepository extends JpaRepository<LoginAuthentication, Long> {

    Optional<LoginAuthentication> findByLogin(String login);

    boolean existsByLogin(String userLogin);

    void deleteByLogin(String userLogin);

    @Modifying
    @Query("UPDATE LoginAuthentication a SET a.login = :newLogin WHERE a.login= :userLogin")
    void updateUser(String userLogin, String newLogin);

}
