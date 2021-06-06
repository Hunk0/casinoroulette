package com.clean.casinoroulette.controller;

import com.clean.casinoroulette.model.Bet;
import com.clean.casinoroulette.model.Roulette;
import com.clean.casinoroulette.repository.BetRepository;
import com.clean.casinoroulette.repository.RouletteRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/roulettes")
public class RouletteController {
    @Autowired
    RouletteRepository rouletteRepository;
    @Autowired
    BetRepository betReRepository;

    @GetMapping("")
    public List<Roulette> getAllRoulettes() {
        return rouletteRepository.findAll();
    }

    @PostMapping("/newroulette")
    public ResponseEntity<Object> createRoulette() {
        return new ResponseEntity<>(rouletteRepository.save(new Roulette()), HttpStatus.CREATED);
    }

    @PostMapping("/{id}/bet")
    public ResponseEntity<Object> doBet(@RequestHeader(value = "userId") Long userId, @PathVariable(value = "id") Long id, @RequestBody Bet bet) throws ConfigDataResourceNotFoundException {
        Optional<Roulette> rouletteOptional = Optional.ofNullable(rouletteRepository.getById(id));
        if(rouletteOptional.isPresent()){
            if(rouletteOptional.get().getOpen()){
                Boolean numberBet = !bet.getValue().replaceAll("[^\\d]", "").equals("");
                bet.setRoulette(rouletteOptional.get());
                bet.setUserId(userId);
                if((numberBet) ? (Integer.parseInt(bet.getValue()) < 0 || Integer.parseInt(bet.getValue()) > 36) : !(bet.getValue().equals("negro") || bet.getValue().equals("rojo")))
                    return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body("{\"message\": \"Esta apuesta no es valida\"}");

                return new ResponseEntity<>(betReRepository.save(bet), HttpStatus.CREATED);
            }

            return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body("{\"message\": \"Esta ruleta no acepta apuestas actualmente\"}");
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/open")
    public ResponseEntity<Object> openRoulette(@PathVariable(value = "id") Long id) throws ConfigDataResourceNotFoundException {
        Optional<Roulette> rouletteOptional = Optional.ofNullable(rouletteRepository.getById(id));
        if(rouletteOptional.isPresent()){
            if(rouletteOptional.get().getOpen()) return ResponseEntity.status(409).contentType(MediaType.APPLICATION_JSON).body("{\"message\": \"Esta ruleta ya se encuentra abierta\"}");
            rouletteOptional.get().setOpen(true);
            rouletteRepository.save(rouletteOptional.get());

            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("{\"message\": \"La ruleta esta abierta, hagan sus apuestas!\"}");
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/close")
    public ResponseEntity<Object> closeRoulette(@PathVariable(value = "id") Long id) throws ConfigDataResourceNotFoundException, JsonProcessingException {
        Optional<Roulette> rouletteOptional = rouletteRepository.findById(id);
        if(rouletteOptional.isPresent()){
            if(!rouletteOptional.get().getOpen()) return ResponseEntity.status(409).contentType(MediaType.APPLICATION_JSON).body("{\"message\": \"Esta ruleta ya se encuentra cerrada\"}");
            rouletteOptional.get().setOpen(false);
            rouletteRepository.save(rouletteOptional.get());

            return ResponseEntity
                    .ok().contentType(MediaType.APPLICATION_JSON)
                    .body("{\"message\": " + "\"La ruleta se ha cerrado... " + rouletteOptional.get().spinRoulette() + "\","+
                            "\"results\": " + new ObjectMapper().writeValueAsString(rouletteOptional.get().getBets()) + "}");
        }

        return ResponseEntity.notFound().build();
    }
}
