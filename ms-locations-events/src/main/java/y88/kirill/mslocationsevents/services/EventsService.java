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
        EventDTO eventDTO = new EventDTO();

        LocationDTO locationDTO = locationsService.toDTO(event.getLocation());
        eventDTO.setLocation(locationDTO);

        return eventDTO;
    }

}
