package y88.kirill.mslocationsevents.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import y88.kirill.mslocationsevents.models.Event;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface EventsRepository extends JpaRepository<Event,Long> {

    Optional<Event> findById(Long id);

    @Query("SELECT e FROM Event e   where e.startDatetime >= :dateStart")
    Page<Event> findAllWithPageAfterCurrentDate(@Param("dateStart") LocalDateTime dateStart, Pageable pageable);

    @Query(value = "SELECT * FROM events  where (title ilike %?1% or description ilike %?1%) and start_datetime >= ?2", nativeQuery = true)
    Page<Event> findAllByManualTitleAndDescription(@Param("manualTitle") String manualTitle,
                                                   @Param("start_datetime") LocalDateTime start_datetime,
                                                   Pageable pageable);

}
