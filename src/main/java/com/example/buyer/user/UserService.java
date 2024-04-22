package com.example.buyer.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepo;

    //회원가입
    @Transactional
    public void save(UserRequest.JoinDTO requestDTO) {
        userRepo.save(requestDTO);
    }

}
