package y88.kirill.msroutes.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
//@NoArgsConstructor
//@RequiredArgsConstructor
public class LocationsByCategoriesAndSector {

    String address;
    int radius;
    private int[] categories;

    public LocationsByCategoriesAndSector(String address, int radius, int[] categories) {
        this.address = address;
        this.radius = radius;
        this.categories = categories;
    }

    public LocationsByCategoriesAndSector() {
    }
}
