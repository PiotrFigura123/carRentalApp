package pl.piotrFigura.backendcarrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.piotrFigura.backendcarrental.model.CarEntity;

import java.util.List;
import java.util.Optional;

@Repository
interface CarRepositoryImpl extends CarRepository, JpaRepository<CarEntity, Integer> {
    
    List<CarEntity> findAll();

    Optional<CarEntity> findById(Integer id);

    @Query(nativeQuery = true, value = "SELECT * FROM cars WHERE available= :available")
    List<CarEntity> findCarsByAvailable(boolean available);

    CarEntity save(CarEntity car);

    void deleteById(Integer integer);
}
