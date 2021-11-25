package uz.pdp.ussdapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.ussdapp.entity.Tariff;

import java.util.Optional;

public interface TariffRepository extends JpaRepository<Tariff, Integer> {
    boolean existsByName(String name);

    Optional<Tariff> findByName(String name);

    void deleteByName(String name);
}
