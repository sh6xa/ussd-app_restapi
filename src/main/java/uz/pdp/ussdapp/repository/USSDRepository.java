package uz.pdp.ussdapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.ussdapp.entity.UssdCode;

public interface USSDRepository extends JpaRepository<UssdCode,Integer> {
}
