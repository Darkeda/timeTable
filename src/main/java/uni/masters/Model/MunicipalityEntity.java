package uni.masters.Model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "municipality")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class MunicipalityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    public MunicipalityEntity(String name) {
        this.name = name;
    }
}
