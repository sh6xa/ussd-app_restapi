package uz.pdp.ussdapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginDto {
    @NotNull(message = "Iltimos emailni kiriting")
    private String email;
    @NotNull(message = "Iltimos passwordni kiriting")
    private String password;

}
