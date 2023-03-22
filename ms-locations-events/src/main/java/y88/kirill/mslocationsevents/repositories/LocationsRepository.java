package y88.kirill.mslocationsevents.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import y88.kirill.mslocationsevents.models.Location;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationsRepository extends JpaRepository<Location,Long> {

    Optional<Location> findById(Long id);

    //todo вщсстановить как в базе появятся картинки
    @Query(value = "select * from locations l where l.link_image is not null and l.link_image !=''", nativeQuery = true)
    Page<Location> findAllWithImage(Pageable pageable);

//    @Query(value = "select * from locations l ", nativeQuery = true)
//    Page<Location> findAllWithImage(Pageable pageable);

    @Query(value = "SELECT * FROM locations  where title ilike %?1% or description ilike %?1%", nativeQuery = true)
    Page<Location> findAllByManualTitleAndDescription(@Param("manualTitle") String manualTitle, Pageable pageable);


    //выборка локаций по категориям в заданном секторе
    @Query(value = "select * from locations l " +
            "right join locations_events_categories lec on l.id = lec.location_id " +
            "where (l.latitude >= :latitudeMin and l.latitude <= :latitudeMax) " +
            "and (l.longitude >= :longitudeMin and l.longitude <= :longitudeMax) " +
            "and  lec.category_id = any (:categories) ",nativeQuery = true)
    List<Location> findAllByLocationsCategoriesAndSector(
            Double latitudeMin, Double latitudeMax, Double longitudeMin, Double longitudeMax,
            @Param("categories") int[] categories
    );



    //выборка локаций по подкатегориям в заданном секторе
    @Query(value = "select * from locations l " +
            "right join locations_events_categories lec on l.id = lec.location_id " +
            "where (l.latitude >= :latitudeMin and l.latitude <= :latitudeMax) " +
            "and (l.longitude >= :longitudeMin and l.longitude <= :longitudeMax) " +
            "and  lec.subcategory_id = any (:subcategories) ",nativeQuery = true)
    List<Location> findAllByLocationsSubcategoriesAndSector(
            Double latitudeMin, Double latitudeMax, Double longitudeMin, Double longitudeMax,
            @Param("subcategories") int[] subcategories
    );






}
