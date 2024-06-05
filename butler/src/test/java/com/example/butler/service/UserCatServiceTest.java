package com.example.butler.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserCatServiceTest {
    @Autowired
    private CatService userCatService;


    @Test
    void findByNick() {
        var userCats = userCatService.findByNick("kim");

    }


}