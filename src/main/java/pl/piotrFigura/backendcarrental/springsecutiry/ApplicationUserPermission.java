package pl.piotrFigura.backendcarrental.springsecutiry;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ApplicationUserPermission {
    ADD_CITY("add:city"),
    ADD_MARK("add:mark"),
    ADD_CAR("add:car"),
    GET_CAR("get:car");
    private final String permission;

}
