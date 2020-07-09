package com.company.domain.entity;


import com.company.enums.VehicleType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;


@Entity
@Table(name = "vehicles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String vehicleNumberPlate;

    @Column
    private VehicleType vehicleType;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "vehicle")
    @JsonBackReference
    private Parking parking;


}
