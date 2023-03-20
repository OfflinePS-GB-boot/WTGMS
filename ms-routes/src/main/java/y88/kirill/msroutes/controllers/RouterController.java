package y88.kirill.msroutes.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import y88.kirill.corelib.dtos.LocationDTO;
import y88.kirill.corelib.dtos.LocationsInSector;
import y88.kirill.msroutes.dto.LocationDTOForAll;
import y88.kirill.msroutes.dto.LocationsByCategoriesAndSector;
import y88.kirill.msroutes.servises.RouteService;

import java.util.*;

@RestController
@RequestMapping("api/v1/routes")
@RequiredArgsConstructor
public class RouterController {

    private final RestTemplate restTemplate;
    private final RouteService routeService;

    @GetMapping(value = "/locations-by-categories-and-sector", consumes = "application/json")
    public List<LocationDTO> getSectorCoordinates(@RequestBody LocationsByCategoriesAndSector lcs) throws JsonProcessingException {
        System.out.println("address  " + lcs.getAddress());

        double[] coordinates =  routeService.getCoordinatesSector(lcs.getAddress(), lcs.getRadius());
        LocationsInSector locationsInSector = new LocationsInSector(coordinates[0],coordinates[1],coordinates[2],coordinates[3], lcs.getCategories());

        ResponseEntity<LocationDTO[]> ldtos = restTemplate.postForEntity("http://ms-locations-events/locations-events/api/v1/locations/in-sector-and-subcategory",locationsInSector, LocationDTO[].class);

        return Arrays.asList(Objects.requireNonNull(ldtos.getBody()));
    }






}
