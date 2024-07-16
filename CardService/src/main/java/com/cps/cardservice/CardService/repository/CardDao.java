package com.cps.cardservice.CardService.repository;

import com.cps.cardservice.CardService.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardDao extends JpaRepository<Card, String> {
    List<Card> findByUserId(String userId);
}
