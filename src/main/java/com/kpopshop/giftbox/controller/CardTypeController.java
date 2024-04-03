package com.kpopshop.giftbox.controller;

import com.kpopshop.giftbox.model.CardType;
import com.kpopshop.giftbox.service.CardTypeService;
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
