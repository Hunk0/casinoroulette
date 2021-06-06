package com.clean.casinoroulette.model;

import javax.persistence.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@Table(name = "roulette")
public class Roulette {
    @Id
    @Column()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rouletteId;
    @Column(nullable = false, columnDefinition = "BIT default 0")
    private Boolean open;
    @OneToMany(mappedBy = "roulette", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Bet> bets;

    public Roulette() {
        this.open = false;
        this.bets = List.of();
    }
    public Long getRouletteId() {
        return rouletteId;
    }
    public void setRouletteId(Long rouletteId) {
        this.rouletteId = rouletteId;
    }
    public Boolean getOpen() {
        return open;
    }
    public void setOpen(Boolean open) {
        this.open = open;
    }
    public List<Bet> getBets() {
        return bets;
    }
    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }

    public String spinRoulette() {
        int winnerNumber = ThreadLocalRandom.current().nextInt(0, 37);
        String winnerColor = (winnerNumber % 2 == 0) ? "rojo" : "negro";
        for(Bet bet: this.bets) {
            Boolean numberBet = !bet.getValue().replaceAll("[^\\d]", "").equals("");
            if(numberBet && Integer.parseInt(bet.getValue()) == winnerNumber){
                bet.setCash(bet.getCash() * 5);
            }else if(bet.getValue().equals(winnerColor)){
                bet.setCash(bet.getCash() * 1.8);
            }else{
                bet.setCash(0.0);
            }
        }

        return "El numero ganador ha sido el " + winnerNumber + "(color " + winnerColor + "), gracias por participar!";
    }
}