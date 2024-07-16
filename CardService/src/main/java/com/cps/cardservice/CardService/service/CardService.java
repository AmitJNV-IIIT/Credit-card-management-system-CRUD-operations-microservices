package com.cps.cardservice.CardService.service;

import com.cps.cardservice.CardService.entities.Card;

import java.util.List;

public interface CardService {
    public Card getCardById(String id);
    public List<Card> getAllCards();
    public Card saveCard(Card user);
    public Card updateCardById(Card card);
    public void deleteCardById(String id);
    public List<Card> getCardsByUserId(String userId);
}
