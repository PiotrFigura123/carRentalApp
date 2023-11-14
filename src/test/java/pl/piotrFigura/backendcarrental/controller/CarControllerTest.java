package pl.piotrFigura.backendcarrental.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.piotrFigura.backendcarrental.dao.Car;
import pl.piotrFigura.backendcarrental.service.CarService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CarControllerTest {

    @InjectMocks
    private CarController carController;

    @Mock
    private CarService carService;
    private MockMvc mockMvc;
    private static final String filePath = "src/test/resources/jsonFiles/";
    private static final String CARS_API = "/api/v1/cars";

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
        mockMvc.perform(MockMvcRequestBuilders.get(CARS_API)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(sampleCars.size()));
        verify(carService, times(1)).findAll();
        assertThat(sampleCars.size()).isEqualTo(2);
    }

    @Test
    void shouldReturnOnlyAvailableCars() throws Exception {
        //given
        List<Car> sampleCarList = getCarsList();
        boolean isCarAvailable = true;
        sampleCarList.get(0).setAvailable(true);

        //when
        List<Car> filtered = sampleCarList
                .stream().filter(
                        entity -> entity.isAvailable() == isCarAvailable)
                .collect(Collectors.toList());
        when(carService.findAvailableCars(isCarAvailable)).thenReturn(filtered);

        //than
        mockMvc.perform(MockMvcRequestBuilders.get(CARS_API).param("available", String.valueOf(isCarAvailable))
                .content(String.valueOf(MediaType.APPLICATION_JSON)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void shouldReturnCarWithDefinedId() throws Exception {
        //given
        List<Car> sampleCarList = getCarsList();
        Integer sampleId = 1;
        sampleCarList.get(0).setId(sampleId);

        //when
        when(carService.getCarById(sampleId))
                .thenReturn(sampleCarList
                        .stream().filter(
                                car -> car.getId() == sampleId)
                        .collect(Collectors.toList()).toString());

        //than
        mockMvc.perform(MockMvcRequestBuilders.get(CARS_API).param("id", String.valueOf(sampleId))
                .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(content().string(Matchers.containsString("[Car(id=" + sampleId)));
    }

    @Test
    void carCreatedShouldReturnStatusCreated() throws Exception {
        //given
        Car car2 = readJsonFile(filePath + "sampleCar.json");

        //when
        //than
        mockMvc.perform(MockMvcRequestBuilders.post(CARS_API)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(asJsonString(car2)))
                .andExpect(status().is2xxSuccessful());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Car readJsonFile(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(filePath), Car.class);
    }

    private List<Car> getCarsList() {
        List<Car> sampleCars = new ArrayList<>();
        sampleCars.add(new Car());
        sampleCars.add(new Car());
        return sampleCars;
    }
}