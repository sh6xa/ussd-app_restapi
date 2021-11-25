package uz.pdp.ussdapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.ussdapp.entity.enums.Position;
import uz.pdp.ussdapp.entity.template.AbsEntity;
import uz.pdp.ussdapp.entity.template.AbsNameEntity;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Filial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "filial")
    private List<Staff> staffList;

    @OneToOne
    private Staff director;

    @OneToOne
    private Staff filialManager;

    public Filial(String name, List<Staff> staffList, Staff director) {
        this.name = name;
        this.staffList = staffList;
        this.director = director;
    }
}
