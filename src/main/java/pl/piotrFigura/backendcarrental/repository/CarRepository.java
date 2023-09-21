package pl.piotrFigura.backendcarrental.repository;

import pl.piotrFigura.backendcarrental.model.CarEntity;

import java.util.List;
import java.util.Optional;

public interface CarRepository {

    List<CarEntity> findAll();

    List<CarEntity> findCarsByAvailable(boolean available);

    Optional<CarEntity> findById(Integer id);

    CarEntity save(CarEntity car);

    void deleteById(Integer id);
}
