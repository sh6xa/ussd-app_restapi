package uz.pdp.ussdapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.ussdapp.entity.Filial;

import java.util.UUID;

public interface FilialRepository extends JpaRepository<Filial, UUID> {

}
