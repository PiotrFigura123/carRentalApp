package pl.piotrFigura.backendcarrental.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "plate_numbers")
public class PlateNumberEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plate_id")
    private Integer plateId;
    private String registration;

    @OneToOne(mappedBy = "plateNumberEntity")
    private CarEntity carEntity;

}
