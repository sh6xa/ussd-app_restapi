package uz.pdp.ussdapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.ussdapp.entity.Tariff;
import uz.pdp.ussdapp.entity.TariffSimcard;

import java.util.Optional;
import java.util.UUID;

public interface TariffSimcardRepository extends JpaRepository<TariffSimcard, UUID> {
    Optional<TariffSimcard> findBySimCard_Id(UUID uuid);

    @Query(value = "SELECT tariff_id FROM tariff_simcard GROUP BY tariff_id ORDER BY  count(tariff_id) desc limit 1",nativeQuery = true)
    Integer findActiveTariff();

    @Query(value = "SELECT tariff_id FROM tariff_simcard GROUP BY tariff_id ORDER BY  count(tariff_id) asc limit 1",nativeQuery = true)
    Integer findPassiveTariff();

}
