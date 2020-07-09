package com.company.service.parkingPreference;


import com.company.domain.dto.FloorDTO;
import com.company.domain.dto.ParkingSpaceDTO;
import com.company.domain.entity.Floor;
import com.company.domain.entity.Parking;
import com.company.domain.entity.ParkingSpace;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static com.company.util.ParkingHelper.*;

public class RoyalPreferenceImpl implements IParkingPreference {

    @Override
    public ParkingSpaceDTO findNearestSlot(List<Parking> parkings, List<Floor> floors) {
        for (Floor floor : floors) {
            List<Parking> parkingsOnFloor = getAllParkingOnFloor(parkings, floor.getFloorNumber());
            Set<ParkingSpace> allParkingSpaceFromParking = getAllParkingSpaceFromParking(parkingsOnFloor);
            List<ParkingSpace> allParkingSpacesPerFloor = floor.getParkingSpaceList();
            Set<ParkingSpace> allParkingSpacesPerFloorInOrderOfEase = getFreeParkingSpaceInOrderOfEase(allParkingSpacesPerFloor);
            List<ParkingSpace> allParkingSpacesPerFloorInOrderOfEaseList = new LinkedList(allParkingSpacesPerFloorInOrderOfEase);
            for (int i = 0; i < allParkingSpacesPerFloorInOrderOfEaseList.size(); i++) {
                ParkingSpace freeParkingSpots = allParkingSpacesPerFloorInOrderOfEaseList.get(i);
                if (!parkingAlreadyExists(allParkingSpaceFromParking, freeParkingSpots)) {
                    ParkingSpace freeParkingSpotCurrentRemaining = getParkingSpace(freeParkingSpots.getParkingNumber(),freeParkingSpots.getParkingLevel().equals(0) ? 1 : 0);
                    ParkingSpace freeParkingSpotsPrevUp =getParkingSpace(String.valueOf(Integer.parseInt(freeParkingSpots.getParkingNumber()) - 1),1);
                    ParkingSpace freeParkingSpotsPrevDown = getParkingSpace(String.valueOf(Integer.parseInt(freeParkingSpots.getParkingNumber()) - 1),0);
                    ParkingSpace freeParkingSpotsNextUp = getParkingSpace(String.valueOf(Integer.parseInt(freeParkingSpots.getParkingNumber()) + 1),1);
                    ParkingSpace freeParkingSpotsNextDown = getParkingSpace(String.valueOf(Integer.parseInt(freeParkingSpots.getParkingNumber()) + 1),0);
                    if (checkForRoyal(allParkingSpaceFromParking, freeParkingSpotsPrevUp, freeParkingSpotsPrevDown, freeParkingSpotsNextUp, freeParkingSpotsNextDown, freeParkingSpotCurrentRemaining)) {
                        return getParkingSpaceDTO(freeParkingSpots, floor.getFloorNumber());
                    }
                }
            }
        }
        return null;
    }
}
