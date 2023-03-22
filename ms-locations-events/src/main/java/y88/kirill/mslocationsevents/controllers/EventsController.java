package y88.kirill.mslocationsevents.controllers;


import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import y88.kirill.corelib.dtos.EventDTO;
import y88.kirill.mslocationsevents.services.EventsService;

import java.util.List;

@RestController
@RequestMapping("api/v1/events")
@RequiredArgsConstructor
public class EventsController {

    private final EventsService eventsService;

    @GetMapping()
    @Operation(summary = "Получение списка событий")
    public ResponseEntity<List<EventDTO>> getAllEvents(@RequestParam(name = "page") int page,
                                                       @RequestParam(name = "pageSize") int pageSize){
        return ResponseEntity.ok(eventsService.findAllWithPageAfterCurrentDate(page, pageSize));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение события по id")
    public ResponseEntity<EventDTO> getEventsById(@PathVariable Long id){
        return ResponseEntity.ok(eventsService.findById(id));
    }

    @GetMapping("/location/{id}")
    @Operation(summary = "Получение списка событий по id локации в которой они проводятся")
    public ResponseEntity<List<EventDTO>> getAllEventsByLocationId(@PathVariable Long id){
        return ResponseEntity.ok(eventsService.findAllByLocation(id));
    }

    @GetMapping("/manual-title-description")
    @Operation(summary = "Получение списка событий содержащих часть поискового слова в названии или описании")
    public ResponseEntity<List<EventDTO>> getAllEventsByManualTitleAndDescription(@RequestParam(name = "manualTitle") String manualTitle,
                                                                                  @RequestParam(name = "page") int page,
                                                                                  @RequestParam(name = "pageSize") int pageSize){
      return ResponseEntity.ok(eventsService.findAllByManualTitleAndDescription(manualTitle, page, pageSize));
    }








}
