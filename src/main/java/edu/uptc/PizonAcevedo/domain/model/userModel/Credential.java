package edu.uptc.PizonAcevedo.domain.model.userModel;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name ="credenciales")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Credential {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @Getter @Setter
    private UserEntity user;

    @Getter @Setter
    private String userName;

    @Getter @Setter
    private String password;

}
