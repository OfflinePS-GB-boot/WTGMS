package y88.kirill.msroutes.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import y88.kirill.corelib.dtos.LocationDTO;
import y88.kirill.corelib.dtos.LocationsInSector;
import y88.kirill.corelib.feigns.FeignLocations;
import y88.kirill.msroutes.dto.LocationsByCategoriesAndSector;
import y88.kirill.msroutes.servises.RouteService;

import java.util.*;


@RestController
@RequestMapping("api/v1/routes")
@RequiredArgsConstructor
@EnableHystrix
public class RouterController {

    private final RestTemplate restTemplate;
    private final RouteService routeService;
    private final FeignLocations feignLocations;



//    @HystrixCommand(fallbackMethod = "fallGetSectorCoordinates")
//    @PostMapping(value = "/locations-by-categories-and-sector", consumes = "application/json")
//    public List<LocationDTO> getSectorCoordinates(@RequestBody LocationsByCategoriesAndSector lcs) throws JsonProcessingException {
//        System.out.println("address  " + lcs.getAddress());
//
//        double[] coordinates =  routeService.getCoordinatesSector(lcs.getAddress(), lcs.getRadius());
//        LocationsInSector locationsInSector = new LocationsInSector(coordinates[0],coordinates[1],coordinates[2],coordinates[3], lcs.getCategories());
//
//        ResponseEntity<LocationDTO[]> ldtos = restTemplate.postForEntity("http://ms-locations-events/locations-events/api/v1/in-sector-and-subcategory",locationsInSector, LocationDTO[].class);
//
//        return Arrays.asList(Objects.requireNonNull(ldtos.getBody()));
//    }
//
//
//
//    public List<LocationDTO> fallGetSectorCoordinates(){
//        return List.of(new LocationDTO());
//    }



    @PostMapping(value = "/locations-by-subcategories-and-sector", consumes = "application/json")
    @Operation(summary = "Получение списка локаций с заданными подкатегориями в радиусе от текущего местоположения")
    public ResponseEntity<List<LocationDTO>> getLocationsBySubcategories(@RequestBody LocationsByCategoriesAndSector lcs) throws JsonProcessingException {
        System.out.println("address  " + lcs.getAddress());

        double[] coordinates =  routeService.getCoordinatesSector(lcs.getAddress(), lcs.getRadius());
        LocationsInSector locationsInSector = new LocationsInSector(coordinates[0],coordinates[1],coordinates[2],coordinates[3], lcs.getCategories());

        ResponseEntity<List<LocationDTO>> ldtos = feignLocations.getAllByLocationsSubcategoryAndSector(locationsInSector);

        return ResponseEntity.ok(ldtos.getBody());
    }

    @PostMapping(value = "/locations-by-categories-and-sector", consumes = "application/json")
    @Operation(summary = "Получение списка локаций с заданными категориями в радиусе от текущего местоположения")
    public ResponseEntity<List<LocationDTO>> getLocationsByCategories(@RequestBody LocationsByCategoriesAndSector lcs) throws JsonProcessingException {
        System.out.println("address  " + lcs.getAddress());

        double[] coordinates =  routeService.getCoordinatesSector(lcs.getAddress(), lcs.getRadius());
        LocationsInSector locationsInSector = new LocationsInSector(coordinates[0],coordinates[1],coordinates[2],coordinates[3], lcs.getCategories());

        ResponseEntity<List<LocationDTO>> ldtos = feignLocations.getAllByLocationsCategoryAndSector(locationsInSector);

        return ResponseEntity.ok(ldtos.getBody());
    }








}
