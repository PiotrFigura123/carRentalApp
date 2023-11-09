package pl.piotrFigura.backendcarrental.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlateNumber implements Serializable {
    private String registration;
}
