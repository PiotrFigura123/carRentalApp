package pl.piotrFigura.backendcarrental.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CarMark implements Serializable {
    private Integer markId;
    private String mark;

    public CarMark(String mark) {
        this.mark = mark;
    }
}
