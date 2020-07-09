package com.company.service;

import com.company.domain.entity.ParkingLot;
import java.util.List;

public interface IParkingLotService {
    List<ParkingLot> findAll();

    ParkingLot save(ParkingLot parkingLot);

    ParkingLot findById(Long parkingLotId);

    void delete(ParkingLot parkingLot);
}
