package com.company.controller;

import com.company.domain.entity.ParkingLot;
import com.company.service.IParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/parkinglots", produces = "application/json")
public class ParkingLotController {
    @Autowired
    IParkingLotService IParkingLotService;

    @GetMapping
    public List<ParkingLot> getAllParkingLots() {
        return IParkingLotService.findAll();
    }

    @PostMapping
    public ParkingLot createParkingLot(@RequestBody ParkingLot parkingLot) {
        return IParkingLotService.save(parkingLot);
    }

    @GetMapping("/{id}")
    public ParkingLot getParkingLotById(@PathVariable(value = "id") Long parkingLotId) {
        return IParkingLotService.findById(parkingLotId);
    }

    @PutMapping("/{id}")
    public ParkingLot updateParkingLot(@PathVariable(value = "id") Long parkingLotId,
                           @Valid @RequestBody ParkingLot parkingLotDetails) {

        IParkingLotService.findById(parkingLotId);

        parkingLotDetails.setId(parkingLotId);

        ParkingLot updatedParkingLot = IParkingLotService.save(parkingLotDetails);
        return updatedParkingLot;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteParkingLot(@PathVariable(value = "id") Long parkingLotId) {
        ParkingLot parkingLot = IParkingLotService.findById(parkingLotId);

        IParkingLotService.delete(parkingLot);

        return ResponseEntity.ok().build();
    }

}
