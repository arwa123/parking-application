package com.company.domain.dto;



import com.company.domain.entity.ParkingLot;
import com.company.domain.entity.ParkingSpace;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FloorDTO {
    private Long id;
    private Integer floorNumber;
    private List<ParkingSpaceDTO> parkingSpaceList;
    private ParkingLot parkingLot;

}
