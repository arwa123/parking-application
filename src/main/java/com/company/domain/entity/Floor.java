package com.company.domain.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Table(name = "floors")
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Floor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Integer floorNumber;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "floor")
    @JsonManagedReference
    private List<ParkingSpace> parkingSpaceList;

    @ManyToOne
    @JsonBackReference
    private ParkingLot parkingLot;



}
