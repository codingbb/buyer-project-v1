package com.example.buyer.comp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CompService {
    private final CompRepository compRepo;

}
