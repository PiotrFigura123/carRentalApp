package pl.piotrFigura.backendcarrental.mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.piotrFigura.backendcarrental.dao.Car;
import pl.piotrFigura.backendcarrental.dao.CarMark;
import pl.piotrFigura.backendcarrental.dao.City;
import pl.piotrFigura.backendcarrental.dao.PlateNumber;
import pl.piotrFigura.backendcarrental.model.CarEntity;
import pl.piotrFigura.backendcarrental.model.CarMarkEntity;
import pl.piotrFigura.backendcarrental.model.CityEntity;
import pl.piotrFigura.backendcarrental.model.PlateNumberEntity;

@Component
@Slf4j
@RequiredArgsConstructor
public class CarMapperImpl implements CarMapper {

    @Override
    public CarEntity map(Car source) {
        if (source == null) {
            return null;
        }
        CarEntity carEntity = new CarEntity();
        carEntity.setModel(source.getModel());
        carEntity.setEngine(source.getEngine());
        carEntity.setPetrol(source.getPetrol());
        carEntity.setAvailable(source.isAvailable());
        carEntity.getPlateNumberEntity().setRegistration(
                source.getPlateNumber().getRegistration());

        carEntity.setAvailable(true);
        return carEntity;
    }


    @Override
    public CarMarkEntity map(CarMark source) {
        return null;
    }

    @Override
    public CarMark map(CarMarkEntity source) {
        CarMark carMark = new CarMark();
        carMark.setMarkId(source.getMarkId());
        carMark.setMark(source.getMark());
        return carMark;
    }

    @Override
    public Car map(CarEntity carEntity) {
        if (carEntity == null) {
            return null;
        }
        PlateNumber plateNumberEntity = new PlateNumber();
        plateNumberEntity.setRegistration(carEntity.getPlateNumberEntity().getRegistration());
        CarMark carMark = new CarMark();
        carMark.setMark(carEntity.getMarkEntity().getMark());
        City city = new City();
        city.setCityName(carEntity.getCityEntity().getCityName());

        Car car = new Car();
        car.setId(carEntity.getCarId());
        car.setModel(carEntity.getModel());
        car.setEngine(carEntity.getEngine());
        car.setPetrol(carEntity.getPetrol());
        car.setAvailable(carEntity.isAvailable());
        car.setPlateNumber(plateNumberEntity);
        car.setCity(city);
        car.setMark(carMark);
        return car;
    }

    @Override
    public CarEntity updateCarFromDto(Car car, CarEntity carEntity) {


        if (car.getModel() != null) {
            carEntity.setModel(car.getModel());
        }
        if (car.getEngine() != null) {
            carEntity.setEngine(car.getEngine());
        }
        if (car.getPetrol() != null) {
            carEntity.setPetrol(car.getPetrol());
        }
        carEntity.setAvailable(car.isAvailable());

        if (car.getPlateNumber() != null) {
            PlateNumberEntity plateNumberEntity = new PlateNumberEntity();
            plateNumberEntity.setRegistration(car.getPlateNumber().getRegistration());
            plateNumberEntity.setPlateId(carEntity.getPlateNumberEntity().getPlateId());
            carEntity.setPlateNumberEntity(plateNumberEntity);
        }

        return carEntity;
    }
}
