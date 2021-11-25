package uz.pdp.ussdapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.ussdapp.entity.SimCard;
import uz.pdp.ussdapp.entity.Tariff;
import uz.pdp.ussdapp.payload.ApiResponse;
import uz.pdp.ussdapp.repository.SimcardRepository;
import uz.pdp.ussdapp.repository.TariffRepository;
import uz.pdp.ussdapp.repository.TariffSimcardRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DashboardService {
    @Autowired
    SimcardRepository simcardRepository;

    @Autowired
    TariffSimcardRepository tariffSimcardRepository;

    @Autowired
    TariffRepository tariffRepository;

    public ApiResponse getActive() {
        List<SimCard> allByActive = simcardRepository.findAllByActive(true);
        return new ApiResponse("Active simCards",true,allByActive);
    }

    public ApiResponse findActiveTariff() {
        Integer activeTariff = tariffSimcardRepository.findActiveTariff();
        Optional<Tariff> tariffOptional = tariffRepository.findById(activeTariff);
        Tariff tariff = tariffOptional.get();
        String tariffName = tariff.getName();
        return new ApiResponse("Most using tariff",true,tariffName);
    }

    public ApiResponse findPassiveTariff() {
        Integer passiveTariff = tariffSimcardRepository.findPassiveTariff();
        Optional<Tariff> tariffOptional = tariffRepository.findById(passiveTariff);
        Tariff tariff = tariffOptional.get();
        String tariffName = tariff.getName();
        return new ApiResponse("Passive tariff",true,tariffName);

    }

//    public ApiResponse findActivePackets() {
//
//    }
}
