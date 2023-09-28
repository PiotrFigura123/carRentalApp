package pl.piotrFigura.backendcarrental.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import pl.piotrFigura.backendcarrental.dao.Car;
import pl.piotrFigura.backendcarrental.exception.RecordNotFoundException;
import pl.piotrFigura.backendcarrental.model.CarEntity;
import pl.piotrFigura.backendcarrental.repository.CarRepository;
import pl.piotrFigura.backendcarrental.CarRentalIT;
import pl.piotrFigura.backendcarrental.validator.FirstLvlValidator;
import pl.piotrFigura.backendcarrental.validator.SecondLvlValidation;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static pl.piotrFigura.backendcarrental.response.ValidatorErrorEnum.RECORD_NOT_FOUND;

import java.util.*;
import java.util.stream.Collectors;



class CarServiceImplTest {

    @InjectMocks
    private CarServiceImpl carService;

    @Spy
    private FirstLvlValidator firstLvlValidation;

    @Spy
    private SecondLvlValidation secondLvlValidation;

    @Spy
    private CarRepository carRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldReturnAllDataFromTable() {
        //given
        List<CarEntity> carEntities = prepareData();

        //when
        when(carRepository.findAll()).thenReturn(carEntities);

        List<Car> cars = carService.findAll();

        //than
        assertEquals(carEntities.size(), cars.size());
        verify(carRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnOnlyAvailableCars() {
        //given
        boolean dataToCheck = true;
        List<CarEntity> carEntities = prepareData();
        carEntities.get(0).setAvailable(dataToCheck);

        //when
        when(carRepository.findCarsByAvailable(dataToCheck)).thenReturn(
                carEntities
                        .stream()
                        .filter(a -> a.isAvailable() == dataToCheck)
                        .collect(Collectors.toList()));

        List<Car> cars = carService.findAvailableCars(dataToCheck);
        //than
        assertEquals(1, cars.size());
        assertFalse(carEntities.get(1).isAvailable());

    }

    @Test
    void shouldThrowExceptionWhenCarDoNoExistById() {
        //given
        List<CarEntity> carEntities = prepareData();
        carEntities.get(0).setCarId(1);
        carEntities.get(1).setCarId(2);
        carEntities.get(2).setCarId(3);
        int id = 4;
        //when
        when(carRepository.findById(id)).thenReturn(
                carEntities.stream()
                        .filter(entity ->
                                entity.getCarId().equals(id)).findFirst());
        //than
        RecordNotFoundException thrown = assertThrows(RecordNotFoundException.class,
                () -> carService.getCarById(id));
        assertTrue(thrown.getMessage().contains(RECORD_NOT_FOUND.getValue()));
    }

    @Test
    void shouldFindSingleEntity(){
        //given
        List<CarEntity> carEntities = prepareData();
        carEntities.get(0).setCarId(1);
        int id=1;
        //when
        when(carRepository.findById(id)).thenReturn(
                carEntities.stream()
                        .filter(entity ->
                                entity.getCarId().equals(id)).findFirst());
        //than
        String car = carService.getCarById(id);
        assertThat(car).contains("CarEntity{" +
                "id=" + id +
                ", model");
    }

    private List<CarEntity> prepareData() {
        List<CarEntity> carEntities = new ArrayList<>();
        carEntities.add(new CarEntity());
        carEntities.add(new CarEntity());
        carEntities.add(new CarEntity());
        return carEntities;
    }

}