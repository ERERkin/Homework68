package kg.Academy.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
@Table(name = "teams")
public class Team {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "website")
    private String website;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sport_kind_id", referencedColumnName = "id")
    private SportKind sportKind;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "championship_id", referencedColumnName = "id")
    private List<Championship> championships;
}
