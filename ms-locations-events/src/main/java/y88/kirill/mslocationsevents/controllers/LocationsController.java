package y88.kirill.mslocationsevents.controllers;


import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import y88.kirill.corelib.dtos.LocationDTO;
import y88.kirill.corelib.dtos.LocationsInSector;
import y88.kirill.mslocationsevents.services.LocationsService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/locations")
@RequiredArgsConstructor
public class LocationsController {

    private final LocationsService locationsService;

    @GetMapping()
    @Operation(summary = "Получение списка всех локаций")
    public ResponseEntity<List<LocationDTO>> getAllLocations(@RequestParam(name = "page") int page,
                                                             @RequestParam(name = "pageSize") int pageSize){
        return ResponseEntity.ok( locationsService.findAllWithPage(page,pageSize));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение локации по id")
    public ResponseEntity<LocationDTO> getLocationById(@PathVariable Long id){
        return ResponseEntity.ok(locationsService.findById(id));
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


    @PostMapping(value = "/in-sector-and-subcategory", produces = "application/json")
    @Operation(summary = "Получение списка локаций с заданными подкатегориями в секторе ")
    public ResponseEntity<List<LocationDTO>> getAllByLocationsSubcategoryAndSector(@RequestBody LocationsInSector locationsInSector){

        return ResponseEntity.ok(locationsService.findAllByLocationsSubcategoryAndSector(locationsInSector.getLatitudeMin(),
                locationsInSector.getLatitudeMax(),
                locationsInSector.getLongitudeMin(),
                locationsInSector.getLongitudeMax(),
                locationsInSector.getCategories()));

    }

    @PostMapping(value = "/in-sector-and-category", produces = "application/json")
    @Operation(summary = "Получение списка локаций с заданными категориями в секторе ")
    public ResponseEntity<List<LocationDTO>> getAllByLocationsCategoryAndSector(@RequestBody LocationsInSector locationsInSector){

        return ResponseEntity.ok(locationsService.findAllByLocationsCategoryAndSector(locationsInSector.getLatitudeMin(),
                locationsInSector.getLatitudeMax(),
                locationsInSector.getLongitudeMin(),
                locationsInSector.getLongitudeMax(),
                locationsInSector.getCategories()));

    }

    @GetMapping("/manual-title-description")
    @Operation(summary = "Получение списка локаций содержащих часть поискового слова в названии или описании")
    public ResponseEntity<List<LocationDTO>> getAllByManualTitleAndDescription(@RequestParam(name = "manualTitle") String manualTitle,
                                                                               @RequestParam(name = "page") int page,
                                                                               @RequestParam(name = "pageSize") int pageSize){
        return ResponseEntity.ok(locationsService.findAllByManualTitleAndDescription(manualTitle, page, pageSize));
    }




}
