package y88.kirill.mslocationsevents.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import y88.kirill.mslocationsevents.models.Event;

@Repository
public interface EventsRepository extends JpaRepository<Event,Long> {

}
