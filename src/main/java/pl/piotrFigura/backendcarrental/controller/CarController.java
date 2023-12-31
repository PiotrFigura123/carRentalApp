package pl.piotrFigura.backendcarrental.controller;

import io.micrometer.core.annotation.Timed;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import pl.piotrFigura.backendcarrental.dao.Car;
import pl.piotrFigura.backendcarrental.exception.RecordNotFoundProcessing;
import pl.piotrFigura.backendcarrental.service.CarService;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;




@Slf4j
@RecordNotFoundProcessing
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/cars")
class CarController {

    private final CarService carService;

    @GetMapping()
    ResponseEntity<?> getAllCars() {
        log.info("Find all cars");
        return ResponseEntity.ok(carService.findAll());
    }

    @GetMapping(params = "available")
    ResponseEntity<?> getAllAvailableCars(@RequestParam("available") boolean isAvailable) {
        log.info("Find all avalaible = {} cars", isAvailable);
        List<Car> cars = carService.findAvailableCars(isAvailable);
        return ResponseEntity.ok().body(cars);
    }

    @GetMapping(params = "id")
    ResponseEntity<?> getCar(@RequestParam("id") Integer id) {
        log.info("Searching car by id {}", id);
        String applicationMessage = carService.getCarById(id);

        return ResponseEntity.ok().body(applicationMessage);
    }

    @Secured({"ROLE_ADMIN", "ROLE_BRANCH"})
    @PostMapping
    ResponseEntity<?> createCar(@RequestBody @Valid Car toCreate) {
        return ResponseEntity.ok().body(carService.save(toCreate));
    }

    @ResponseBody
    @Transactional
    @PatchMapping("/{id}")
    ResponseEntity<?> toggleCar(@PathVariable Integer id) {
        log.info("Toggle car number {}", id);
        carService.toggleCar(id);
        return ResponseEntity.noContent().build();
    }

    @Timed(value = "car.update", histogram = true,
            percentiles = {0.5, 0.95, 0.99})
    @PutMapping("/{id}")
    ResponseEntity<?> updateCar(@PathVariable Integer id, @RequestBody @Valid Car source) {
         String message = carService.updateCar(id, source);
        return ResponseEntity.ok().body(message);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteCarId(@PathVariable Integer id) {
        String applicationMessage = carService.deleteCarById(id);
        return ResponseEntity.ok().body(applicationMessage);
    }
}
