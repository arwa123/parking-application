package  com.company.service.parkingPreference;


import com.company.domain.dto.FloorDTO;
import com.company.domain.dto.ParkingSpaceDTO;
import com.company.domain.entity.Floor;
import com.company.domain.entity.Parking;
import com.company.domain.entity.ParkingSpace;

import java.util.List;
import java.util.Set;

import static com.company.util.ParkingHelper.*;

public class NoPreferenceImpl implements IParkingPreference {
    @Override
    public ParkingSpaceDTO findNearestSlot(List<Parking> parkings, List<Floor> floors) {
        for (Floor floor : floors) {
            List<Parking> parkingsOnFloor=getAllParkingOnFloor(parkings, floor.getFloorNumber());
            Set<ParkingSpace> allParkingSpaceFromParking = getAllParkingSpaceFromParking(parkingsOnFloor);
            List<ParkingSpace> allParkingSpacesPerFloor = floor.getParkingSpaceList();
            Set<ParkingSpace> allParkingSpacesPerFloorInOrderOfEase = getFreeParkingSpaceInOrderOfEase(allParkingSpacesPerFloor);
            for (ParkingSpace freeParkingSpots :
                    allParkingSpacesPerFloorInOrderOfEase) {
                if (!parkingAlreadyExists(allParkingSpaceFromParking, freeParkingSpots)) {
                    ParkingSpaceDTO parkingSpaceDTO = new ParkingSpaceDTO();
                    parkingSpaceDTO.setFloorNumber(floor.getFloorNumber());
                    parkingSpaceDTO.setParkingLevel(freeParkingSpots.getParkingLevel());
                    parkingSpaceDTO.setParkingNumber(freeParkingSpots.getParkingNumber());
                    return parkingSpaceDTO;
                }
            }
        }
        return null;
    }



}
