package y88.kirill.corelib.feigns;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import y88.kirill.corelib.dtos.LocationDTO;
import y88.kirill.corelib.dtos.LocationsInSector;

import java.util.List;

@FeignClient(value ="ms-locations-events" , path = "/locations-events/api/v1/locations")
public interface FeignLocations {

    @PostMapping(value = "/in-sector-and-subcategory", produces = "application/json")
    ResponseEntity<List<LocationDTO>> getAllByLocationsSubcategoryAndSector(@RequestBody LocationsInSector locationsInSector);

    @PostMapping(value = "/in-sector-and-category", produces = "application/json")
    ResponseEntity<List<LocationDTO>> getAllByLocationsCategoryAndSector(@RequestBody LocationsInSector locationsInSector);


}
