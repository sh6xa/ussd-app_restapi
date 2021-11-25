package uz.pdp.ussdapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.ussdapp.entity.enums.ActionType;
import uz.pdp.ussdapp.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Details extends AbsEntity {

    @ManyToOne
    private SimCard simCard;

    private Date date;

    @Enumerated
    private ActionType actionType;
    //miqdor yo sum
    private double amount;

}
