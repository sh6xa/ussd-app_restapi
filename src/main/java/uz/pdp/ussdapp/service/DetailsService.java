package uz.pdp.ussdapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.ussdapp.entity.Details;
import uz.pdp.ussdapp.entity.Packet;
import uz.pdp.ussdapp.entity.SimCard;
import uz.pdp.ussdapp.entity.TariffSimcard;
import uz.pdp.ussdapp.entity.enums.ActionType;
import uz.pdp.ussdapp.entity.enums.Type;
import uz.pdp.ussdapp.payload.ApiResponse;
import uz.pdp.ussdapp.payload.DetailsDTO;
import uz.pdp.ussdapp.payload.ExcelRequestDynamic;
import uz.pdp.ussdapp.repository.DetailsRepository;
import uz.pdp.ussdapp.repository.SimcardRepository;
import uz.pdp.ussdapp.repository.TariffSimcardRepository;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DetailsService {

    @Autowired
    SimcardRepository simcardRepository;
    @Autowired
    TariffSimcardRepository tariffSimcardRepository;
    @Autowired
    DetailsRepository detailsRepository;
//    @Autowired
//    GenerateExcel generateExcel;

    public ApiResponse save(UUID id, DetailsDTO detailsDTO) {

        Optional<SimCard> byId = simcardRepository.findById(id);
        SimCard simCard = byId.get();

        Optional<TariffSimcard> optionalTariffSimcard = tariffSimcardRepository.findBySimCard_Id(simCard.getId());
        TariffSimcard tariffSimcard = optionalTariffSimcard.get();
        List<Packet> packets = tariffSimcard.getSimCard().getPackets();
        List<Packet> mbPacket = new ArrayList<>();
        for (Packet packet : packets) {
            if (packet.getType().equals(Type.MB)) {
                mbPacket.add(packet);
            }
        }

        Details details = new Details(
                simCard,
                new Date(),
                ActionType.valueOf(detailsDTO.getAction()),
                detailsDTO.getAmount()
        );
        detailsRepository.save(details);

        //simkard
        switch (ActionType.valueOf(detailsDTO.getAction())) {
            case MB:
                if (tariffSimcard.getLeftOverMB() > detailsDTO.getAmount()) {
                    tariffSimcard.setLeftOverMB(tariffSimcard.getLeftOverMB() - detailsDTO.getAmount());
                } else if (tariffSimcard.getLeftOverMB() < detailsDTO.getAmount()) {
                    //toliq yetamydi 10 8
                    double bor = tariffSimcard.getLeftOverMB(); //227
                    double qoldi = detailsDTO.getAmount() - tariffSimcard.getLeftOverMB(); //73
                    tariffSimcard.setLeftOverMB(0);
                    if (!mbPacket.isEmpty()) {
                        for (Packet packet : mbPacket) {
                            if (packet.getAmount() > qoldi) {
                                packet.setAmount(packet.getAmount() - qoldi);
                                break;
                            }
                        }
                    } else {
                        //balancedan
                        tariffSimcard.getSimCard().setBalance(
                                tariffSimcard.getSimCard().getBalance() - qoldi * tariffSimcard.getTariff().getMbPrice());
                    }
                }
//
                break;
            case SMS:
//                simCard.getTariff().setTariffSMS(
//                        (int) (simCard.getTariff().getTariffSMS() - detailsDTO.getAmount()));
                break;
            case DAQ:
//                simCard.getTariff().setTariffDAQ(
//                        (int) (simCard.getTariff().getTariffDAQ() - detailsDTO.getAmount()));
                break;
            case SERVICE:
                simCard.setBalance(simCard.getBalance() - detailsDTO.getAmount());
                break;
            case TARIFF:
                simCard.setBalance(simCard.getBalance() - detailsDTO.getAmount());
                break;
            case PAKET:
                simCard.setBalance(simCard.getBalance() - detailsDTO.getAmount());
                break;
        }

        tariffSimcardRepository.save(tariffSimcard);
        simcardRepository.save(simCard);
        return new ApiResponse("Details Saved!", true);
    }

    public ApiResponse getDetails(UUID id, String from, String to) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date fromDate = simpleDateFormat.parse(from);
        Date toDate = simpleDateFormat.parse(to);


        List<Details> dateBetween = detailsRepository.findAllBySimCard_IdAndDateBetween(id, fromDate, toDate);
        return new ApiResponse("Mana", true, dateBetween);
    }

//    public ResponseEntity getExcel() {
//        List<Details> all = detailsRepository.findAll();
//
//        List<Details> detailsList = all.stream().map(this::getResponse).collect(Collectors.toList());
//
//        List<String> columns = columns(all.get(0));
////        List<List<String>> collect = all.stream().map(this::columns).collect(Collectors.toList());
//
//
//        ExcelRequestDynamic requestDynamic = new ExcelRequestDynamic(
//                "Mana List",
//                columns,
//                columns,
//                detailsList,
//                new Timestamp(System.currentTimeMillis()),
//                new Timestamp(System.currentTimeMillis() + 86400 * 1000)
//        );
//
//        return generateExcel.exportDataToExcel(requestDynamic);
////        List<ResWarehouse> warehouses = warehouseRepository.findAll().stream().map(this::getWarehouse).collect(Collectors.toList());
//    }

    public Details getResponse(Details details) {

        return new Details(
                details.getSimCard(),
                details.getDate(),
                details.getActionType(),
                details.getAmount()
        );
    }

    public List<String> columns(Details details) {
        return new ArrayList(
                Arrays.asList(
                        details.getSimCard().getNumber(),
                        String.valueOf(details.getActionType()),
                        String.valueOf(details.getAmount()),
                        String.valueOf(details.getDate())
                )
        );
    }
}
