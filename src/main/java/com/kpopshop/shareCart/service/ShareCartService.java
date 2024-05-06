package com.kpopshop.shareCart.service;
import com.kpopshop.shareCart.model.ShareCart;
import com.kpopshop.shareCart.repository.ShareCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ShareCartService {

    @Autowired
    private  ShareCartRepository shareCartRepository;

    public ShareCart saveShareCart(ShareCart shareCart) {
        shareCart.setId(UUID.randomUUID().toString().split("-")[0]);
        return shareCartRepository.save(shareCart);
    }
    public List<ShareCart> findAllMembers(){
        return shareCartRepository.findAll();
    }
    public ShareCart getMemberByID(String Id){
        return shareCartRepository.findById(Id).get();

    }
    public List<ShareCart> getMemberByFirstName(String firstName){

        return shareCartRepository.findByFirstName(firstName);
    }
    public ShareCart updateCart(ShareCart itemRequest){
        ShareCart Existingdata =shareCartRepository.findById(itemRequest.getId()).get();
        Existingdata.setFirstName(itemRequest.getFirstName());
        Existingdata.setLastName(itemRequest.getLastName());
        Existingdata.setEmailAddress(itemRequest.getEmailAddress());
        Existingdata.setPhoneNumber(itemRequest.getPhoneNumber());
        Existingdata.setAccessType(itemRequest.getAccessType());
        return shareCartRepository.save(Existingdata);
    }

    public String deleteItem(String Id){
        shareCartRepository.deleteById(Id);
        return Id+ "member deleted from your saved members";
    }

}
