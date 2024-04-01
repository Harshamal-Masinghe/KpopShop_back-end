package com.kpopshop.giftbox.controller;

import com.kpopshop.giftbox.model.GiftBox;
import com.kpopshop.giftbox.service.GiftBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/giftBox")
public class GiftBoxController {
    @Autowired
    private GiftBoxService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GiftBox createGiftBox(@RequestBody GiftBox giftBox){
        return service.createGiftBox(giftBox);
    }

    @GetMapping
    public List<GiftBox> getGiftBox(){
        return service.findAllGiftBox();
    }

    @GetMapping("/{giftBoxId}")
    public GiftBox getGiftBox(@PathVariable String giftBoxId){
        return service.getGiftBoxbyId(giftBoxId);
    }

    //search by other
    @PutMapping
    public GiftBox modifyGiftBox(@RequestBody GiftBox giftBox){
        return service.updateGiftBox(giftBox);
    }

    @DeleteMapping("/{giftBoxId}")
    public String deleteGiftBox(@PathVariable String giftBoxId){
        return service.deleteGiftBox(giftBoxId);
    }

}
