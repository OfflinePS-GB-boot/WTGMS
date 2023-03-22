package y88.kirill.mslocationsevents.services;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Service;
import y88.kirill.corelib.dtos.EventDTO;
import y88.kirill.corelib.dtos.LocationDTO;
import y88.kirill.mslocationsevents.models.Event;
import y88.kirill.mslocationsevents.repositories.EventsRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventsService {

    private final LocationsService locationsService;
    private final EventsRepository eventsRepository;


    public EventDTO findById(Long id){
        return toDTO(eventsRepository.findById(id).orElse( Event.builder().title("Нет событий с данным Id").build()));
    }

    public List<EventDTO> findAllByLocation(Long locationId){
        return eventsRepository.findAllByLocation(locationId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<EventDTO> findAllWithPageAfterCurrentDate(int page, int pageSize){
        return eventsRepository.findAllWithPageAfterCurrentDate( LocalDateTime.now(), PageRequest.of(page-1,pageSize))
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<EventDTO> findAllByManualTitleAndDescription(String manualTitle, int page, int pageSize){
        return eventsRepository.findAllByManualTitleAndDescription(manualTitle,LocalDateTime.now() , PageRequest.of(page-1,pageSize))
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }



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
