package com.company.domain.repository;

import com.company.domain.entity.Vehicle;
import com.company.enums.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Optional<Vehicle> findTopByVehicleNumberPlateAndVehicleTypeOrderByIdDesc(String vehicleNumberPlate, VehicleType vehicleType);

}
