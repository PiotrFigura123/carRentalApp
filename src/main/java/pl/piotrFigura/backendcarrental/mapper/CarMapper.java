package pl.piotrFigura.backendcarrental.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import pl.piotrFigura.backendcarrental.dao.Car;
import pl.piotrFigura.backendcarrental.dao.CarMark;
import pl.piotrFigura.backendcarrental.model.CarEntity;
import pl.piotrFigura.backendcarrental.model.CarMarkEntity;


public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);


    @Mapping(source = "plateNumber", target = "plateNumberEntity")
    CarEntity map(Car source);


    CarMarkEntity map(CarMark source);
    CarMark map(CarMarkEntity source);


    @Mapping(source = "plateNumberEntity", target = "plateNumber")
    @Mapping(source = "markEntity", target = "mark")
    Car map(CarEntity carEntity);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CarEntity updateCarFromDto(Car car, @MappingTarget CarEntity carEntity);
}