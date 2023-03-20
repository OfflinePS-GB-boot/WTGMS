package y88.kirill.msroutes.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Data
@NoArgsConstructor
public class LocationDTOForAll {
    private Long id;
    private String title;
    private String description;
    private String fullDescription;
    private String address;
    private String linkImage;
    private String linkSite;
    private Double latitude;
    private Double longitude;

//    public LocationDTOForAll(Long id, String title, String description, String fullDescription, String address, String linkImage, String linkSite, Double latitude, Double longitude) {
//        this.id = id;
//        this.title = title;
//        this.description = description;
//        this.fullDescription = fullDescription;
//        this.address = address;
//        this.linkImage = linkImage;
//        this.linkSite = linkSite;
//        this.latitude = latitude;
//        this.longitude = longitude;
//    }

//    public LocationDTOForAll() {
//    }


}
