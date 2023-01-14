package uni.masters.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "route")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class RouteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "startingLocation",referencedColumnName = "id", nullable = false)
    private LocationEntity startingLocationEntity;

    @ManyToOne
    @JoinColumn(name = "finalLocation",referencedColumnName = "id", nullable = false)
    private LocationEntity finalLocationEntity;

    @Column(name= "departure", nullable = false)
    private String departure;

//    @OneToMany
//    private List<LocationEntity> passingPoints = new ArrayList<>();

    public RouteEntity(LocationEntity startingLocationEntity,
                       LocationEntity finalLocationEntity,
                       String departure) {
        this.startingLocationEntity = startingLocationEntity;
        this.finalLocationEntity = finalLocationEntity;
        this.departure = departure;
    }

}
