package pl.piotrFigura.backendcarrental.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.piotrFigura.backendcarrental.dao.CarMark;
import pl.piotrFigura.backendcarrental.service.MarkService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class MarkControllerTest {

    @InjectMocks
    private MarkController markController;
    @Mock
    private MarkService markService;
    private MockMvc mockMvc;
    private static final String CAR_MARK = "WW";
    public static final String CAR_MARK_API = "/api/v1/addMark";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(markController).build();
    }

    @Test
    void shouldReturnAllCarMarks() throws Exception {
        //given
        List<CarMark> carMarkList = getCarMarkList();
        //when
        when(markService.getAllMarks()).thenReturn(carMarkList);
        //then
        mockMvc.perform(MockMvcRequestBuilders.get(CAR_MARK_API))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(carMarkList.size()));
    }

    @Test
    void shouldAddCarMarkToDatabase() throws Exception {
        //given
        List<CarMark> carMarkList = new ArrayList<>();
        carMarkList.add(new CarMark(1, CAR_MARK));
        //when
        when(markService.save(CAR_MARK)).thenReturn("Car mark saved:" + CAR_MARK);
        //then
        mockMvc.perform(MockMvcRequestBuilders.post(CAR_MARK_API).param("carMark", "WW"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN));
    }
    @Test
    void shouldReturnStatus400AfterReceivingRequestWithoutBody() throws Exception {
        //given
        //when
        ResultActions resultActions= mockMvc.perform(MockMvcRequestBuilders.post(CAR_MARK_API)
                .contentType(MediaType.TEXT_PLAIN)
                .accept(MediaType.TEXT_PLAIN));
        //then
        resultActions.andExpect(status().is4xxClientError());
    }

    private List<CarMark> getCarMarkList() {
        List<CarMark> carMarks = new ArrayList<>();
        carMarks.add(new CarMark(1,"WW"));
        carMarks.add(new CarMark(2, "OPEL"));
        return carMarks;
    }
}