package com.company.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;


@Entity
@Table(name = "parking_spaces")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParkingSpace {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String parkingNumber;

    @Column
    private Integer parkingLevel;

    @Column
    private Integer easeOfParking;

    @ManyToOne
    @JsonBackReference
    private Floor floor;

}
