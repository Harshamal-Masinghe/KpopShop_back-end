package com.kpopshop.user.controller;

import com.kpopshop.user.model.Address;
import com.kpopshop.user.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Address createAddress(@RequestBody Address address) {
        return addressService.createAddress(address);
    }

    @GetMapping
    public List<Address> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    @GetMapping("/{addressId}")
    public Address getAddressById(@PathVariable String addressId) {
        return addressService.getAddressById(addressId);
    }

    @PutMapping("/addresses/{addressId}")
    public Address updateAddress(@PathVariable String addressId, @RequestBody Address address) {
        return addressService.updateAddress(addressId, address);
    }

    @DeleteMapping("/address/{addressID}")
    public String deleteAddress(@PathVariable String addressID){
    return addressService.deleteAddress(addressID);
    }
}
