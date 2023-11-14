package pl.piotrFigura.backendcarrental.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.web.servlet.MockMvc;
import pl.piotrFigura.backendcarrental.CarRentalIT;

import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;

@ExtendWith(MockitoExtension.class)
@CarRentalIT
public class CityServiceImplIT {

    public static final String REMOVE_DATA = "classpath:CityServiceImplIT/delete_data.sql";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Sql(scripts = REMOVE_DATA,
            config = @SqlConfig(transactionMode = ISOLATED,
                    dataSource = "dataSourceData"))
    public void firstIT() {
        System.out.println("ala");
    }
}