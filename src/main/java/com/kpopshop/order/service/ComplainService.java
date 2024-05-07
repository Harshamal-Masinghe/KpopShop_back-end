package com.kpopshop.order.service;

import com.kpopshop.order.repository.ComplainRepository;
import com.kpopshop.order.model.ComplainModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ComplainService {

    private final ComplainRepository complainRepository;

    @Autowired
    public ComplainService(ComplainRepository complainRepository) {
        this.complainRepository = complainRepository;
    }


    public List<ComplainModel> getAllComplains() {
        return complainRepository.findAll();
    }

    public ComplainModel saveComplain(ComplainModel complain) {
        return complainRepository.save(complain);
    }

    public ComplainModel updateComplain(String ComplainId, ComplainModel complain) {
        return complainRepository.findById(ComplainId)
                .map(existingComplain -> {
                    existingComplain.setComplainStatus(complain.getComplainStatus());
                    return complainRepository.save(existingComplain);
                }).orElse(null);
    }

    public boolean deleteComplain(String ComplainId) {
        return complainRepository.findById(ComplainId)
                .map(complain -> {
                    complainRepository.delete(complain);
                    return true;
                }).orElse(false);
    }

    public long getTotalComplains() {
        return complainRepository.countBy();
    }

    public long getTotalPendingComplains() {
        return complainRepository.countByComplainStatus("Processing");
    }

    public long getSolvedComplain(){
        return complainRepository.countByComplainStatus("Done");
    }

    public List<ComplainModel> searchComplains(String complainType) {

        if (complainType != null) {
            return complainRepository.findByComplainType(complainType);
        }
        else {
            return complainRepository.findAll();
        }
    }
}
