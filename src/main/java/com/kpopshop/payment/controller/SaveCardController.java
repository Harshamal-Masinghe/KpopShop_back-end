package com.kpopshop.payment.controller;

import com.kpopshop.payment.model.PaymentModel;
import com.kpopshop.payment.model.SaveCardModel;
import com.kpopshop.payment.reposotory.SaveCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class SaveCardController {

    @Autowired
    private SaveCardRepository saveCardRepository;

    @GetMapping("/savecard")
    public ResponseEntity<?> getAllSaveCard(){
        List<SaveCardModel> savecard= saveCardRepository.findAll();
        if(savecard.size()>0){
            return new ResponseEntity<List<SaveCardModel>>(savecard, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("No save card available", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/savecard")
    public ResponseEntity<?> saveCardDetails(@RequestBody SaveCardModel saveCard) {
        try {
            saveCard.setCardNumber(UUID.randomUUID().toString());
            saveCardRepository.save(saveCard);
            return new ResponseEntity<>("Card details saved successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/savecard/{id}")
    public ResponseEntity<?> getSingleSaveCard(@PathVariable("id") String id){
        Optional<SaveCardModel> saveCardOptional = saveCardRepository.findById(id);
        if (saveCardOptional.isPresent()){
            return new ResponseEntity<>(saveCardOptional.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Save card not found with id" + id, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/savecard/{id}")
    public ResponseEntity<?> updateSaveCardById(@PathVariable("id") String id, @RequestBody SaveCardModel saveCard){
        Optional<SaveCardModel> saveCardOptional = saveCardRepository.findById(id);
        if (saveCardOptional.isPresent()){
            SaveCardModel saveCardSave = saveCardOptional.get();
            // Update the fields as needed
            saveCardSave.setCardNumber(saveCard.getCardNumber());
            saveCardSave.setCardHolderName(saveCard.getCardHolderName());
            saveCardSave.setExpirationDate(saveCard.getExpirationDate());
            saveCardSave.setCvv(saveCard.getCvv());
            saveCardRepository.save(saveCardSave);
            return new ResponseEntity<>(saveCardSave, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Save card not found with id" + id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/savecard/{id}")
    public ResponseEntity<?> deleteSaveCardById(@PathVariable("id") String id){
        try {
            saveCardRepository.deleteById(id);
            return new ResponseEntity<>("Successfully deleted save card with id" + id, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
