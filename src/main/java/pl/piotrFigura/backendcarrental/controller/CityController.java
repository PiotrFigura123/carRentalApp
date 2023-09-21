package pl.piotrFigura.backendcarrental.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.piotrFigura.backendcarrental.exception.RecordNotFoundProcessing;
import pl.piotrFigura.backendcarrental.service.CityService;

@Slf4j
@RecordNotFoundProcessing
@RestController
@RequiredArgsConstructor
@RequestMapping(("/api/v1/city"))
public class CityController {

    private final CityService cityService;

    @GetMapping
    @PreAuthorize("hasAuthority('add:city')")
    public ResponseEntity<?> getAllCities() {
        return ResponseEntity.ok(cityService.findAll());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('add:city')")
    public ResponseEntity<?> addCity(@RequestParam("cityName") String cityName) {
        String message = cityService.saveCity(cityName);
        return ResponseEntity.ok().body(message);
    }
}
