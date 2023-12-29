package pl.piotrFigura.backendcarrental.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.piotrFigura.backendcarrental.dao.City;
import pl.piotrFigura.backendcarrental.exception.RecordNotFoundException;
import pl.piotrFigura.backendcarrental.model.CityEntity;
import pl.piotrFigura.backendcarrental.repository.CityNameRepository;
import pl.piotrFigura.backendcarrental.response.ValidatorErrorEnum;
import pl.piotrFigura.backendcarrental.validator.FirstLvlValidator;
import pl.piotrFigura.backendcarrental.validator.SecondLvlValidation;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class CityServiceImpl implements CityService {

    private final FirstLvlValidator firstLvlValidator;
    private final SecondLvlValidation secondLvlValidation;
    private final CityNameRepository cityNameRepository;

    @Override
    public String saveCity(String sourceCity) {
        sourceCity=sourceCity.toUpperCase();
        firstLvlValidator.validateCityName(sourceCity);
        secondLvlValidation.validateDuplicatedCityName(sourceCity);
        var cityEntity = new CityEntity();
        cityEntity.setCityName(sourceCity);
        cityNameRepository.save(cityEntity);
        return "City saved in db: " + sourceCity;
    }

    @Override
    public List<String> findAll() {
        return cityNameRepository.findAll()
                .stream()
                .map(cityEntity -> new City(cityEntity.getCityName()))
                .map(City::getCityName)
                .collect(Collectors.toList());
    }

    @Override
    public String removeCity(String cityName) {
        cityName=cityName.toUpperCase();
        City city = cityNameRepository.findFirstByCityName(cityName)
                .map(cityEntity -> new City(cityEntity.getCityId(), cityEntity.getCityName()))
                .orElseThrow(
                        ()-> new RecordNotFoundException(ValidatorErrorEnum.CITY_DO_NOT_EXIST.getValue()));
        cityNameRepository.deleteByCityId(city.getCityId());
        return "City removed from database: " + city.getCityName();
    }

}
