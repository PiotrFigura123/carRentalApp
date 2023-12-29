package pl.piotrFigura.backendcarrental.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Table(name = "car_marks")
@Entity
public class CarMarkEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mark_id")
    private Integer markId;
    private String mark;


    @OneToMany(mappedBy = "markEntity")
    private Set<CarEntity> carEntity;
}
