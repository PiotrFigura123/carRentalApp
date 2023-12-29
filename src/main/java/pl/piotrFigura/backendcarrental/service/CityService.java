package pl.piotrFigura.backendcarrental.service;

import pl.piotrFigura.backendcarrental.dao.City;

import java.util.List;

public interface CityService {

    String saveCity(String cityName);

    List<String> findAll();

    String removeCity(String cityName);
}
