package com.example.butler.service;

import com.example.butler.dto.CatDto;
import com.example.butler.entity.BaseCatEntity;
import com.example.butler.repository.PetCatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CatService {
    private final PetCatRepository petCatRepository;

    public List<CatDto> findByUserIdx(Long userIdx) {
        var userCats = petCatRepository.findByUserIdx(userIdx);
        return userCats.stream().map(this::toDto).toList();
    }

    public List<CatDto> findByNick(String nick) {
        var userCats = petCatRepository.findByNick(nick);
        return userCats.stream().map(this::toDto).toList();
    }

    private CatDto toDto(BaseCatEntity cat) {
        return CatDto.builder()
                .name(cat.getName())
                .des(cat.getDes())
                .build();
    }

}
