package pl.piotrFigura.backendcarrental.validator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.piotrFigura.backendcarrental.exception.NotCorrectBodyException;
import pl.piotrFigura.backendcarrental.response.ValidatorErrorEnum;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FirstLvlValidationImplTest {

    @Spy
    FirstLvlValidationImpl firstLvlValidation;

    private static final String EMPTY_STRING = "";
    private static final String NAME_LONGER_THAN_20_CHARS = "tooLongCityNametooLongCityName";
    private static final String PROPER_CITY_NAME = "Warszawa";
    private static final String NAME_LONGER_THAN_5_CHARS = "sixcha";
    private static final String PROPER_ENGINE_NAME = "1.40L";
    private static final String PROPER_MODEL_NAME = "Corsa";
    private static final String PROPER_MARK_NAME = "Opel";

    @Test
    public void cityNameShouldNotBeEmpty() {
        NotCorrectBodyException thrown = assertThrows(NotCorrectBodyException.class,
                () -> {
                    firstLvlValidation.validateCityName(EMPTY_STRING);
                });
        assertTrue(thrown.getMessage().contains(ValidatorErrorEnum.CITY_CANT_BE_EMPTY.getValue()));
    }

    @Test
    public void cityNameShouldNotBeLongerThan20Chars() {
        NotCorrectBodyException thrown = assertThrows(NotCorrectBodyException.class,
                () ->
                        firstLvlValidation.validateCityName(NAME_LONGER_THAN_20_CHARS)
        );
        assertTrue(thrown.getMessage().contains(ValidatorErrorEnum.TOO_LONG_NAME.getValue()));
    }

    @Test
    public void properCityName() {
        assertDoesNotThrow(() ->
                firstLvlValidation.validateCityName(PROPER_CITY_NAME));
    }

    @Test
    public void petrolShouldNotBeEmpty() {
        NotCorrectBodyException thrown = assertThrows(NotCorrectBodyException.class,
                () -> firstLvlValidation.validPetrol(EMPTY_STRING));
        assertTrue(thrown.getMessage().contains(ValidatorErrorEnum.PETROL_IS_NOT_CORRECT.getValue()));
    }

    @Test
    public void engineShouldNotBeEmpty() {
        NotCorrectBodyException thrown = assertThrows(NotCorrectBodyException.class,
                () -> firstLvlValidation.validEngine(EMPTY_STRING));
        assertTrue(thrown.getMessage().contains(ValidatorErrorEnum.ENGINE_IS_NOT_CORRECT.getValue()));
    }

    @Test
    public void engineShouldNotBeLongerThanFiveChar() {
        NotCorrectBodyException thrown = assertThrows(NotCorrectBodyException.class,
                () -> firstLvlValidation.validEngine(NAME_LONGER_THAN_5_CHARS));
        assertTrue(thrown.getMessage().contains(ValidatorErrorEnum.TOO_LONG_NAME.getValue()));
    }

    @Test
    public void properEngineName() {
        assertDoesNotThrow(() -> firstLvlValidation.validEngine(PROPER_ENGINE_NAME));
    }

    @Test
    public void modelShouldNotBeEmpty() {
        NotCorrectBodyException thrown = assertThrows(NotCorrectBodyException.class,
                () -> firstLvlValidation.validModel(EMPTY_STRING));
        thrown.getMessage().contains(ValidatorErrorEnum.MODEL_IS_NOT_CORRECT.getValue());
    }

    @Test
    public void modelNameChouldNotBeLongerThan20Chars() {
        NotCorrectBodyException thrown = assertThrows(NotCorrectBodyException.class,
                () -> firstLvlValidation.validModel(NAME_LONGER_THAN_20_CHARS));
        thrown.getMessage().contains(ValidatorErrorEnum.TOO_LONG_NAME.getValue());
    }

    @Test
    public void markNameChouldNotBeLongerThan20Chars() {
        NotCorrectBodyException thrown = assertThrows(NotCorrectBodyException.class,
                () -> firstLvlValidation.validateMark(NAME_LONGER_THAN_20_CHARS));
        thrown.getMessage().contains(ValidatorErrorEnum.TOO_LONG_NAME.getValue());
    }

    @Test
    public void modelNameShouldNotThrowAnyException() {
        assertDoesNotThrow(() -> firstLvlValidation.validModel(PROPER_MODEL_NAME));
    }

    @Test
    public void markNameShouldNotThrowAnyException() {
        assertDoesNotThrow(() -> firstLvlValidation.validateMark(PROPER_MARK_NAME));
    }
}