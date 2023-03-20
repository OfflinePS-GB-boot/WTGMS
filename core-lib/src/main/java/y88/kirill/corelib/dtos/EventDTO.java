package y88.kirill.corelib.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventDTO {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime startDatetime;
    private LocalDateTime finishDatetime;
    private String linkEventSite;
    private String linkImage;
    private Integer price;
    //private Long location;
    private LocationDTO location;
    private Boolean isActive;
    private Long userCreatedId;

//    public EventDTO(Event event) {
//        this.id = event.getId();
//        this.title = event.getTitle();
//        this.description = event.getDescription();
//        this.startDatetime = event.getStartDatetime();
//        this.finishDatetime = event.getFinishDatetime();
//        this.linkEventSite = event.getLinkEventSite();
//        this.linkImage = event.getLinkImage();
//        this.price = event.getPrice();
//        //this.location = event.getLocation().getId();
//        this.location = new LocationDTO(event.getLocation());
//        this.isActive = event.getIsActive();
//    }

}
