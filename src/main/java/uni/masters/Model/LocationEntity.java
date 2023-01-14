package uni.masters.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "location")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class LocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name", unique = true, nullable = false )
    private String name;

    @ManyToOne
    @JoinColumn(name = "municipality",referencedColumnName = "id", nullable = false)
    private MunicipalityEntity municipality;

    public LocationEntity(String name, MunicipalityEntity municipality) {
        this.name = name;
        this.municipality = municipality;
    }
}
