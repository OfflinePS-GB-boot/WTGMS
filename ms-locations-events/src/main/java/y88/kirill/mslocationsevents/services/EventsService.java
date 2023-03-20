package y88.kirill.mslocationsevents.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import y88.kirill.corelib.dtos.EventDTO;
import y88.kirill.corelib.dtos.LocationDTO;
import y88.kirill.mslocationsevents.models.Event;

@Service
@RequiredArgsConstructor
public class EventsService {

    private final LocationsService locationsService;


    public EventDTO toDTO (Event event){
        LocationDTO locationDTO = locationsService.toDTO(event.getLocation());
        return EventDTO.builder()
                .id(event.getId())
                .title(event.getTitle())
                .description(event.getDescription())
                .startDatetime(event.getStartDatetime())
                .finishDatetime(event.getFinishDatetime())
                .isActive(event.getIsActive())
                .linkEventSite(event.getLinkEventSite())
                .linkImage(event.getLinkImage())
                .location(locationDTO)
                .price(event.getPrice())
                .price(event.getPrice())
                .build();
    }

}
