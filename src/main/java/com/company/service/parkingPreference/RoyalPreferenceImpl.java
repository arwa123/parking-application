package com.company.service.parkingPreference;


import com.company.domain.dto.ParkingSpaceDTO;
import com.company.domain.entity.Floor;
import com.company.domain.entity.Parking;
import com.company.domain.entity.ParkingSpace;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.company.mapper.MapperUtil.*;

public class RoyalPreferenceImpl implements IParkingPreference {

    @Override
    public ParkingSpaceDTO findNearestSlot(List<Parking> parkings, List<Floor> floors) {
        for (Floor floor : floors) {
            List<Parking> parkedOnFloor = getAllParkingOnFloor(parkings, floor.getFloorNumber());
            Set<ParkingSpace> parkedSpacesOnFloor = getAllParkingSpaceFromParking(parkedOnFloor);
            List<ParkingSpace> allParkingSpacesPerFloor = floor.getParkingSpaceList();
            List<ParkingSpace> allParkingSpacesPerFloorInOrderOfEaseList = getFreeParkingSpaceInOrderOfEase(allParkingSpacesPerFloor).stream().collect(Collectors.toList());
            for (int i = 0; i < allParkingSpacesPerFloorInOrderOfEaseList.size(); i++) {
                ParkingSpace slot = allParkingSpacesPerFloorInOrderOfEaseList.get(i);
                if (!parkingAlreadyExists(parkedSpacesOnFloor, slot)) {
                    ParkingSpace freeParkingSpotCurrentRemaining = getParkingSpace(slot.getParkingNumber(),slot.getParkingLevel().equals(0) ? 1 : 0);
                    ParkingSpace freeParkingSpotsPrevUp =getParkingSpace(slot.getParkingNumber() - 1,1);
                    ParkingSpace freeParkingSpotsPrevDown = getParkingSpace(slot.getParkingNumber() - 1,0);
                    ParkingSpace freeParkingSpotsNextUp = getParkingSpace(slot.getParkingNumber() + 1,1);
                    ParkingSpace freeParkingSpotsNextDown = getParkingSpace(slot.getParkingNumber() + 1,0);
                    if (checkForRoyal(parkedSpacesOnFloor, freeParkingSpotsPrevUp, freeParkingSpotsPrevDown, freeParkingSpotsNextUp, freeParkingSpotsNextDown, freeParkingSpotCurrentRemaining)) {
                        return getParkingSpaceDTO(slot, floor.getFloorNumber());
                    }
                }
            }
        }
        return null;
    }
}
