package y88.kirill.msroutes.models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "routes")
@NoArgsConstructor
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="duration")
    private LocalDateTime duration;

    @Column(name="distance")
    private Double distance;




}
