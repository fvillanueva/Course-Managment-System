package com.fvilla.CourseManagmentSystem.repository;

import com.fvilla.CourseManagmentSystem.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }


    @Test
    void itShouldFindByUsername() {
        //given
        String username = "fvillanueva";
        User user = new User(
                username,
                "pass123",
                null,
                "Facundo",
                "Villanueva",
                "MALE",
                "facundovillanueva97@gmail.com",
                LocalDate.of(1997,11,19),
                "Argentina",
                null
        );
        underTest.save(user);

        //when
        User found = underTest.findByUsername(username);
        boolean expected = found.equals(user);
        //then
        assertThat(expected).isTrue();
    }

    @Test
    void itShouldNotFindByUsername() {
        //given
        String username = "fvillanueva";
        //when
        User found = underTest.findByUsername(username);
        boolean expected = found != null;
        //then
        assertThat(expected).isFalse();
    }
}