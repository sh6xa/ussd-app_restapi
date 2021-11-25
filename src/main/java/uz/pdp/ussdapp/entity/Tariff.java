package uz.pdp.ussdapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.ussdapp.entity.enums.UserType;
import uz.pdp.ussdapp.entity.template.AbsNameEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tariff extends AbsNameEntity {

    @Enumerated(EnumType.STRING)
    private UserType userType;

    private double price; //narxi
    private double switchPrice;//otish narxi

    private int expire;//amal qilish

    private int tariffSMS;//5000
    private double tariffMB;//10Gb
    private int tariffDAQ;//5000

    private double smsPrice;//84
    private double mbPrice;//121
    private double daqPrice;//

//    @OneToMany(mappedBy = "tariff")
//    private List<SimCard> simCardList;

    public Tariff(String name, UserType userType, double price, double switchPrice, int expire, int tariffSMS, double tariffMB, int tariffDAQ, double smsPrice, double mbPrice, double daqPrice) {
        super(name);
        this.userType = userType;
        this.price = price;
        this.switchPrice = switchPrice;
        this.expire = expire;
        this.tariffSMS = tariffSMS;
        this.tariffMB = tariffMB;
        this.tariffDAQ = tariffDAQ;
        this.smsPrice = smsPrice;
        this.mbPrice = mbPrice;
        this.daqPrice = daqPrice;
    }
}
