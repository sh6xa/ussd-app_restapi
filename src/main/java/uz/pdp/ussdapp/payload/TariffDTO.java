package uz.pdp.ussdapp.payload;

import lombok.Data;
import uz.pdp.ussdapp.entity.enums.UserType;

@Data
public class TariffDTO {

    private String name;
    private String userType;
    private double price;
    private double switchPrice;
    private int expire;
    private int tariffSMS;//5000
    private double tariffMB;//10Gb
    private int tariffDAQ;//5000
    private double smsPrice;//84
    private double mbPrice;//121
    private double daqPrice;//
}
