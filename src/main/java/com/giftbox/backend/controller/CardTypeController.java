package com.giftbox.backend.controller;

import com.giftbox.backend.model.CardType;
import com.giftbox.backend.service.CardTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/cardType")
public class CardTypeController {
    @Autowired
    private CardTypeService service;

    @GetMapping
    public List<CardType> getCardType(){
        return service.findAllCardTypes();
    }

    @GetMapping("/{CardId}")
    public CardType getGiftBoxColor(@PathVariable String CardId){
        return service.getCardTypebyId(CardId);
    }
}
