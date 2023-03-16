package y88.kirill.mslocationsevents.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import y88.kirill.mslocationsevents.dto.LocationDTO;
import y88.kirill.mslocationsevents.services.LocationsService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/locations")
@RequiredArgsConstructor
public class LocationsController {

    private final LocationsService locationsService;

    @GetMapping()
    public ResponseEntity<List<LocationDTO>> getAllLocations(@RequestParam(name = "page") int page,
                                                       @RequestParam(name = "pageSize") int pageSize){
        return ResponseEntity.ok( locationsService.findAllWithPage(page,pageSize));
    }

    //todo only test
    @GetMapping("/temp")
    public ResponseEntity<List<LocationDTO>> getAll(){
        return ResponseEntity.ok( locationsService.findAll());
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/test")
    public ResponseEntity<?> test (){
        return ResponseEntity.of(Optional.of(String.format("какой то тестовый метод из локаций")));
    }


}
