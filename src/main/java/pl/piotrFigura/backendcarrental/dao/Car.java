package pl.piotrFigura.backendcarrental.dao;

import lombok.Data;

import java.io.Serializable;

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
