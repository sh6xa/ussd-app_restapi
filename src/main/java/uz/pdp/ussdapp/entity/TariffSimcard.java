package uz.pdp.ussdapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.ussdapp.entity.template.AbsEntity;

import javax.annotation.security.DenyAll;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TariffSimcard extends AbsEntity {

    @OneToOne
    private SimCard simCard;
    @OneToOne
    private Tariff tariff;

    private int leftOverSMS;//
    private double leftOverMB;//
    private int leftOverDAQ;//
}
