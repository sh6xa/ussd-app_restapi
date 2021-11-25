package uz.pdp.ussdapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.ussdapp.entity.Payment;
import uz.pdp.ussdapp.entity.SimCard;
import uz.pdp.ussdapp.entity.enums.PayType;
import uz.pdp.ussdapp.payload.ApiResponse;
import uz.pdp.ussdapp.payload.PaymentDTO;
import uz.pdp.ussdapp.repository.PaymentRepository;
import uz.pdp.ussdapp.repository.SimcardRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentService {
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    SimcardRepository simcardRepository;


    public ApiResponse add(PaymentDTO paymentDTO) {

        // paymentDTO.getPhoneNumber().startsWith("+998")

        // 91 2455897 - new pattern,+998 did not work
        if (  paymentDTO.getPhoneNumber().length() == 9) {
            boolean exists = simcardRepository.existsByCodeAndNumber(
                    paymentDTO.getPhoneNumber().substring(0, 2),
                    paymentDTO.getPhoneNumber().substring(2));

            Optional<SimCard> optionalSimCard = simcardRepository.findByCodeAndNumber(
                    paymentDTO.getPhoneNumber().substring(0, 2),
                    paymentDTO.getPhoneNumber().substring(2));

            if (!exists) return new ApiResponse("This phone number does not exists!", false);


            SimCard simCard = optionalSimCard.get();
            simCard.setBalance(simCard.getBalance() + paymentDTO.getAmount());


            Payment payment = new Payment();
            payment.setAmount(paymentDTO.getAmount());
            payment.setDate(new Date());
            payment.setNumber(paymentDTO.getPhoneNumber());
            payment.setPayType(PayType.valueOf(paymentDTO.getType()));
            paymentRepository.save(payment);
            simcardRepository.save(simCard);
            return new ApiResponse("Success", true);
        }
        return new ApiResponse("Phone number pattern wrong!", false);

    }

    public ApiResponse getAll() {
        List<Payment> paymentList = paymentRepository.findAll();
        return new ApiResponse("Payments history",true,paymentList);
    }


    public ApiResponse getOne(String phoneNumber) {
        Optional<SimCard> optionalSimCard = simcardRepository.findByCodeAndNumber(
                phoneNumber.substring(0, 2),
                phoneNumber.substring(2));
        if(!optionalSimCard.isPresent()) return new ApiResponse("Phone Number does not exists!",false);
        List<Payment> paymentList = paymentRepository.findAllByNumber(phoneNumber);
        return new ApiResponse(phoneNumber+"-history",true,paymentList);

    }

    public ApiResponse delete(UUID id) {
        paymentRepository.deleteById(id);
        return new ApiResponse("Deleted!",true);
    }
}


