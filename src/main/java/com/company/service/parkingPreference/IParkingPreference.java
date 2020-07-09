package com.company.service.parkingPreference;


import com.company.domain.dto.FloorDTO;
import com.company.domain.dto.ParkingSpaceDTO;
import com.company.domain.entity.Floor;
import com.company.domain.entity.Parking;
import java.util.List;

public interface IParkingPreference {

    ParkingSpaceDTO findNearestSlot(List<Parking> parkings, List<Floor> floors);
}
