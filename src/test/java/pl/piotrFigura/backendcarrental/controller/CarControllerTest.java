package pl.piotrFigura.backendcarrental.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.piotrFigura.backendcarrental.dao.Car;
import pl.piotrFigura.backendcarrental.service.CarService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CarControllerTest {

    @InjectMocks
    private CarController carController;

    @Mock
    private CarService carService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(carController).build();
    }

    @Test
    void shouldReturnAllCarsWithOkStatus() throws Exception {
        //given
        List<Car> sampleCars = getCarsList();

        //when
        when(carService.findAll()).thenReturn(sampleCars);

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/cars")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(sampleCars.size()));

        verify(carService, times(1)).findAll();
        assertThat(sampleCars.size()).isEqualTo(2);
    }

    private List<Car> getCarsList() {
        List<Car> sampleCars = new ArrayList<>();
        sampleCars.add(new Car());
        sampleCars.add(new Car());
        return sampleCars;
    }
}