package com.boteam.sou2sawda.controller;

import com.boteam.sou2sawda.model.FinalResponse;
import com.boteam.sou2sawda.model.RateResponse;
import com.boteam.sou2sawda.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rate")
public class RateController {
    @Autowired
    RateService rateService;


    @PostMapping("/fetch")
    ResponseEntity<Object> fetch() {
        FinalResponse fr = rateService.fetch();
            return new ResponseEntity<Object>(fr, fr.getErrorCode());

    }

}
