package com.company.domain.dto;

import com.company.enums.ParkingType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParkingRequestDTO {
    private Long parkingLotId;
    private VehicleDTO vehicle;
    ParkingType parkingType;
    private Boolean isCarPooled;


}