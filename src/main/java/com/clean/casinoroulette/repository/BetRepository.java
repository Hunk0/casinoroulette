package com.clean.casinoroulette.repository;

import com.clean.casinoroulette.model.Bet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BetRepository extends JpaRepository<Bet, Long> {}