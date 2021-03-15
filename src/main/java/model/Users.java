package model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = "name", includeFieldNames = false)
@Table(name = "users")
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
    private String email;
    private String password;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = Advertisement.class, mappedBy = "idUser")
    private List<Advertisement> advertisements = new ArrayList<>();
}
