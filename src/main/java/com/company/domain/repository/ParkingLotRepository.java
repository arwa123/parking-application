package  com.company.domain.repository;

import com.company.domain.entity.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long> {

}
