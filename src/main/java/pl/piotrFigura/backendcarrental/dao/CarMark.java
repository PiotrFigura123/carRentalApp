package pl.piotrFigura.backendcarrental.dao;

import lombok.Data;

import java.io.Serializable;

@Data
public class CarMark implements Serializable {
    private Integer markId;
    private String mark;
}
