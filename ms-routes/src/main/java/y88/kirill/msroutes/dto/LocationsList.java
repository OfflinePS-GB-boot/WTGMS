package y88.kirill.msroutes.dto;

import lombok.Data;
import lombok.Getter;


import java.util.List;

@Data
public class LocationsList {

    @Getter
    private List<LocationDTOForAll> locationDTOForAlls;

    public LocationsList(List<LocationDTOForAll> locationDTOForAlls) {
        this.locationDTOForAlls = locationDTOForAlls;
    }


    @Override
    public String toString() {
        return "LocationsList{" +
                "locationDTOForAlls=" + locationDTOForAlls +
                '}';
    }
}
