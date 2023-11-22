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
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.*;

@ExtendWith(MockitoExtension.class)
@CarRentalIT
class MarkServiceIT {

    public static final String DELETE_DATA_PATH = "classpath:CityServiceImplIt/delete_data.sql";
    public static final String ADD_CAR_MARK_PATH = "classpath:CityServiceImplIT/add_car_mark.sql";
    public static final String CAR_MARK_FROM_DB = "OPEL";
    @Autowired
    private MarkService markService;

    @Test
    @Sql(scripts = {DELETE_DATA_PATH},
            config = @SqlConfig(transactionMode = ISOLATED, dataSource = "dataSourceData"))
    @DisplayName("Should add car mark into database")
    void firstMarkServiceIT() {
        //given
        //when
        markService.save("WW");
        //then
        assertThat(markService.getAllMarks().size()).isEqualTo(1);
    }

    @Test
    @Sql(scripts = {DELETE_DATA_PATH, ADD_CAR_MARK_PATH},
            config = @SqlConfig(transactionMode = ISOLATED, dataSource = "dataSourceData"))
    @DisplayName("Should remove car mark from database")
    void secondMarkServiceIT() {

        //given
        //when
        markService.deleteCarMark(CAR_MARK_FROM_DB);
        //then
        assertThat(markService.getAllMarks().size()).isEqualTo(0);
    }

    @Test
    @Sql(scripts = {DELETE_DATA_PATH, ADD_CAR_MARK_PATH},
            config = @SqlConfig(transactionMode = ISOLATED, dataSource = "dataSourceData"))
    @DisplayName("Should throw exception, car mark duplicated")
    void thirdMarkServiceIT() {
        //given
        String messageExpected = ValidatorErrorEnum.MARK_EXIST_IN_TABLE.getValue();
        //when
        NotCorrectBodyException messageOutput = assertThrows(
                NotCorrectBodyException.class, () -> markService.save(CAR_MARK_FROM_DB));
        //then
        assertEquals(messageExpected, messageOutput.getMessage());
    }
}