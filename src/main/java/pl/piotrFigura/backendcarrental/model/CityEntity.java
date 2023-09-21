package pl.piotrFigura.backendcarrental.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Table(name = "cities")
@Entity
public class CityEntity implements Serializable {

    public CityEntity(String cityName) {
        this.cityName = cityName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cityId;
    private String cityName;

    @OneToMany(mappedBy = "cityEntity")
    private List<CarEntity> carEntity;
}
