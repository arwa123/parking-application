package  com.company.service.parkingPreference;

import com.company.domain.dto.FloorDTO;
import com.company.domain.dto.ParkingSpaceDTO;
import com.company.domain.entity.Floor;
import com.company.domain.entity.Parking;
import com.company.domain.entity.ParkingSpace;

import java.util.List;
import java.util.Set;

import static com.company.util.ParkingHelper.*;

public class ElderPreferenceImpl implements IParkingPreference {

    @Override
    public ParkingSpaceDTO findNearestSlot(List<Parking> parkings, List<Floor> floors) {
        for (Floor floor : floors) {
            List<Parking> parkedOnFloor = getAllParkingOnFloor(parkings, floor.getFloorNumber());
            Set<ParkingSpace> parkedSpacesOnFloor = getAllParkingSpaceFromParking(parkedOnFloor);
            List<ParkingSpace> allParkingSpacesPerFloor = floor.getParkingSpaceList();
            Set<ParkingSpace> allParkingSpacesPerFloorInOrderOfEase = getFreeParkingSpaceInOrderOfEase(allParkingSpacesPerFloor);
            for (ParkingSpace slot :
                    allParkingSpacesPerFloorInOrderOfEase) {
                if (!parkingAlreadyExists(parkedSpacesOnFloor, slot)) {
                    if (slot.getParkingLevel().equals(0)) {
                        ParkingSpaceDTO parkingSpaceDTO = new ParkingSpaceDTO();
                        parkingSpaceDTO.setFloorNumber(floor.getFloorNumber());
                        parkingSpaceDTO.setParkingLevel(slot.getParkingLevel());
                        parkingSpaceDTO.setParkingNumber(slot.getParkingNumber());
                        return parkingSpaceDTO;
                    }
                }
            }
        }
        return null;
    }
}
