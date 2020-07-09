package com.company.service;

import com.company.domain.entity.ParkingLot;
import com.company.domain.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IParkingLotServiceImpl implements IParkingLotService {
    @Autowired
    ParkingLotRepository parkingLotRepository;
    @Override
    public List<ParkingLot> findAll() {
        return parkingLotRepository.findAll();
    }

    @Override
    public ParkingLot save(ParkingLot parkingLot) {
        return parkingLotRepository.save(parkingLot);
    }

    @Override
    public ParkingLot findById(Long parkingLotId) {
        return parkingLotRepository.findById(parkingLotId)
                .orElse(null);
    }

    @Override
    public void delete(ParkingLot parkingLot) {
        parkingLotRepository.delete(parkingLot);
    }
}
