package uz.pdp.ussdapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.ussdapp.entity.SimCard;
import uz.pdp.ussdapp.entity.TariffSimcard;
import uz.pdp.ussdapp.entity.UssdCode;
import uz.pdp.ussdapp.payload.ApiResponse;
import uz.pdp.ussdapp.payload.USSDDTO;
import uz.pdp.ussdapp.repository.SimcardRepository;
import uz.pdp.ussdapp.repository.TariffSimcardRepository;
import uz.pdp.ussdapp.repository.USSDRepository;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@Service
public class USSDService {
    @Autowired
    USSDRepository ussdRepository;
   @Autowired
    SimcardRepository simcardRepository;
   @Autowired
    TariffSimcardRepository tariffSimcardRepository;


    //
    public ApiResponse getBalance(UUID id){
        Optional<SimCard> byId = simcardRepository.findById(id);

        SimCard simCard = byId.get();
        return new ApiResponse("success",true,simCard.getBalance() );


    }

    //mb  va daq
    public ApiResponse getMb(UUID id){
        Optional<SimCard> byId = simcardRepository.findById(id);
        SimCard simCard = byId.get();
        Optional<TariffSimcard> bySimCard_id = tariffSimcardRepository.findBySimCard_Id(id);

        return new ApiResponse("success",true,Arrays.asList(bySimCard_id.get().getLeftOverDAQ(),bySimCard_id.get().getLeftOverMB()));
    }

    //all code

}
