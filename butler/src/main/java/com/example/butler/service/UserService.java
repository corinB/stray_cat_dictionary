package com.example.butler.service;

import com.example.butler.dto.UserDto;
import com.example.butler.entity.entities.UserEntity;
import com.example.butler.repository.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<String> findUserProfileImg(String nick) {
        return userRepository.findUserProfileImg(nick);
    }

    @Transactional
    public boolean emailCheck(String email) {
        var result = userRepository.checkEmail(email);
        return result == null;
    }

    @Transactional
    public boolean singIn(UserDto userDto) {
        var entity = UserEntity.builder().nick(userDto.getNick())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
        try {
            userRepository.save(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public UserDto findMe(String email, String password) {
        var entity = userRepository.findMe(email, password);
        if (entity == null) {
            return null;
        }
        return entityToDto(entity);
    }

    public UserDto entityToDto(UserEntity entity) {
        return UserDto.builder()
                .nick(entity.getNick())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .build();
    }


}
