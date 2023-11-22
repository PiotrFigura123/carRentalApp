package pl.piotrFigura.backendcarrental.repository;

import pl.piotrFigura.backendcarrental.model.CarMarkEntity;

import java.util.List;
import java.util.Optional;

public interface CarMarkRepository {

    boolean existByMarkName(String source);

    Optional<CarMarkEntity> findIdByMarkName(String mark);

    List<CarMarkEntity> findAll();

    CarMarkEntity save(CarMarkEntity source);

    void deleteById(Integer integer);
}
