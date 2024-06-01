package gmail.vezhur2003.blps.secondary;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "vacancy")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class VacancyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Boolean confirmation;

    @Column(nullable = false)
    private Long salary;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private String contact;

    @Column(nullable = false)
    private String shortDescription;

    @Column(nullable = false)
    private String longDescription;

    @Column(nullable = false)
    private Long userId;
}
