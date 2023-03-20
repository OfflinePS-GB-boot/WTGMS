package y88.kirill.msroutes.servises;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import y88.kirill.msroutes.mapAPI.MapAPIInterface;
import y88.kirill.msroutes.repositories.RouteRepository;

import java.util.List;

@Service
public class RouteService {

    private final RouteRepository routeRepository;
    private final Sector sector;
    private final MapAPIInterface mapAPIInterface;

    @Autowired
    public RouteService(RouteRepository routeRepository, Sector sector, @Qualifier("mapAPIYandex") MapAPIInterface mapAPIInterface) {
        this.routeRepository = routeRepository;
        this.sector = sector;
        this.mapAPIInterface = mapAPIInterface;
    }

    public double[] getCoordinatesSector (String address, int radius ) {
        List<Double> coordinates = mapAPIInterface.getCoordinateByAddress(address);
        return sector.getSectorByRadius(coordinates.get(0), coordinates.get(1), radius);
    }




}
