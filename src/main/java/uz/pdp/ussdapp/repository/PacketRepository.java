package uz.pdp.ussdapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.ussdapp.entity.Packet;

public interface PacketRepository extends JpaRepository<Packet, Integer> {

}
