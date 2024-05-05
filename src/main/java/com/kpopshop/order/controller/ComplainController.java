package com.kpopshop.order.controller;

import com.kpopshop.order.model.ComplainModel;
import com.kpopshop.order.service.ComplainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/complains")
public class ComplainController {
    private final ComplainService complainService;

    @Autowired
    public ComplainController(ComplainService complainService) {
        this.complainService = complainService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ComplainModel>> getAllComplains() {
        List<ComplainModel> complains = complainService.getAllComplains();
        return new ResponseEntity<>(complains, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<ComplainModel> saveComplain(@RequestBody ComplainModel complain) {
        ComplainModel newComplain = complainService.saveComplain(complain);
        return new ResponseEntity<>(newComplain, HttpStatus.CREATED);
    }

    @PutMapping("/update/{ComplainId}")
    public ResponseEntity<?> updateComplain(@PathVariable String ComplainId, @RequestBody ComplainModel complain) {
        ComplainModel updatedComplain = complainService.updateComplain(ComplainId, complain);
        if (updatedComplain != null) {
            return ResponseEntity.ok(updatedComplain);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{ComplainId}")
    public ResponseEntity<Void> deleteComplain(@PathVariable String ComplainId) {
        boolean isDeleted = complainService.deleteComplain(ComplainId);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/count")
    public long getTotalComplains() {
        return complainService.getTotalComplains();
    }

    @GetMapping("/pending")
    public long getTotalPendingComplains(){
        return complainService.getTotalPendingComplains();
    }

    @GetMapping("/solved")
    public long getSolvedComplain(){
        return complainService.getSolvedComplain();
    }

}
