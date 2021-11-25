package uz.pdp.ussdapp.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.pdp.ussdapp.entity.Filial;
import uz.pdp.ussdapp.entity.Role;
import uz.pdp.ussdapp.entity.Staff;
import uz.pdp.ussdapp.entity.enums.Permission;
import uz.pdp.ussdapp.repository.FilialRepository;
import uz.pdp.ussdapp.repository.RoleRepository;
import uz.pdp.ussdapp.repository.StaffRepository;
import uz.pdp.ussdapp.repository.UserRepository;
import uz.pdp.ussdapp.util.Constants;

import java.util.Arrays;
import java.util.Collections;

import static uz.pdp.ussdapp.entity.enums.Permission.*;

@Component
public class Dataloader implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    FilialRepository filialRepository;

    @Autowired
    StaffRepository staffRepository;

    @Autowired
    RoleRepository roleRepository;



    @Value("${spring.sql.init.mode}")
    private String initMode;

    @Override
    public void run(String... args) throws Exception {
        Permission[] values = values();
        if (initMode.equals("always")) {
            Staff director = staffRepository.save(new Staff("Director", "director", "director"));
            Staff staff = staffRepository.save(new Staff("Xodim", "xodim", "xodim"));

            filialRepository.save(new Filial("PDP",Collections.singletonList(staff),director));

             roleRepository.save(new Role(Constants.DIRECTOR,
                    Arrays.asList(values)));
             roleRepository.save(new Role(Constants.USER,
                    Arrays.asList(GET_SIMCARD)));

        }
    }
}
