package  com.company.domain.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.List;


@Entity
@Table(name = "parking_lots")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parkingLot")
    @JsonManagedReference
    private List<Floor> floorList;

}
