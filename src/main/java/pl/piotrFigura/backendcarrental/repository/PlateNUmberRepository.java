package pl.piotrFigura.backendcarrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.piotrFigura.backendcarrental.model.PlateNumberEntity;

@Repository
interface PlateNUmberRepository extends JpaRepository<PlateNumberEntity, Integer> {
}
