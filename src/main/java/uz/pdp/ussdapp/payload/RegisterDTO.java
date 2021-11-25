package uz.pdp.ussdapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    private String passportID;
    private String passportNumber;
    private String code;
    private String number;
}
