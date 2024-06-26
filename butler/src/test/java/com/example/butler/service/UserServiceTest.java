package com.example.butler.service;

import com.example.butler.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    void emailCheck() {
        var result = userService.emailCheck("a@a.com");
        System.out.println(result);
    }

    @Test
    void singIn() {
        var userDto = UserDto.builder().email("a@a.com").nick("a").password("a").build();
        var result = userService.singIn(userDto);
        System.out.println(result);
    }


    @Test
    void findMe(){
        var userA = userService.findMe("a@a.com", "a");

        System.out.println(userA);
    }

}