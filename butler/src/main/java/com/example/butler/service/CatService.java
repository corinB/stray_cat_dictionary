package com.example.butler.service;

import com.example.butler.dto.CatDto;
import com.example.butler.entity.entities.PetCatEntity;
import com.example.butler.entity.entities.PetCatImgEntity;
import com.example.butler.entity.entities.UserEntity;
import com.example.butler.repository.repositories.PetCatImgRepository;
import com.example.butler.repository.repositories.PetCatRepository;
import com.example.butler.repository.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CatService {

    private final PetCatRepository petCatRepository;
    private final PetCatImgRepository petCatImgRepository;
    private final UserRepository userRepository;

    @Transactional
    public String saveCat(CatDto catDto) {
        userRepository.findById(catDto.getUserId()).ifPresent(userEntity -> {
            var petCatEntity =  petDtoToEntity(userEntity, catDto);
            catDto.getCatImgList().forEach(catImg -> {
                petCatEntity.addPetCatImg(petCatImgDtoToEntity(petCatEntity, catImg));
            });
            petCatRepository.save(petCatEntity);
        });
        return "success";
    }

    private PetCatEntity petDtoToEntity(UserEntity userEntity, CatDto catDto) {
        return PetCatEntity.builder()
                .name(catDto.getName())
                .des(catDto.getDes())
                .userEntity(userEntity)
                .build();
    }

    private PetCatImgEntity petCatImgDtoToEntity(PetCatEntity petCatEntity, String imgPath) {
        return PetCatImgEntity.builder()
                .path(imgPath)
                .petCatEntity(petCatEntity)
                .build();
    }






}
