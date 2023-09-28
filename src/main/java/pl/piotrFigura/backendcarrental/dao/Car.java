package pl.piotrFigura.backendcarrental.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
