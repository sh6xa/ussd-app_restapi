package uz.pdp.ussdapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthRegisterDTO {
    @NotNull(message = "fullName ni kiriting")
    private String fullName;
    @NotNull(message = "email ni kiriting")
    private String email;
    @NotNull(message = "password ni kiriting")
    private String password;
    @NotNull(message = "Prepassword ni kiriting")
    private String prePassword;
}
