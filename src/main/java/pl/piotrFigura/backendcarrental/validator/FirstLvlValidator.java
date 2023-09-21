package pl.piotrFigura.backendcarrental.validator;

import pl.piotrFigura.backendcarrental.dao.Car;

public interface FirstLvlValidator {
    void validModel(String model);
    void validPetrol(String petrol);
    void validEngine(String engine);
    void validateMark(String source);
    void validSource(Car source);
    void validateCityName(String entity);
}
