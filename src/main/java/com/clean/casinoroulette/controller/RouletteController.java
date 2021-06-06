package com.clean.casinoroulette.controller;

import com.clean.casinoroulette.model.Roulette;
import com.clean.casinoroulette.repository.BetRepository;
import com.clean.casinoroulette.repository.RouletteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roulettes")
public class RouletteController {
    @Autowired
    RouletteRepository rouletteRepository;

    @Autowired
    BetRepository betReRepository;

    @GetMapping()
    public ResponseEntity<String> testFunction() {
        return new ResponseEntity<>("It works!", HttpStatus.OK);
    }
}
