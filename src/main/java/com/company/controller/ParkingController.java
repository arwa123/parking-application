package com.company.controller;

import com.company.domain.dto.ParkingRequestDTO;
import com.company.domain.dto.VehicleDTO;
import com.company.domain.entity.Parking;
import com.company.service.IParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping(value = "/parkings", produces = "application/json")
public class ParkingController {
    @Autowired
    IParkingService IParkingService;

    @GetMapping
    public List<Parking> getAllParkings() {
        return IParkingService.findAll();
    }

    @PostMapping
    public Parking createParking(@RequestBody ParkingRequestDTO parkingRequestDto) throws Exception {
        return IParkingService.createParking(parkingRequestDto);
    }
    @PostMapping("/free")
    public Parking freeUpParking(@RequestBody VehicleDTO vehicleDTO) {
        return IParkingService.freeUpParking(vehicleDTO);
    }
    @GetMapping("/{id}")
    public Parking getParkingById(@PathVariable(value = "id") Long parkingId) {
        return IParkingService.findById(parkingId);
    }

    @PutMapping("/{id}")
    public Parking updateParking(@PathVariable(value = "id") Long parkingId,
                                 @Valid @RequestBody Parking parkingDetails) {

        IParkingService.findById(parkingId);

        parkingDetails.setId(parkingId);

        Parking updatedParking = IParkingService.save(parkingDetails);
        return updatedParking;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteParking(@PathVariable(value = "id") Long parkingId) {
        Parking parking = IParkingService.findById(parkingId);

        IParkingService.delete(parking);

        return ResponseEntity.ok().build();
    }

}
