package y88.kirill.mslocationsevents.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import y88.kirill.mslocationsevents.models.Location;

@Repository
public interface LocationsRepository extends JpaRepository<Location,Long> {


    //todo вщсстановить как в базе появятся картинки
//    @Query(value = "select * from locations l where l.link_image is not null and l.link_image !=''", nativeQuery = true)
//    Page<Location> findAllWithImage(Pageable pageable);

    @Query(value = "select * from locations l ", nativeQuery = true)
    Page<Location> findAllWithImage(Pageable pageable);



}
