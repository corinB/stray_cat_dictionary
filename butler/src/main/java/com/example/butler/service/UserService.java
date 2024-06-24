package com.example.butler.service;

import com.example.butler.repository.repositories.UserRepository;
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


}
