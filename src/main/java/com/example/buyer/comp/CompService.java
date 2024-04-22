package com.example.buyer.comp;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CompService {
    private final CompRepository compRepo;

    @Transactional
    public void save(CompRequest.JoinDTO requestDTO) {
        compRepo.save(requestDTO);

    }

}
