package com.kpopshop.shareCart.controller;
import com.kpopshop.shareCart.model.ShareCart;
import com.kpopshop.shareCart.service.ShareCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/addShareCartMembers")
public class ShareCartController {

@Autowired
    private ShareCartService Service;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShareCart createMember(@RequestBody ShareCart shareCart){
        return Service.saveShareCart(shareCart);

    }
    @GetMapping
    public List<ShareCart> getMembers(){
        return Service.findAllMembers();
    }
    @GetMapping("/{Id}")
    public ShareCart getMember(@PathVariable String Id){
        return Service.getMemberByID(Id);

    }
    @GetMapping("/firstName/{firstName}")
    public List<ShareCart> findTaskUsingFirstName(@PathVariable String firstName){

        return Service.getMemberByFirstName(firstName);
    }

   @PutMapping
    public ShareCart updateMember(@RequestBody ShareCart shareCart) {
return Service.updateCart(shareCart);
   }

  @DeleteMapping("/{Id}")
    public String deleteMember(@PathVariable String Id) {
return Service.deleteItem(Id);

    }
}
