package uz.pdp.ussdapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.ussdapp.entity.Details;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface DetailsRepository extends JpaRepository<Details, UUID> {
    List<Details> findAllBySimCard_IdAndDateBetween(UUID simCard_id, Date date, Date date2);
}
