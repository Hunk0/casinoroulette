package com.clean.casinoroulette.model;

import javax.persistence.*;
import java.util.List;

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
}