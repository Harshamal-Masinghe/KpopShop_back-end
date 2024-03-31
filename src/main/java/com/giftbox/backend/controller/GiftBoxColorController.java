package com.giftbox.backend.controller;


import com.giftbox.backend.model.GiftBoxColor;
import com.giftbox.backend.service.GiftBoxColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/giftBoxColor")
public class GiftBoxColorController {
    @Autowired
    private GiftBoxColorService service;

    @GetMapping
    public List<GiftBoxColor> getGiftBox(){
        return service.findAllGiftBoxColor();
    }

    @GetMapping("/{giftBoxId}")
    public GiftBoxColor getGiftBoxColor(@PathVariable String giftBoxId){
        return service.getGiftBoxColorbyId(giftBoxId);
    }
}
