package pl.piotrFigura.backendcarrental.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Table(name = "car_marks")
@Entity
public class CarMarkEntity implements Serializable{

    public CarMarkEntity(String mark) {
        this.mark = mark;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer markId;
    private String mark;


    @OneToMany(mappedBy = "markEntity")
    private List<CarEntity> carEntity;
}
