package com.kpopshop.giftbox.controller;

import com.kpopshop.giftbox.model.GiftBox;
import com.kpopshop.giftbox.service.GiftBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
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

    @GetMapping("/update/get")
    public ResponseEntity<?> getGiftBoxByIdForUpdate(@RequestParam String id) {
        System.out.println("Received request for gift box ID: " + id);
        GiftBox giftBox = service.getGiftBoxbyId(id);

        if (giftBox != null) {
            return ResponseEntity.ok(giftBox);
        } else {
            return ResponseEntity.notFound().build();

        }
    }

    @PutMapping("/{giftBoxId}")
    public ResponseEntity<GiftBox> modifyGiftBox(@RequestBody GiftBox giftBox) {
        // Logic to update the gift box using the provided data (giftBox)
        GiftBox updatedBox = service.updateGiftBox(giftBox);
        return ResponseEntity.ok(updatedBox);
    }



    /*search by other
    @PutMapping
    public GiftBox modifyGiftBox(@RequestBody GiftBox giftBox){
        return service.updateGiftBox(giftBox);
    }*/

    @DeleteMapping("/{giftBoxId}")
    public String deleteGiftBox(@PathVariable String giftBoxId){
        return service.deleteGiftBox(giftBoxId);
    }

}
