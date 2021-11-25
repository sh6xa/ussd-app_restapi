package uz.pdp.ussdapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.ussdapp.entity.enums.Position;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffDTO {
    private String fullName;
    private String userName;
    private String password;
    private Position position;
    private UUID filialId;

}
