package com.kpopshop.giftbox.controller;


import com.kpopshop.giftbox.model.GiftBoxColor;
import com.kpopshop.giftbox.service.GiftBoxColorService;
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

    @GetMapping("/{boxColorId}")
    public GiftBoxColor getGiftBoxColorbyId(@PathVariable String boxColorId) {
        return service.getGiftBoxColorbyId(boxColorId);
    }

}
