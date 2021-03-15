package model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = "name", includeFieldNames = false)
@Table(name = "advertisement")
public class Advertisement implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;


    @ManyToOne
    @JoinColumn(name = "id_user")
    private Users idUser;

    @OneToOne
    @JoinColumn(name = "id_car")
    private Cars car;


    private String photo;
    private Timestamp published;
    private String description;
    private Boolean status;


}
