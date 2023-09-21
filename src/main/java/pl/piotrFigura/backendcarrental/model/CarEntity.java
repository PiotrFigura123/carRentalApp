package pl.piotrFigura.backendcarrental.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.piotrFigura.backendcarrental.event.ReservationEvent;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "cars")
public class CarEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer carId;
    private String model;
    private String engine;
    private String petrol;
    private boolean available;

    @ManyToOne()
    @JoinColumn(name = "mark_id" )
    private CarMarkEntity markEntity = new CarMarkEntity();

    @ManyToOne()
    @JoinColumn(name = "city_id")
    private CityEntity cityEntity = new CityEntity();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "plate_id")
    private PlateNumberEntity plateNumberEntity = new PlateNumberEntity();

    @Embedded
    private AuditableEntity auditableEntity = new AuditableEntity();

    @Override
    public String toString() {
        return "CarEntity{" +
                "id=" + carId +
                ", model='" + model + '\'' +
                ", engine='" + engine + '\'' +
                ", petrol='" + petrol + '\'' +
                ", available=" + available +
                '}';
    }

    public ReservationEvent toggle(){
        this.available = !this.available;
        return ReservationEvent.changed(this);
    }
}
