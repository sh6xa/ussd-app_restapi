package uz.pdp.ussdapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.ussdapp.entity.enums.UserType;
import uz.pdp.ussdapp.entity.template.AbsEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"code", "number"})})
public class SimCard extends AbsEntity {

//    @ManyToOne(fetch = FetchType.LAZY)
//    private Tariff tariff;

    @Column(nullable = false)
    private String code;
    @Column(nullable = false)
    private String number;
    private double balance;
    private boolean active;
    private Date start;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Packet> packets;

}
