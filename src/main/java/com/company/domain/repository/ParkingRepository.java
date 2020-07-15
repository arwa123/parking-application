package com.company.domain.repository;

import com.company.domain.entity.Parking;
import com.company.domain.entity.Vehicle;
import com.company.enums.ParkingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;


@Repository
public interface ParkingRepository extends JpaRepository<Parking, Long> {
    Optional<List<Parking>> findByParkingLotId(Long parkingLotId);

    @Query("SELECT p FROM Parking p where p.parkingLotId = :parkingLotId AND p.floorNumber = :floorNumber")
    Optional<List<Parking>> findByParkingLotIdAndFloorNumber(@Param("parkingLotId") Long parkingLotId,
                                                             @Param("floorNumber") Integer floorNumber);

    @Query("SELECT p FROM Parking p where p.parkingLotId = :parkingLotId AND (p.endTime > :endTime OR p.endTime IS NULL)")
    Optional<List<Parking>> findByParkingLotIdAndEndTimeGreaterThan(Long parkingLotId,
                                                                    Long endTime);

    Optional<Parking> findByVehicle(Vehicle vehicle);

    @Query("SELECT p FROM Parking p where p.parkingLotId = :parkingLotId AND p.floorNumber = :floorNumber AND p.parkingNumber = :parkingNumber AND p.parkingLevel = :parkingLevel AND p.parkingType = :parkingType")
    Optional<Parking> findByParkingLotIdAndFloorNumberAndParkingNumberAndParkingLevelAndType(@Param("parkingLotId") Long parkingLotId,
                                                                                            @Param("floorNumber") Integer floorNumber, @Param("parkingNumber") Integer parkingNumber, @Param("parkingLevel") Integer parkingLevel, @Param("parkingType") ParkingType parkingType);

}
