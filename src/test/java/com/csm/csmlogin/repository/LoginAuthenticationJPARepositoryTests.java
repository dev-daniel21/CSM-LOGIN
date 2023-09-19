package com.csm.csmlogin.repository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LoginAuthenticationJPARepositoryTests {

    @Autowired
    LoginAuthenticationJPARepository loginAuthenticationJPARepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    void should_correctly_add_new_user_and_persist() {
        // given
        long count = loginAuthenticationJPARepository.count();

        // when
        loginAuthenticationJPARepository.save(new LoginAuthentication(UUID.randomUUID(), "user3", true));

        // then
        long countAgain = loginAuthenticationJPARepository.count();
        assertThat(count).isEqualTo(2);
        assertThat(countAgain).isEqualTo(3);
    }

    @Test
    @Order(2)
    void should_find_two_users_and_add_another() {
        // given
        long count = loginAuthenticationJPARepository.count();

        // when
        loginAuthenticationJPARepository.save(new LoginAuthentication(UUID.randomUUID(), "user4", true));

        // then
        long countAgain = loginAuthenticationJPARepository.count();
        assertThat(count).isEqualTo(3);
        assertThat(countAgain).isEqualTo(4);
    }
    @Test
    @Order(3)
    void should_delete_all_users_and_add_new_one() {
        // given
        loginAuthenticationJPARepository.deleteAll();
        long count = loginAuthenticationJPARepository.count();

        // when
        loginAuthenticationJPARepository.save(new LoginAuthentication(UUID.randomUUID(), "user5", true));

        // then
        long countAgain = loginAuthenticationJPARepository.count();
        assertThat(count).isEqualTo(0);
        assertThat(countAgain).isEqualTo(1);
    }
}
