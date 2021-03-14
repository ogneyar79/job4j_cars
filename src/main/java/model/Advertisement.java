package model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString
@Table(name = "advertisement")
@Entity
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String photo;
    private Timestamp published;
    private String description;
    private Boolean status;


    public Advertisement() {

    }
}
