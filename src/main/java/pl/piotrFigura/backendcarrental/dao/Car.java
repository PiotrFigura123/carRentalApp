package pl.piotrFigura.backendcarrental.dao;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Car implements Serializable {
    private Integer id;
    private String model;
    private String engine;
    private String petrol;
    private boolean available;
    private PlateNumber plateNumber;
    private CarMark mark;
    private City city;
}
