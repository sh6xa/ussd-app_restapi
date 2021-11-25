package uz.pdp.ussdapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.ussdapp.entity.Tariff;
import uz.pdp.ussdapp.entity.enums.UserType;
import uz.pdp.ussdapp.payload.ApiResponse;
import uz.pdp.ussdapp.payload.TariffDTO;
import uz.pdp.ussdapp.repository.TariffRepository;
import uz.pdp.ussdapp.repository.TariffSimcardRepository;

import java.util.Optional;

@Service
public class TariffService {
    @Autowired
    TariffRepository tariffRepository;

    public ApiResponse saveTariff(TariffDTO tariffDTO) {

        if (tariffRepository.existsByName(tariffDTO.getName())) return new ApiResponse("Bunday tariff bor", false);

        Tariff tariff = new Tariff(
                tariffDTO.getName(),
                UserType.valueOf(tariffDTO.getUserType()),
                tariffDTO.getPrice(),
                tariffDTO.getSwitchPrice(),
                tariffDTO.getExpire(),
                tariffDTO.getTariffSMS(),
                tariffDTO.getTariffMB(),
                tariffDTO.getTariffDAQ(),
                tariffDTO.getSmsPrice(),
                tariffDTO.getMbPrice(),
                tariffDTO.getDaqPrice());

        tariffRepository.save(tariff);
        return new ApiResponse("Saved!", true);

    }

    public ApiResponse editTariff(String name, TariffDTO tariffDTO) {

        if (!tariffRepository.existsByName(name)) return new ApiResponse("tariff not found", false);
        Optional<Tariff> byName = tariffRepository.findByName(name);
        Tariff tariff = byName.get();
        tariff.setTariffMB(tariffDTO.getTariffMB());

        tariff.setTariffSMS(tariffDTO.getTariffSMS());
        tariff.setDaqPrice(tariffDTO.getDaqPrice());
        tariff.setTariffMB(tariffDTO.getTariffMB());
        tariff.setTariffMB(tariffDTO.getTariffMB());
        tariff.setExpire(tariffDTO.getExpire());
        tariff.setPrice(tariffDTO.getPrice());
        tariff.setMbPrice(tariffDTO.getMbPrice());
        tariff.setDaqPrice(tariffDTO.getDaqPrice());
        tariff.setSmsPrice(tariffDTO.getSmsPrice());
        tariff.setSwitchPrice(tariffDTO.getSwitchPrice());
        tariff.setUserType(UserType.PHYSICAL_USER);
        tariff.setName("special30");
        tariffRepository.save(tariff);
        return new ApiResponse("success", true);


    }
}
