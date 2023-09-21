package pl.piotrFigura.backendcarrental.service;

import pl.piotrFigura.backendcarrental.dao.Car;

import java.util.List;

public interface CarService {
    void toggleCar(Integer carId);

    List<Car> findAll();

    List<Car> findAvailableCars(boolean isAvailable);

    String getCarById(Integer id);

    String save(Car source);

    String updateCar(Integer id, Car source);

    String deleteCarById(Integer id);
}
