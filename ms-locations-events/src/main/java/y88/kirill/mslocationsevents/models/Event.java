package y88.kirill.mslocationsevents.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "events")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "start_datetime")
    private LocalDateTime startDatetime;

    @Column(name = "finish_datetime")
    private LocalDateTime finishDatetime;

    @Column(name = "link_event_site")
    private String linkEventSite;

    @Column(name = "link_image")
    private String linkImage;

    @Column(name = "price")
    private Integer price;

    @ManyToOne()
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_active")
    private Boolean isActive;


}
