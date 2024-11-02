package edu.uptc.PizonAcevedo.domain.model.userModel;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name ="roles")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Getter @Setter
    private ERole name;
}
