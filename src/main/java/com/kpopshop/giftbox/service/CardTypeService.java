package com.kpopshop.giftbox.service;

import com.kpopshop.giftbox.model.CardType;
import com.kpopshop.giftbox.reposotory.CardTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardTypeService {
    @Autowired
    private CardTypeRepository repository;

    public List<CardType> findAllCardTypes(){
        return repository.findAll();
    }

    public CardType getCardTypebyId(String BoxColorId){
        return repository.findById(BoxColorId).get();
    }

}
