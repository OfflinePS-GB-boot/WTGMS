package y88.kirill.mslocationsevents.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import y88.kirill.mslocationsevents.dto.LocationDTO;
import y88.kirill.mslocationsevents.models.Location;
import y88.kirill.mslocationsevents.repositories.LocationsRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationsService {

    private final LocationsRepository locationsRepository;

    public List<LocationDTO> findAllWithPage(int page, int pageSize){
        return locationsRepository.findAllWithImage( PageRequest.of(page-1,pageSize))
                .stream()
                .map(LocationDTO::new)
                .collect(Collectors.toList());
    }

    public List <LocationDTO> findAll(){
        return locationsRepository.findAll().stream().map(LocationDTO::new).collect(Collectors.toList());
    }


}
