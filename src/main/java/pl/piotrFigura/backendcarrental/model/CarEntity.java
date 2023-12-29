package pl.piotrFigura.backendcarrental.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.piotrFigura.backendcarrental.event.ReservationEvent;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "cars")
public class CarEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Integer carId;
    private String model;
    private String engine;
    private String petrol;
    private boolean available;

    @ManyToOne()
    @JoinColumn(name = "mark_id" )
    private CarMarkEntity markEntity;

    @ManyToOne()
    @JoinColumn(name = "city_id")
    private CityEntity cityEntity;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "plate_id")
    private PlateNumberEntity plateNumberEntity;

    @Embedded
    @Column(name = "auditableEntity")
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
