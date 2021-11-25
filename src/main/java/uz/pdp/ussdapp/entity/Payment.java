package uz.pdp.ussdapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.ussdapp.entity.enums.PayType;
import uz.pdp.ussdapp.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Payment extends AbsEntity {
    private double amount;
    private Date date;

    @Enumerated(EnumType.STRING)
    private PayType payType; //Paynet Click Payme

    @ManyToOne
    private User payer;

    private String number; //+998 90 2455897
//    @ManyToOne
//    private SimCard simCard;

}
