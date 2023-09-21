package pl.piotrFigura.backendcarrental.service;

import pl.piotrFigura.backendcarrental.dao.CarMark;

import java.util.List;

public interface MarkService {

    List<CarMark> getAllMarks();

    String save(String entity);

}
