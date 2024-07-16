package com.cps.cardservice.CardService.service;

import com.cps.cardservice.CardService.entities.Card;
import com.cps.cardservice.CardService.entities.Transaction;
import com.cps.cardservice.CardService.exceptions.ResourceNotFoundException;
import com.cps.cardservice.CardService.repository.CardDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    private CardDao cardDao;

    @Autowired
    private RestTemplate restTemplate;

    private final String userServiceBaseUrl = "http://host.docker.internal:8081/api/v1/users/";
    public Card saveCard(Card card) {
        String randomCardId = UUID.randomUUID().toString();
        card.setCardId(randomCardId);
        return this.cardDao.save(card);
    }

    public List<Card> getAllCards() {
        if(!cardDao.findAll().isEmpty()){
            return cardDao.findAll();
        } else{
            throw new ResourceNotFoundException("No cards found on server!");
        }
    }

    public List<Card> getCardsByUserId(String userId) {
        List<Card> cardList = cardDao.findByUserId(userId);
        for(Card card: cardList) {
            ArrayList<Transaction> transactions = restTemplate.getForObject("http://host.docker.internal:8083/api/v1/transactions/cards/" + card.getCardId(), ArrayList.class);
            card.setTransactions(transactions);
        }

        return cardList;
    }

    public Card getCardById(String cardId) {
        Card card = cardDao.findById(cardId).orElseThrow(() -> new ResourceNotFoundException("Card not found with id: " + cardId));
        ArrayList<Transaction> transactions = restTemplate.getForObject("http://host.docker.internal:8083/api/v1/transactions/cards/" + cardId, ArrayList.class);
        card.setTransactions(transactions);
        return card;
    }

    public Card updateCardById(Card card) {
        return cardDao.save(card);
    }

    public void deleteCardById(String cardId) {
        cardDao.deleteById(cardId);
    }


}
