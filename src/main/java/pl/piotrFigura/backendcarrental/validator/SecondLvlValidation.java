package pl.piotrFigura.backendcarrental.validator;

import pl.piotrFigura.backendcarrental.dao.Car;
import pl.piotrFigura.backendcarrental.model.CarMarkEntity;
import pl.piotrFigura.backendcarrental.model.CityEntity;

public interface SecondLvlValidation {

    void validCarMarkIfExistInDb(Car mark);

    void validDuplicatedMark(String source);

    void validateDuplicatedCityName(String entity);

    CarMarkEntity validateCarMarkName(Car toCreate);

    CityEntity validateCityName(Car toCreate);
}
