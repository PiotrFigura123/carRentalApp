package pl.piotrFigura.backendcarrental.validator;

import org.springframework.stereotype.Component;
import pl.piotrFigura.backendcarrental.dao.Car;
import pl.piotrFigura.backendcarrental.exception.NotCorrectBodyException;
import pl.piotrFigura.backendcarrental.exception.RecordNotFoundException;
import pl.piotrFigura.backendcarrental.model.CarMarkEntity;
import pl.piotrFigura.backendcarrental.model.CityEntity;
import pl.piotrFigura.backendcarrental.response.ValidatorErrorEnum;

@Component
public class FirstLvlValidationImpl implements FirstLvlValidator {

    private static final int MAXIMUM_LENGTH_ENGINE_NAME = 5;
    private static final int MAXIMUM_LENGTH_MODEL_NAME = 20;
    private static final int MAXIMUM_LENGTH_MARK_NAME = 20;
    private static final int MAXIMUM_LENGTH_CITY_NAME = 20;

    @Override
    public void validSource(Car source) {
        validPetrol(source.getPetrol());
        validEngine(source.getEngine());
        validModel(source.getModel());
        validateMark(source.getMark().getMark());
    }

    @Override
    public void validateCityName(String sourceCity) {
        if (sourceCity == null || sourceCity.isBlank()) {

            throw new NotCorrectBodyException(ValidatorErrorEnum.CITY_CANT_BE_EMPTY.getValue());
        }
        if(sourceCity.length()> MAXIMUM_LENGTH_CITY_NAME){
            throw new NotCorrectBodyException(sourceCity + ValidatorErrorEnum.TOO_LONG_NAME.getValue());
        }
    }

    @Override
    public void validateMark(String mark) {
        if (mark == null || mark.isBlank()) {

            throw new NotCorrectBodyException(ValidatorErrorEnum.MARK_DO_NOT_EXIST.getValue());
        }
        if(mark.length()> MAXIMUM_LENGTH_MARK_NAME){
            throw new NotCorrectBodyException(mark + ValidatorErrorEnum.TOO_LONG_NAME.getValue());
        }
    }

    @Override
    public void validModel(String model) {
        if (model == null || model.isBlank()) {

            throw new NotCorrectBodyException(ValidatorErrorEnum.MODEL_IS_NOT_CORRECT.getValue());
        }
        if(model.length()> MAXIMUM_LENGTH_MODEL_NAME){
            throw new NotCorrectBodyException(model + ValidatorErrorEnum.TOO_LONG_NAME.getValue());
        }
    }

    @Override
    public void validEngine(String engine) {
        if (engine == null || engine.isEmpty()) {
            throw new NotCorrectBodyException(ValidatorErrorEnum.ENGINE_IS_NOT_CORRECT.getValue());
        }
        if(engine.length()> MAXIMUM_LENGTH_ENGINE_NAME){
            throw new NotCorrectBodyException(engine + ValidatorErrorEnum.TOO_LONG_NAME.getValue());
        }
    }

    @Override
    public void validPetrol(String petrol) {
        if (petrol == null || petrol.isEmpty()) {
            throw new NotCorrectBodyException(ValidatorErrorEnum.PETROL_IS_NOT_CORRECT.getValue());
        }
    }


}
