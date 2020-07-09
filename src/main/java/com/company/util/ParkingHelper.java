package com.company.util;

import com.company.domain.dto.ParkingSpaceDTO;
import com.company.domain.entity.Parking;
import com.company.domain.entity.ParkingSpace;

import java.util.*;
import java.util.stream.Collectors;

public class ParkingHelper {
    public static Set<ParkingSpace> getAllParkingSpaceFromParking(List<Parking> parkings) {
        Set<ParkingSpace> parkingSpaces = new HashSet<>();
        for (Parking parking :
                parkings) {
            ParkingSpace parkingSpace = new ParkingSpace();
            parkingSpace.setParkingNumber(parking.getParkingNumber());
            parkingSpace.setParkingLevel(parking.getParkingLevel());
            parkingSpaces.add(parkingSpace);
        }
        return parkingSpaces;
    }

    public static Set<ParkingSpace> getFreeParkingSpaceInOrderOfEase(List<ParkingSpace> parkingSpaces) {
        Set<ParkingSpace> freeParkingNumberWithLevel = new TreeSet<>(Comparator.comparing(ParkingSpace::getEaseOfParking).thenComparing(ParkingSpace::getParkingLevel).thenComparing(ParkingSpace::getParkingNumber));
        freeParkingNumberWithLevel.addAll(parkingSpaces);
        return freeParkingNumberWithLevel;
    }

    public static List<Parking> getAllParkingOnFloor(List<Parking> parkings, Integer floorNumber) {
        return parkings.stream().filter(p -> p.getFloorNumber().equals(floorNumber)).collect(Collectors.toList());
    }

    public static boolean parkingAlreadyExists(Set<ParkingSpace> allParkingSpaceFromParking, ParkingSpace freeParkingSpots) {
        return allParkingSpaceFromParking.stream().anyMatch(p -> (p.getParkingLevel().equals(freeParkingSpots.getParkingLevel()) && p.getParkingNumber().equals(freeParkingSpots.getParkingNumber())));
    }

    public static boolean checkForRoyal(Set<ParkingSpace> allParkingSpaceFromParking, ParkingSpace freeParkingSpotsPrevUp, ParkingSpace freeParkingSpotsPrevDown,
                                        ParkingSpace freeParkingSpotsNextUp, ParkingSpace freeParkingSpotsNextDown, ParkingSpace freeParkingSpotCurrentRemaining) {
        if (!parkingAlreadyExists(allParkingSpaceFromParking, freeParkingSpotsPrevUp) && !parkingAlreadyExists(allParkingSpaceFromParking, freeParkingSpotsPrevDown)
                && !parkingAlreadyExists(allParkingSpaceFromParking, freeParkingSpotsNextUp) && !parkingAlreadyExists(allParkingSpaceFromParking, freeParkingSpotsNextDown)
                && !parkingAlreadyExists(allParkingSpaceFromParking, freeParkingSpotCurrentRemaining)) {
            return true;
        }
        return false;
    }
    public static ParkingSpace getParkingSpace(String parkingNum, Integer level) {
        ParkingSpace parkingSpace = new ParkingSpace();
        parkingSpace.setParkingLevel(level);
        parkingSpace.setParkingNumber(parkingNum);
        return parkingSpace;
    }
    public static ParkingSpaceDTO getParkingSpaceDTO(ParkingSpace parkingSpace, Integer floorNumber) {
        ParkingSpaceDTO parkingSpaceDTO = new ParkingSpaceDTO();
        parkingSpaceDTO.setParkingLevel(parkingSpace.getParkingLevel());
        parkingSpaceDTO.setParkingNumber(parkingSpace.getParkingNumber());
        parkingSpaceDTO.setFloorNumber(floorNumber);
        return parkingSpaceDTO;
    }
}
