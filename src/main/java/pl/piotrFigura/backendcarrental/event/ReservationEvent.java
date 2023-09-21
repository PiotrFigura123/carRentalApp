package pl.piotrFigura.backendcarrental.event;

import lombok.Getter;
import pl.piotrFigura.backendcarrental.dao.Car;
import pl.piotrFigura.backendcarrental.model.CarEntity;

import java.time.Clock;
import java.time.Instant;

@Getter
public abstract class ReservationEvent {
    public static ReservationEvent changed(CarEntity car){
        return car.isAvailable() ? new CarReserved(car) : new CarNotReserved(car);
    }

    private int reservation_id;
    private Instant occurrence;

    ReservationEvent(int reservation_id, Clock clock) {
        this.reservation_id = reservation_id;
        this.occurrence = Instant.now(clock);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "reservation_id=" + reservation_id +
                ", occurrence=" + occurrence +
                '}';
    }
}
