package pl.piotrFigura.backendcarrental.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.piotrFigura.backendcarrental.exception.RecordNotFoundProcessing;
import pl.piotrFigura.backendcarrental.service.MarkService;

import javax.annotation.security.RolesAllowed;

@Slf4j
@RecordNotFoundProcessing
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/addMark")
public class MarkController {

    private final MarkService markService;

    @Secured({"ROLE_ADMIN", "ROLE_BRANCH"})
    @GetMapping
    public ResponseEntity<?> getAllMarks(){
        return ResponseEntity.ok().body(markService.getAllMarks());
    }

    @RolesAllowed({"ROLE_BRANCH", "ROLE_ADMIN"})
    @PostMapping
    public ResponseEntity<?> addMark(@RequestParam("carMark") String carMark){
        String message = markService.save(carMark);
        return ResponseEntity.ok().body(message);
    }
    @Secured({"ROLE_ADMIN", "ROLE_BRANCH"})
    @DeleteMapping
    public ResponseEntity<?> removeMark(@RequestParam("carMark") String carMark){
        String message = markService.deleteCarMark(carMark);
        return ResponseEntity.ok().body(message);
    }
}
