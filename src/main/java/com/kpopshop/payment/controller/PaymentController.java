package com.kpopshop.payment.controller;



import com.kpopshop.payment.model.PaymentModel;
import com.kpopshop.payment.service.PaymenService;
import com.kpopshop.payment.reposotory.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")

public class PaymentController {

    @Autowired
    private  PaymentRepository PaymentRepo;

    //new codes
    @GetMapping("/payment")
    public ResponseEntity<?> getAllPayment(){
        List<PaymentModel> payment= PaymentRepo.findAll();
        if(payment.size()>0){
            return new ResponseEntity<List<PaymentModel>>(payment, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("No payment available", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/payment")
    public ResponseEntity<?> createPayment(@RequestBody PaymentModel payment){
        try{
            payment.setPaymentDate(new Date(System.currentTimeMillis()));
            PaymentRepo.save(payment);
            return new ResponseEntity<PaymentModel>(payment,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/payment/{id}")
    public ResponseEntity<?> getSingalePayment(@PathVariable("id")String id){
        Optional<PaymentModel> paymentOptional = PaymentRepo.findById(id);
        if (paymentOptional.isPresent()){
            return new ResponseEntity<>(paymentOptional.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Payment not found with id" + id, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/payment/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id")String id, @RequestBody PaymentModel payment){
        Optional<PaymentModel> paymentOptional = PaymentRepo.findById(id);
        if (paymentOptional.isPresent()){
            PaymentModel paymentSave = paymentOptional.get();
            paymentSave.setCompleted(payment.getCompleted() != null ? payment.getCompleted(): paymentSave.getCompleted());
            paymentSave.setName(payment.getName() != null ? payment.getName(): paymentSave.getName());
            paymentSave.setPaymentType(payment.getPaymentType() != null ? payment.getPaymentType(): paymentSave.getPaymentType());
            paymentSave.setUpdateDate(new Date(System.currentTimeMillis()));
            PaymentRepo.save(paymentSave);
            return new ResponseEntity<>(paymentSave, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Payment not found with id" + id, HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/payment/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id){
        try {
            PaymentRepo.deleteById(id);
            return new ResponseEntity<>("Successfull deleted with id" + id , HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }








    /*public PaymentController(PaymenService paymenService) {
        this.paymenService = paymenService;
    }
    @GetMapping("/all")
    public ResponseEntity<List<PaymentModel>>getAllPayments(){
        List<PaymentModel> payment = paymenService.getAllPayments();
        return new ResponseEntity<>(payment, HttpStatus.OK);
    }
    @PostMapping("/save")
    public ResponseEntity<PaymentModel> savePayment(@RequestBody PaymentModel payment){
        PaymentModel newPayment = paymenService.savePayment(payment);
        return new ResponseEntity<>(newPayment, HttpStatus.CREATED);
    }



    @GetMapping("/search/{paymentId}")
    public ResponseEntity<PaymentModel> searchPaymentById(@PathVariable String paymentId){
        if (paymentId==null || paymentId.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        PaymentModel payment = paymenService.findPaymentByPaymentId(paymentId);

        return ResponseEntity.ok(payment);
    }*/


}
