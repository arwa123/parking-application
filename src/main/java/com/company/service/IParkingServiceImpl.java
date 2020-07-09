package com.company.service;

import com.company.domain.dto.ParkingRequestDTO;
import com.company.domain.dto.ParkingSpaceDTO;
import com.company.domain.dto.VehicleDTO;
import com.company.domain.entity.Floor;
import com.company.domain.entity.Parking;
import com.company.domain.entity.ParkingSpace;
import com.company.domain.entity.Vehicle;
import com.company.domain.repository.ParkingLotRepository;
import com.company.domain.repository.ParkingRepository;
import com.company.domain.repository.VehicleRepository;
import com.company.enums.ParkingType;
import com.company.service.parkingPreference.IParkingPreference;
import com.company.service.parkingPreference.RoyalPreferenceImpl;
import com.company.service.parkingPreference.ParkingPreferenceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import static com.company.util.ParkingHelper.getParkingSpace;
import static com.company.util.ParkingHelper.getParkingSpaceDTO;

@Service
public class IParkingServiceImpl implements IParkingService {
    @Autowired
    private
    ParkingRepository parkingRepository;

    @Autowired
    private
    ParkingLotRepository parkingLotRepository;

    @Autowired
    private
    VehicleRepository vehicleRepository;

    @Override
    public List<Parking> findAll() {
        return parkingRepository.findAll();
    }

    @Override
    public Parking findById(Long parkingId) {
        return parkingRepository.findById(parkingId)
                .orElse(null);
    }

    @Override
    public Parking save(Parking parkingDetails) {
        return parkingRepository.save(parkingDetails);
    }

    @Override
    public void delete(Parking parking) {
        parkingRepository.delete(parking);
    }

    @Override
    public Parking createParking(ParkingRequestDTO parkingRequestDto) throws Exception {
        ParkingType parkingType = parkingRequestDto.getParkingType();
        List<Floor> floors = parkingLotRepository.findById(parkingRequestDto.getParkingLotId()).get().getFloorList();
        Optional<List<Parking>> parkings = parkingRepository.findByParkingLotIdAndEndTimeGreaterThan(parkingRequestDto.getParkingLotId(), new Date().getTime());
        IParkingPreference parkingPreference = ParkingPreferenceFactory.getParkingType(parkingType);
        ParkingSpaceDTO parkingSpaceDTO = parkingPreference.findNearestSlot(parkings.orElse(new ArrayList<>()), floors);
        if (parkingSpaceDTO == null) {
            throw new Exception("No Parking Found");
        }
        if (parkingPreference instanceof RoyalPreferenceImpl) {
            ParkingSpace freeParkingSpotCurrentRemaining = getParkingSpace(parkingSpaceDTO.getParkingNumber(), parkingSpaceDTO.getParkingLevel().equals(0) ? 1 : 0);
            ParkingSpaceDTO freeParkingSpotCurrentRemainingDTO = getParkingSpaceDTO(freeParkingSpotCurrentRemaining, parkingSpaceDTO.getFloorNumber());
            save(getParkingFromDTO(freeParkingSpotCurrentRemainingDTO, null, parkingRequestDto.getParkingType(), parkingRequestDto.getParkingLotId()));

            ParkingSpace freeParkingSpotsPrevUp = getParkingSpace(String.valueOf(Integer.parseInt(parkingSpaceDTO.getParkingNumber()) - 1), 1);
            ParkingSpaceDTO freeParkingSpotsPrevUpDTO = getParkingSpaceDTO(freeParkingSpotsPrevUp, parkingSpaceDTO.getFloorNumber());
            save(getParkingFromDTO(freeParkingSpotsPrevUpDTO, null, parkingRequestDto.getParkingType(), parkingRequestDto.getParkingLotId()));

            ParkingSpace freeParkingSpotsPrevDown = getParkingSpace(String.valueOf(Integer.parseInt(parkingSpaceDTO.getParkingNumber()) - 1), 0);
            ParkingSpaceDTO freeParkingSpotsPrevDownDTO = getParkingSpaceDTO(freeParkingSpotsPrevDown, parkingSpaceDTO.getFloorNumber());
            save(getParkingFromDTO(freeParkingSpotsPrevDownDTO, null, parkingRequestDto.getParkingType(), parkingRequestDto.getParkingLotId()));

            ParkingSpace freeParkingSpotsNextUp = getParkingSpace(String.valueOf(Integer.parseInt(parkingSpaceDTO.getParkingNumber()) + 1), 1);
            ParkingSpaceDTO freeParkingSpotsNextUpDTO = getParkingSpaceDTO(freeParkingSpotsNextUp, parkingSpaceDTO.getFloorNumber());
            save(getParkingFromDTO(freeParkingSpotsNextUpDTO, null, parkingRequestDto.getParkingType(), parkingRequestDto.getParkingLotId()));

            ParkingSpace freeParkingSpotsNextDown = getParkingSpace(String.valueOf(Integer.parseInt(parkingSpaceDTO.getParkingNumber()) + 1), 0);
            ParkingSpaceDTO freeParkingSpotsNextDownDTO = getParkingSpaceDTO(freeParkingSpotsNextDown, parkingSpaceDTO.getFloorNumber());
            save(getParkingFromDTO(freeParkingSpotsNextDownDTO, null, parkingRequestDto.getParkingType(), parkingRequestDto.getParkingLotId()));

        }
        return save(getParkingFromDTO(parkingSpaceDTO, parkingRequestDto.getVehicle(), parkingRequestDto.getParkingType(), parkingRequestDto.getParkingLotId()));
    }

    @Override
    public Parking freeUpParking(VehicleDTO vehicleDTO) {
        Vehicle topByVehicleNumberPlateAndVehicleTypeOrderByIdDesc = vehicleRepository.findTopByVehicleNumberPlateAndVehicleTypeOrderByIdDesc(vehicleDTO.getVehicleNumberPlate(), vehicleDTO.getVehicleType()).get();
        Optional<Parking> parking = parkingRepository.findByVehicle(topByVehicleNumberPlateAndVehicleTypeOrderByIdDesc);
        parking.get().setEndTime(new Date().getTime());
        if (ParkingType.ROYAL == parking.get().getParkingType()) {
            Optional<Parking> parking1 = parkingRepository.findByParkingLotIdAndFloorNumberAndParkingNumberAndParkingLevelAndType(parking.get().getParkingLotId()
                    , parking.get().getFloorNumber(), parking.get().getParkingNumber(), parking.get().getParkingLevel().equals(0) ? 1 : 0, parking.get().getParkingType());
            Optional<Parking> parking2 = parkingRepository.findByParkingLotIdAndFloorNumberAndParkingNumberAndParkingLevelAndType(parking.get().getParkingLotId()
                    , parking.get().getFloorNumber(), String.valueOf(Integer.parseInt(parking.get().getParkingNumber()) - 1), 1, parking.get().getParkingType());
            Optional<Parking> parking3 = parkingRepository.findByParkingLotIdAndFloorNumberAndParkingNumberAndParkingLevelAndType(parking.get().getParkingLotId()
                    , parking.get().getFloorNumber(), String.valueOf(Integer.parseInt(parking.get().getParkingNumber()) - 1), 0, parking.get().getParkingType());
            Optional<Parking> parking4 = parkingRepository.findByParkingLotIdAndFloorNumberAndParkingNumberAndParkingLevelAndType(parking.get().getParkingLotId()
                    , parking.get().getFloorNumber(), String.valueOf(Integer.parseInt(parking.get().getParkingNumber()) + 1), 1, parking.get().getParkingType());
            Optional<Parking> parking5 = parkingRepository.findByParkingLotIdAndFloorNumberAndParkingNumberAndParkingLevelAndType(parking.get().getParkingLotId()
                    , parking.get().getFloorNumber(), String.valueOf(Integer.parseInt(parking.get().getParkingNumber()) + 1), 0, parking.get().getParkingType());
            parking1.get().setEndTime(new Date().getTime());
            parking2.get().setEndTime(new Date().getTime());
            parking3.get().setEndTime(new Date().getTime());
            parking4.get().setEndTime(new Date().getTime());
            parking5.get().setEndTime(new Date().getTime());

        }
        return save(parking.get());
    }

    private Parking getParkingFromDTO(ParkingSpaceDTO parkingSpaceDTO, VehicleDTO vehicleDTO, ParkingType parkingType, Long parkingLotId) {
        Parking parking = new Parking();
        parking.setStartTime(new Date().getTime());
        if (vehicleDTO != null) {
            Vehicle vehicle = new Vehicle();
            vehicle.setVehicleType(vehicleDTO.getVehicleType());
            vehicle.setVehicleNumberPlate(vehicleDTO.getVehicleNumberPlate());
            parking.setVehicle(vehicle);
        }

        parking.setParkingType(parkingType);
        parking.setParkingLotId(parkingLotId);
        parking.setFloorNumber(parkingSpaceDTO.getFloorNumber());
        parking.setParkingNumber(parkingSpaceDTO.getParkingNumber());
        parking.setParkingLevel(parkingSpaceDTO.getParkingLevel());
        return parking;
    }
}
