package com.cps.userservice.UserService.external.services;

import com.cps.userservice.UserService.entities.Card;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface CardService {
    @PostMapping("/api/v1/cards")
    public Card createCard(@RequestBody Card card);
}
