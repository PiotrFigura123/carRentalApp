package pl.piotrFigura.backendcarrental.validator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.piotrFigura.backendcarrental.repository.CarMarkRepository;
import pl.piotrFigura.backendcarrental.repository.CityNameRepository;

@ExtendWith(MockitoExtension.class)
class SecondLvlValidationImplIT {

    @Mock
    CarMarkRepository carMarkRepository;
    @Mock
    CityNameRepository cityNameRepository;

    @Test
    void validateDuplicatedCityName() {
    //given

        //when
        //then

    }

    @Test
    void validDuplicatedMark() {
    }

    @Test
    void validateCarMarkName() {
    }

    @Test
    void validateCityName() {
    }
}