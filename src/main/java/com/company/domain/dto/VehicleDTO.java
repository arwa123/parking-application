package com.company.domain.dto;

import com.company.enums.VehicleType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class VehicleDTO {

    private String vehicleNumberPlate;
    private VehicleType vehicleType;


}
