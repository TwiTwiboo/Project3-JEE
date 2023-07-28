package triphub.entity.user;

import javax.persistence.*;

@Entity
public class SuperAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;
}