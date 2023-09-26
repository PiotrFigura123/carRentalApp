package pl.piotrFigura.backendcarrental.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.piotrFigura.backendcarrental.dao.Car;
import pl.piotrFigura.backendcarrental.exception.RecordNotFoundException;
import pl.piotrFigura.backendcarrental.mapper.CarMapper;
import pl.piotrFigura.backendcarrental.model.CarEntity;
import pl.piotrFigura.backendcarrental.model.CarMarkEntity;
import pl.piotrFigura.backendcarrental.model.CityEntity;
import pl.piotrFigura.backendcarrental.repository.CarRepository;
import pl.piotrFigura.backendcarrental.response.ValidatorErrorEnum;
import pl.piotrFigura.backendcarrental.validator.FirstLvlValidator;
import pl.piotrFigura.backendcarrental.validator.SecondLvlValidation;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CarServiceImpl implements CarService {

    private final FirstLvlValidator firstLvlValidation;
    private final SecondLvlValidation secondLvlValidation;
    private final CarRepository carRepository;

    public void toggleCar(Integer carId) {
        CarEntity result = carRepository.findById(carId)
                .orElseThrow(() -> new RecordNotFoundException(ValidatorErrorEnum.RECORD_NOT_FOUND.getValue()));

        result.toggle();
        log.info("Car with id: {} , availability is {}", result.getCarId(), result.isAvailable());
        carRepository.save(result);
    }

    public List<Car> findAll() {
        List<Car> cars = new ArrayList<>();
        List<CarEntity> carEntities = carRepository.findAll();
        carEntities.stream().forEach(
                carEntity -> cars.add(CarMapper.INSTANCE.map(carEntity)));
        return cars;
    }

    public List<Car> findAvailableCars(boolean isAvailable) {
        List<Car> cars = new ArrayList<>();
        carRepository.findCarsByAvailable(isAvailable)
                .forEach(entity -> cars.add(CarMapper.INSTANCE.map(entity)));
        return cars;
    }

    public String getCarById(Integer id) {
        CarEntity entity = carRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(ValidatorErrorEnum.RECORD_NOT_FOUND.getValue()));
        return entity.toString();

    }

    public String save(Car source) {
        firstLvlValidation.validSource(source);
        CarMarkEntity carMarkEntity = secondLvlValidation.validateCarMarkName(source);
        CityEntity cityEntity = secondLvlValidation.validateCityName(source);
        CarEntity carEntity = CarMapper.INSTANCE.map(source);
        carEntity.setMarkEntity(carMarkEntity);
        carEntity.setCityEntity(cityEntity);
        carRepository.save(carEntity);
        return "Car saved in db";
    }

    public String updateCar(Integer id, Car source) {
        CarMarkEntity carMarkEntity = secondLvlValidation.validateCarMarkName(source);
        CityEntity cityNameEntity = secondLvlValidation.validateCityName(source);
        CarEntity carEntity = carRepository.findById(id)
                .orElseThrow(
                        () -> new RecordNotFoundException(ValidatorErrorEnum.RECORD_NOT_FOUND.getValue())
                );
        CarMapper.INSTANCE.updateCarFromDto(source, carEntity);
        carEntity.setMarkEntity(carMarkEntity);
        carEntity.setCityEntity(cityNameEntity);
        log.info("Update car id = {}", id);
        carRepository.save(carEntity);
        return carEntity.toString();

    }


    public String deleteCarById(Integer id) {
        CarEntity carEntity = carRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(ValidatorErrorEnum.RECORD_NOT_FOUND.getValue()));

        log.info("Deleting car id = {}", id);
        carRepository.deleteById(id);
        return "Deleted : " + carEntity.toString();
    }

}
