package uz.pdp.ussdapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private double amount;

//    @Pattern(regexp = "(^$|[+998]{3})")
    private String phoneNumber; //+998 90 2455897
    private String type;
}
