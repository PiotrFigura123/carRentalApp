package pl.piotrFigura.backendcarrental.service;


import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import pl.piotrFigura.backendcarrental.CarRentalIT;
import pl.piotrFigura.backendcarrental.dao.Car;
import pl.piotrFigura.backendcarrental.dao.CarMark;
import pl.piotrFigura.backendcarrental.dao.City;
import pl.piotrFigura.backendcarrental.exception.RecordNotFoundException;
import pl.piotrFigura.backendcarrental.repository.CarRepository;
import pl.piotrFigura.backendcarrental.response.ValidatorErrorEnum;

import java.io.*;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;

@ExtendWith(MockitoExtension.class)
@CarRentalIT
public class CarServiceIT {

    public static final String REMOVE_DATA_PATH = "classpath:CityServiceImplIT/delete_data.sql";
    public static final String ADD_CAR_MARK_PATH = "classpath:CityServiceImplIT/add_car_mark.sql";
    public static final String SAMPLE_CAR_JSON_PATH = "jsonFiles/sampleCar.json";
    public static final String ADD_WARSZAWA_PATH = "classpath:CityServiceImplIT/add_warszawa.sql";
    public static final String UNVALID_NAME = "unvalid";
    @Autowired
    private CarService carService;
    @Autowired
    private CarRepository carRepository;

    @Test
    @Sql(scripts = {REMOVE_DATA_PATH, ADD_CAR_MARK_PATH, ADD_WARSZAWA_PATH},
            config = @SqlConfig(transactionMode = ISOLATED,
                    dataSource = "dataSourceData"))
    @DisplayName("Should add car to database")
    void firstIT() {
        //given
        Car car = loadDataFromJson();
        //when
        carService.save(car);
        //then
        assertThat(carService.findAll().size()).isEqualTo(1);
    }

    @Test
    @Sql(scripts = {REMOVE_DATA_PATH, ADD_WARSZAWA_PATH},
            config = @SqlConfig(transactionMode = ISOLATED,
                    dataSource = "dataSourceData"))
    @DisplayName("Should throw exception, car mark doesn't exist.")
    void secondIT() {
        //given
        String messageExpected = ValidatorErrorEnum.MARK_DO_NOT_EXIST.getValue();
        Car car = loadDataFromJson();
        CarMark mark = new CarMark();
        mark.setMark(UNVALID_NAME);
        car.setMark(mark);
        //when
        RecordNotFoundException recordNotFoundException = assertThrows(RecordNotFoundException.class,
                () -> carService.save(car));
        //then
        assertEquals(messageExpected, recordNotFoundException.getMessage());
    }

    @Test
    @Sql(scripts = {REMOVE_DATA_PATH, ADD_CAR_MARK_PATH},
            config = @SqlConfig(transactionMode = ISOLATED,
                    dataSource = "dataSourceData"))
    @DisplayName("Should throw exception, car mark doesn't exist.")
    void thirdIT() {
        //given
        String messageExpected = ValidatorErrorEnum.CITY_DO_NOT_EXIST.getValue();
        Car car = loadDataFromJson();
        City city = new City();
        city.setCityName(UNVALID_NAME);
        car.setCity(city);
        //when
        RecordNotFoundException recordNotFoundException = assertThrows(RecordNotFoundException.class,
                () -> carService.save(car));
        //then
        assertEquals(messageExpected, recordNotFoundException.getMessage());
    }

    private Car loadDataFromJson() {
        Gson gson = new Gson();
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(SAMPLE_CAR_JSON_PATH);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(resourceAsStream))) {
            return gson.fromJson(br, Car.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
