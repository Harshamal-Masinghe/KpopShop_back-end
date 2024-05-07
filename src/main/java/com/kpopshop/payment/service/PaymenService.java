package com.kpopshop.payment.service;



import com.kpopshop.payment.model.PaymentModel;
import com.kpopshop.payment.reposotory.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymenService {

    private final PaymentRepository paymentRepository;


    @Autowired
    public PaymenService(PaymentRepository  paymentRepository) {
       this.paymentRepository = paymentRepository ;
    }

    public List<PaymentModel> getAllPayments(){
        return paymentRepository.findAll();
    }

    public PaymentModel savePayment(PaymentModel payment){
        return paymentRepository.save(payment);
    }



   /* public PaymentModel findPaymentByPaymentId(String paymentId) {
        return paymentRepository.findByPaymentId(paymentId);
    }*/
}
