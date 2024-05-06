package com.kpopshop.user.service;

import com.kpopshop.user.model.Address;
import com.kpopshop.user.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Address getAddressById(String addressId) {
        Optional<Address> optionalAddress = addressRepository.findById(addressId);
        return optionalAddress.orElse(null);
    }

    public Address updateAddress(String addressId, Address address) {
        address.setAddressId(addressId);
        return addressRepository.save(address);
    }

    public String deleteAddress(String addressID) {
         addressRepository.deleteById(addressID);
        return addressID+"task deleted from dashboard";
        }
}
