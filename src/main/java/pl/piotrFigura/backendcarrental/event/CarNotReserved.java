package pl.piotrFigura.backendcarrental.event;

import pl.piotrFigura.backendcarrental.model.CarEntity;

import java.time.Clock;

public class CarNotReserved extends ReservationEvent {
    CarNotReserved(CarEntity car) {
        super(car.getCarId(), Clock.systemDefaultZone());
    }
}
