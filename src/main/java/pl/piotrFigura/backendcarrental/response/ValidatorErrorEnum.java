package pl.piotrFigura.backendcarrental.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ValidatorErrorEnum {
    RECORD_NOT_FOUND("Record doesn't exist."),
    MARK_IS_NOT_CORRECT("Mark is not correct."),
    MODEL_IS_NOT_CORRECT("Model can't be empty."),
    ENGINE_IS_NOT_CORRECT("Engine can't be empty."),
    TOO_LONG_NAME(" is too long."),
    PETROL_IS_NOT_CORRECT("Petrol can't be empty."),
    MARK_DO_NOT_EXIST("Mark do not exist in database"),
    CITY_DO_NOT_EXIST("City do not exist in database"),
    MARK_EXIST_IN_TABLE("Mark already exist in database"),
    CITY_CANT_BE_EMPTY("City can't be empty"),
    CITY_EXIST_IN_TABLE("City already exist in database");
    private final String value;
}
