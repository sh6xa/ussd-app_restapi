package uz.pdp.ussdapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.ussdapp.entity.Packet;
import uz.pdp.ussdapp.entity.SimCard;
import uz.pdp.ussdapp.entity.Tariff;
import uz.pdp.ussdapp.entity.TariffSimcard;
import uz.pdp.ussdapp.payload.ApiResponse;
import uz.pdp.ussdapp.payload.RegisterDTO;
import uz.pdp.ussdapp.repository.PacketRepository;
import uz.pdp.ussdapp.repository.SimcardRepository;
import uz.pdp.ussdapp.repository.TariffRepository;
import uz.pdp.ussdapp.repository.TariffSimcardRepository;

import java.util.*;

@Service
public class SimCardService {
    @Autowired
    TariffSimcardRepository tariffSimcardRepository;
    @Autowired
    SimcardRepository simcardRepository;
    @Autowired
    TariffRepository tariffRepository;
    @Autowired
    PacketRepository packetRepository;

    public ApiResponse add(RegisterDTO registerDTO) {
        SimCard simCard = new SimCard();
        simCard.setBalance(0);
        simCard.setCode(registerDTO.getCode());
        simCard.setNumber(registerDTO.getNumber());
        simCard.setActive(true);
        simcardRepository.save(simCard);
        return new ApiResponse("Registred!", true);
    }

    public ApiResponse changeTariff(UUID id, Integer tariffId) {

        Optional<SimCard> optionalSimCard = simcardRepository.findById(id);
        SimCard simCard = optionalSimCard.get();
        Optional<Tariff> optionalTariff = tariffRepository.findById(tariffId);
        if (!optionalTariff.isPresent() || !optionalSimCard.isPresent()) return new ApiResponse("NOT", false);

        Tariff tariff = optionalTariff.get();

        TariffSimcard tariffSimcard = new TariffSimcard();
        tariffSimcard.setTariff(tariff); //qaysi tariff
        tariffSimcard.setSimCard(simCard); //qaysi simcard

        simCard.setStart(new Date());
//        simCard.setTariff(tariff);
        //yangi ulanish tariff

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(simCard.getStart());
        int kun = calendar.get(Calendar.DAY_OF_MONTH);//20
        int maxKun = calendar.getActualMaximum(Calendar.DATE);//31
        int qolganKun = maxKun - kun;
        double kunlik = tariff.getPrice() / maxKun; //1 kunlik 1200
        double yechilishiKk = (maxKun - kun) * kunlik;
        double kunlikMB = tariff.getTariffMB() / maxKun;
        int kunlikSMS = tariff.getTariffSMS() / maxKun;
        int kunlikDAQ = tariff.getTariffDAQ() / maxKun;

        if (simCard.getBalance() > tariff.getPrice() + tariff.getSwitchPrice()) {
            tariffSimcard.setLeftOverSMS(tariff.getTariffSMS());
            tariffSimcard.setLeftOverDAQ(tariff.getTariffDAQ());
            tariffSimcard.setLeftOverMB(tariff.getTariffMB());
            simCard.setBalance(simCard.getBalance() - tariff.getPrice() - tariff.getSwitchPrice());

        } else if (simCard.getBalance() > yechilishiKk) {
            tariffSimcard.setLeftOverMB(kunlikMB * qolganKun);
            tariffSimcard.setLeftOverSMS(kunlikSMS * qolganKun);
            tariffSimcard.setLeftOverDAQ(kunlikDAQ * qolganKun);
            simCard.setBalance(simCard.getBalance() - yechilishiKk);
            //20-kun 10kunga yetadigan
            //oyni o'rtasidan ulandi
            //kunlik yechish

        } else {
            //kunlik

            if (simCard.getBalance() < kunlik) {
//                simCard.setActive(false);
                return new ApiResponse("Kunlik un ham puling yoq debil!", false);
            }
//            simCard.getTariff().setTariffMB(kunlikMB);
//            simCard.getTariff().setTariffDAQ(kunlikDAQ);
//            simCard.getTariff().setTariffSMS(kunlikSMS);
            tariffSimcard.setLeftOverMB(kunlikMB);
            tariffSimcard.setLeftOverSMS(kunlikSMS);
            tariffSimcard.setLeftOverDAQ(kunlikDAQ);

//            Chrone annotation

            simCard.setBalance(simCard.getBalance() - kunlik);
        }
        tariffSimcardRepository.save(tariffSimcard);
        simcardRepository.save(simCard);
        return new ApiResponse("Mana", true);
    }

    public ApiResponse buyPacket(UUID id, Integer packetId) {
        Optional<Packet> optionalPacket = packetRepository.findById(packetId);
        Packet packet = optionalPacket.get();


        Optional<SimCard> cardOptional = simcardRepository.findById(id);
        SimCard simCard = cardOptional.get();


        simCard.setPackets(Collections.singletonList(packet));
        simCard.setBalance(simCard.getBalance() - packet.getPrice());
        simcardRepository.save(simCard);
        return new ApiResponse("Save", true);
    }
}
