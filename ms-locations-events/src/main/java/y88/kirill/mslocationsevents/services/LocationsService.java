package y88.kirill.mslocationsevents.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import y88.kirill.corelib.dtos.LocationDTO;
import y88.kirill.mslocationsevents.models.Location;
import y88.kirill.mslocationsevents.repositories.LocationsRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationsService {

    private final LocationsRepository locationsRepository;

    public LocationDTO findById(Long id){
       return toDTO( locationsRepository.findById(id).orElse(Location.builder().title("Локации с данным id не найдено").build()));
    }

    public List<LocationDTO> findAllWithPage(int page, int pageSize){
        return locationsRepository.findAllWithImage( PageRequest.of(page-1,pageSize))
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List <LocationDTO> findAll(){
        return locationsRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<LocationDTO> findAllByLocationsSubcategoryAndSector (
            Double latitudeMin, Double latitudeMax,
            Double longitudeMin, Double longitudeMax,
            int[] subcategories){

        return locationsRepository.findAllByLocationsSubcategoriesAndSector(
                latitudeMin, latitudeMax, longitudeMin, longitudeMax, subcategories)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<LocationDTO> findAllByLocationsCategoryAndSector (
            Double latitudeMin, Double latitudeMax,
            Double longitudeMin, Double longitudeMax,
            int[] categories){

        return locationsRepository.findAllByLocationsCategoriesAndSector(
                        latitudeMin, latitudeMax, longitudeMin, longitudeMax, categories)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<LocationDTO> findAllByManualTitleAndDescription(String manualTitle, int page, int pageSize){
        return locationsRepository.findAllByManualTitleAndDescription( manualTitle,  PageRequest.of(page-1,pageSize))
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }



    public LocationDTO toDTO(Location location){
        return LocationDTO.builder()
                .id(location.getId())
                .title(location.getTitle())
                .address(location.getAddress())
                .description(location.getDescription())
                .fullDescription(location.getFullDescription())
                .linkImage(location.getLinkImage())
                .linkSite(location.getLinkSite())
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .build();
    }





}
