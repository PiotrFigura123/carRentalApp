package pl.piotrFigura.backendcarrental.service;

import java.util.List;

public interface CityService {

    String saveCity(String cityName);

    List<String> findAll();

    String removeCity(String cityName);
}
