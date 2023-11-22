package pl.piotrFigura.backendcarrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.piotrFigura.backendcarrental.model.CarMarkEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarMarkRepositoryImpl extends CarMarkRepository, JpaRepository<CarMarkEntity, Integer> {


    @Override
    @Query(nativeQuery = true, value = "SELECT * FROM car_marks")
    List<CarMarkEntity> findAll();

    @Query(nativeQuery = true, value = "SELECT count(*)>0 FROM car_marks WHERE mark = :source")
    @Override
    boolean existByMarkName(String source);

    @Override
    @Query(nativeQuery = true, value = "SELECT * FROM car_marks WHERE mark = :source")
    Optional<CarMarkEntity> findIdByMarkName(String source);

    @Override
    void deleteById(Integer integer);

    }
