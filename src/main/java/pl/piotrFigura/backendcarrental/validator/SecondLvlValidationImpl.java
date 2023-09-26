package pl.piotrFigura.backendcarrental.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.piotrFigura.backendcarrental.dao.Car;
import pl.piotrFigura.backendcarrental.exception.NotCorrectBodyException;
import pl.piotrFigura.backendcarrental.exception.RecordNotFoundException;
import pl.piotrFigura.backendcarrental.model.CarMarkEntity;
import pl.piotrFigura.backendcarrental.model.CityEntity;
import pl.piotrFigura.backendcarrental.repository.CarMarkRepository;
import pl.piotrFigura.backendcarrental.repository.CityNameRepository;
import pl.piotrFigura.backendcarrental.response.ValidatorErrorEnum;

@RequiredArgsConstructor
@Component
public class SecondLvlValidationImpl implements SecondLvlValidation {

    private final CarMarkRepository carMarkRepository;
    private final CityNameRepository cityNameRepository;

    @Override
    public void validateDuplicatedCityName(String entity) {
        if(cityNameRepository.existByCityName(entity)){
            throw new NotCorrectBodyException(ValidatorErrorEnum.CITY_EXIST_IN_TABLE.getValue());
        }

    }


    @Override
    public void validDuplicatedMark(String source) {
        if (carMarkRepository.existByMarkName(source.toUpperCase())) {
            throw new NotCorrectBodyException(ValidatorErrorEnum.MARK_EXIST_IN_TABLE.getValue());
        }
    }
    @Override
    public CarMarkEntity validateCarMarkName(Car toCreate) {
        return carMarkRepository.findIdByMarkName(toCreate.getMark().getMark().toUpperCase())
                .orElseThrow(
                        () -> new RecordNotFoundException(ValidatorErrorEnum.MARK_DO_NOT_EXIST.getValue())
                );
    }

    @Override
    public CityEntity validateCityName(Car toCreate) {
        return cityNameRepository.findIdByCityName(toCreate.getCity().getCityName().toUpperCase())
                .orElseThrow(
                        () -> new RecordNotFoundException(ValidatorErrorEnum.CITY_DO_NOT_EXIST.getValue())
                );
    }
}
