package y88.kirill.msroutes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import y88.kirill.msroutes.models.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {


}
