package model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString
@Entity
public class Cars {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String model;
    private String typeCarBody;
    private String color;

    public Cars() {

    }

}
