package pl.piotrFigura.backendcarrental.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.piotrFigura.backendcarrental.exception.RecordNotFoundProcessing;
import pl.piotrFigura.backendcarrental.service.MarkService;

@Slf4j
@RecordNotFoundProcessing
@RestController
@RequiredArgsConstructor
@RequestMapping(("/api/v1/addMark"))
public class MarkController {

    private final MarkService markService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAllMarks(){
        return ResponseEntity.ok().body(markService.getAllMarks());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<?> addMark(@RequestParam("carMark") String carMark){
        String message = markService.save(carMark);
        return ResponseEntity.ok().body(message);
    }
}
