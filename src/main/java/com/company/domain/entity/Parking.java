package com.company.domain.entity;

import com.company.enums.ParkingType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;


@Entity
@Table(name = "parkings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column
    Long parkingLotId;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    Vehicle vehicle;

    @Column
    Integer parkingNumber;

    @Column
    Integer parkingLevel;

    @Column
    Integer floorNumber;

    @Column
    Long startTime;

    @Column
    Long endTime;

    @Column
    ParkingType parkingType;

}