package pl.piotrFigura.backendcarrental.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class City implements Serializable {
    private Integer cityId;
    private String cityName;

    public City(String cityName) {
        this.cityName = cityName;
    }
}
