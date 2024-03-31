package com.giftbox.backend.service;


import com.giftbox.backend.model.GiftBoxColor;
import com.giftbox.backend.reposotory.GiftBoxColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiftBoxColorService {
    @Autowired
    private GiftBoxColorRepository repository;

    public List<GiftBoxColor> findAllGiftBoxColor(){
        return repository.findAll();
    }

    public GiftBoxColor getGiftBoxColorbyId(String BoxColorId){
        return repository.findById(BoxColorId).get();
    }

}
