package com.kpopshop.giftbox.service;


import com.kpopshop.giftbox.model.GiftBoxColor;
import com.kpopshop.giftbox.reposotory.GiftBoxColorRepository;
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

    public GiftBoxColor getGiftBoxColorbyId(String boxColorId){
        return repository.findById(boxColorId).orElse(null);
    }



}
