package com.clean.casinoroulette.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

@Entity
@Table(name = "bet")
public class Bet {
    @Id
    @Column()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long betId;
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private String value;
    @Column(nullable = false)
    private Double cash;
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "roulette_id")
    private Roulette roulette;

    public Long getBetId() {
        return betId;
    }

    public void setBetId(Long betId) {
        this.betId = betId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Double getCash() {
        return cash;
    }

    public void setCash(Double cash) {
        this.cash = cash;
    }

    public Roulette getRoulette() {
        return roulette;
    }

    public void setRoulette(Roulette roulette) {
        this.roulette = roulette;
    }
}
