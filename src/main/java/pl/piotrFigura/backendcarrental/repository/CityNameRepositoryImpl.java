package pl.piotrFigura.backendcarrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.piotrFigura.backendcarrental.model.CityEntity;

import java.util.Optional;

@Repository
public interface CityNameRepositoryImpl extends CityNameRepository, JpaRepository<CityEntity, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM cities where city_name = :cityName")
    @Override
    Optional<CityEntity> findIdByCityName(String cityName);

    @Query(nativeQuery = true, value = "SELECT count(*)>0 FROM cities where city_name = :source")
    @Override
    boolean existByCityName(String source);

    @Override
    Optional<CityEntity> findFirstByCityName(String cityName);

    @Override
    void deleteByCityId(Integer cityId);
}

