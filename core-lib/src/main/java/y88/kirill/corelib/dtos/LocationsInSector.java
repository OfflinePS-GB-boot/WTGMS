package y88.kirill.corelib.dtos;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
//@RequiredArgsConstructor
public class LocationsInSector {

    private double latitudeMin;
    private double latitudeMax;
    private double longitudeMin;
    private double longitudeMax;
    private int[] categories;

    public LocationsInSector(double latitudeMin, double latitudeMax, double longitudeMin, double longitudeMax, int[] categories) {
        this.latitudeMin = latitudeMin;
        this.latitudeMax = latitudeMax;
        this.longitudeMin = longitudeMin;
        this.longitudeMax = longitudeMax;
        this.categories = categories;
    }
}
