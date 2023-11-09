package pl.piotrFigura.backendcarrental.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.piotrFigura.backendcarrental.CarRentalIT;

import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode;

@ExtendWith(MockitoExtension.class)
@CarRentalIT
public class CityServiceImplIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Sql(scripts = "classpath:cityServiceImplIT/delete_data.sql",
            config = @SqlConfig(transactionMode = TransactionMode.ISOLATED,
                    dataSource = "dataSourceData"))
    public void firstIT() {
        System.out.println("ala");
    }
}