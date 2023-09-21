package pl.piotrFigura.backendcarrental.event;

import pl.piotrFigura.backendcarrental.model.CarEntity;

import java.time.Clock;

public class CarReserved extends ReservationEvent{
    CarReserved( CarEntity car) {
        super(car.getCarId(), Clock.systemDefaultZone());
    }
}
