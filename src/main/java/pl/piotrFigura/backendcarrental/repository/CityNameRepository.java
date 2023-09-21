package pl.piotrFigura.backendcarrental.repository;

import pl.piotrFigura.backendcarrental.model.CarMarkEntity;
import pl.piotrFigura.backendcarrental.model.CityEntity;

import java.util.List;
import java.util.Optional;

public interface CityNameRepository {

    Optional<CityEntity> findIdByCityName(String cityName);

    List<CityEntity> findAll();

    boolean existByCityName(String source);


    CityEntity save(CityEntity source);


}
