package pl.piotrFigura.backendcarrental.service;

import pl.piotrFigura.backendcarrental.model.CityEntity;

import java.util.List;

public interface CityService {

    String saveCity(String entity);

    List<String> findAll();

}
