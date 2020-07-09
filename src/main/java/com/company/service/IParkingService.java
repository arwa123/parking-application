package com.company.service;


import com.company.domain.dto.ParkingRequestDTO;
import com.company.domain.dto.VehicleDTO;
import com.company.domain.entity.Parking;
import java.util.List;

public interface IParkingService {
    List<Parking> findAll();

    Parking findById(Long parkingId);

    Parking save(Parking parkingDetails);

    void delete(Parking parking);

    Parking createParking(ParkingRequestDTO parkingRequestDto) throws Exception;

    Parking freeUpParking(VehicleDTO vehicleDTO);
}
