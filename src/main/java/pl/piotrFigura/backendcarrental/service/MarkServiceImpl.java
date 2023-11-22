package pl.piotrFigura.backendcarrental.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.piotrFigura.backendcarrental.dao.CarMark;
import pl.piotrFigura.backendcarrental.exception.RecordNotFoundException;
import pl.piotrFigura.backendcarrental.mapper.CarMapper;
import pl.piotrFigura.backendcarrental.model.CarMarkEntity;
import pl.piotrFigura.backendcarrental.repository.CarMarkRepository;
import pl.piotrFigura.backendcarrental.response.ValidatorErrorEnum;
import pl.piotrFigura.backendcarrental.validator.FirstLvlValidator;
import pl.piotrFigura.backendcarrental.validator.SecondLvlValidation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MarkServiceImpl implements MarkService {

    private final FirstLvlValidator firstLevelValidator;
    private final SecondLvlValidation secondLvlValidation;
    private final CarMarkRepository carMarkRepository;

    @Override
    public List<CarMark> getAllMarks() {
        List<CarMark> cars = new ArrayList<>();
        carMarkRepository.findAll().stream().forEach(entity -> cars.add(CarMapper.INSTANCE.map(entity)));
        return cars;

    }

    @Override
    public String save(String source) {
        firstLevelValidator.validateMark(source);
        secondLvlValidation.validDuplicatedMark(source);
        CarMarkEntity carMarkEntity = new CarMarkEntity();
        carMarkEntity.setMark(source.toUpperCase());
        carMarkRepository.save(carMarkEntity);
        return "Car mark saved: " + source.toUpperCase();
    }

    @Override
    public String deleteCarMark(String markName) {
        CarMarkEntity carMark = carMarkRepository.findIdByMarkName(markName)
                .orElseThrow(()->
                        new RecordNotFoundException(ValidatorErrorEnum.MARK_DO_NOT_EXIST.getValue()));
        carMarkRepository.deleteById(carMark.getMarkId());
        return "Car mark removed: " + carMark.getMark();


    }
}
