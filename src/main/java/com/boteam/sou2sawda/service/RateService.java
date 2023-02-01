package com.boteam.sou2sawda.service;

import com.boteam.sou2sawda.model.FinalResponse;
import com.boteam.sou2sawda.model.RateResponse;
import org.springframework.http.ResponseEntity;

public interface RateService {
    FinalResponse fetch();
}
