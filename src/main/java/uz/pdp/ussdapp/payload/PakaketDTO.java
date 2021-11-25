package uz.pdp.ussdapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.ussdapp.entity.Tariff;
import uz.pdp.ussdapp.entity.enums.Type;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PakaketDTO {
    private Type type;
    private double amount; //5Gb
    private double price;
    private String name;
    private int dueDate;
    private List<Integer> tariffList;
}
