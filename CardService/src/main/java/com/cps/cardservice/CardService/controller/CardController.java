package com.cps.cardservice.CardService.controller;

import com.cps.cardservice.CardService.entities.Card;
import com.cps.cardservice.CardService.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/cards")
public class CardController {
    @Autowired
    private CardService cardService;

    @PostMapping
    public ResponseEntity<Card> createCard(@RequestBody Card card) {
        return ResponseEntity.ok(cardService.saveCard(card));
    }

    @GetMapping()
    public ResponseEntity<List<Card>> getAllCards() {
        return ResponseEntity.ok(cardService.getAllCards());
    }

    @GetMapping("/{cardId}")
    public ResponseEntity<Card> getSingleCard(@PathVariable String cardId) {
        Card card = cardService.getCardById(cardId);
        return ResponseEntity.ok(card);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Card>> getCardsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(cardService.getCardsByUserId(userId));
    }


    @PatchMapping("/{cardId}")
    public ResponseEntity<Card> updateCardById(@RequestBody Card card, @PathVariable String cardId) {
        card.setCardId(cardId);
        return ResponseEntity.ok(cardService.updateCardById(card));
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity<Void> deleteCardById(@PathVariable String cardId) {
        cardService.deleteCardById(cardId);
        return ResponseEntity.ok().build();
    }



}
