package y88.kirill.mslocationsevents.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import y88.kirill.mslocationsevents.models.Location;


@Data
@NoArgsConstructor
public class LocationDTO {
    private Long id;
    private String title;
    private String description;
    private String fullDescription;
    private String address;
    private String linkImage;
    private String linkSite;
    private Double latitude;
    private Double longitude;

    public LocationDTO(Location location) {
        this.id = location.getId();
        this.title = location.getTitle();
        this.description = location.getDescription();
        this.fullDescription = location.getFullDescription();
        this.address = location.getAddress();
        this.linkImage = location.getLinkImage();
        this.linkSite = location.getLinkSite();
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
    }
}
