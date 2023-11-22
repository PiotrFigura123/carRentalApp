package pl.piotrFigura.backendcarrental.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import pl.piotrFigura.backendcarrental.CarRentalIT;
import pl.piotrFigura.backendcarrental.exception.NotCorrectBodyException;
import pl.piotrFigura.backendcarrental.response.ValidatorErrorEnum;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;

@ExtendWith(MockitoExtension.class)
@CarRentalIT
public class CityServiceImplIT {

    public static final String REMOVE_DATA_PATH = "classpath:CityServiceImplIT/delete_data.sql";
    public static final String ADD_WARSZAWA_PATH = "classpath:CityServiceImplIT/add_warszawa.sql";
    public static final String TOO_LONG_CITY_NAME = "LONGERTHENTWENTYCHARS";
    public static final String CITY_NAME = "Warszawa";

    @Autowired
    private CityService cityService;

    @Test
    @Sql(scripts = REMOVE_DATA_PATH,
            config = @SqlConfig(transactionMode = ISOLATED,
                    dataSource = "dataSourceData"))
    @DisplayName("Should add city to database")
    public void firstCityServiceIT() {
        //given
        //when
        cityService.saveCity(CITY_NAME);
        //than
        assertThat(cityService.findAll().size()).isEqualTo(1);
        System.out.println("1");
    }

    @Test
    @Sql(scripts = REMOVE_DATA_PATH,
            config = @SqlConfig(transactionMode = ISOLATED,
                    dataSource = "dataSourceData"))
    @DisplayName("Should throw exception, city can't be empty")
    public void secondCityServiceIT() {
        //given
        String messageExpected = ValidatorErrorEnum.CITY_CANT_BE_EMPTY.getValue();
        //when
        NotCorrectBodyException messageOutput = assertThrows(NotCorrectBodyException.class,
                () -> cityService.saveCity(""));
        //then
        assertEquals(messageExpected, messageOutput.getMessage());
    }

    @Test
    @Sql(scripts = REMOVE_DATA_PATH,
            config = @SqlConfig(transactionMode = ISOLATED,
                    dataSource = "dataSourceData"))
    @DisplayName("Should throw exception, too long city name.")
    void thirdCityServiceIT() {
        //given
        String messageExpected = TOO_LONG_CITY_NAME + ValidatorErrorEnum.TOO_LONG_NAME.getValue();
        //when
        NotCorrectBodyException messageOutput = assertThrows(NotCorrectBodyException.class,
                () -> cityService.saveCity(TOO_LONG_CITY_NAME));
        //than
        assertEquals(messageExpected, messageOutput.getMessage());
    }

    @Test
    @Sql(scripts = REMOVE_DATA_PATH,
            config = @SqlConfig(transactionMode = ISOLATED,
                    dataSource = "dataSourceData"))
    @DisplayName("Should throw exception, duplicated cities")
    void fourthCityServiceIT() {
        //given
        String messageExpected = ValidatorErrorEnum.CITY_EXIST_IN_TABLE.getValue();
        //when
        NotCorrectBodyException messageOutput = assertThrows(NotCorrectBodyException.class,
                () -> {
                    cityService.saveCity(CITY_NAME);
                    cityService.saveCity(CITY_NAME);
                });
        assertEquals(messageExpected,messageOutput.getMessage());
    }

    @Test
    @Sql(scripts = {REMOVE_DATA_PATH, ADD_WARSZAWA_PATH},
            config = @SqlConfig(transactionMode = ISOLATED,
    dataSource = "dataSourceData"))
    @DisplayName("Should remove city from database")
    void fifthCityServiceIT(){
        //given
        //when
        cityService.removeCity(CITY_NAME);
        //then
        assertEquals(cityService.findAll().size(),0);
    }
}