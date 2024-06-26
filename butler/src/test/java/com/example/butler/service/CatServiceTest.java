package com.example.butler.service;

import com.example.butler.dto.CatDto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CatServiceTest {
    @Autowired
    CatService catService;

    @Test
    @Rollback(value = false)
    @Transactional
    void saveCat() {
        List<String> catImgList = List.of("img1", "img2", "img3");

        var catDto = CatDto.builder()
                .name("Cat Test")
                .des("Test Cat Des")
                .userId(1L)
                .catImgList(catImgList)
                .build();
        String res = catService.saveCat(catDto);
        System.out.println(res);
    }

}