package pl.piotrFigura.backendcarrental.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.piotrFigura.backendcarrental.exception.RecordNotFoundProcessing;
import pl.piotrFigura.backendcarrental.service.CityService;

import java.security.Principal;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@Slf4j
@RecordNotFoundProcessing
@RestController
@RequiredArgsConstructor
@RequestMapping(("/api/v1/city"))
public class CityController {

    private final CityService cityService;

    @GetMapping
    public ResponseEntity<?> getAllCities() {
        return ResponseEntity.ok(cityService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> addCity(@RequestParam("cityName") String cityName) {
        String message = cityService.saveCity(cityName);
        return ResponseEntity.ok().body(message);
    }
}
