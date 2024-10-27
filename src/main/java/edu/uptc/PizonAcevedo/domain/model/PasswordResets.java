package edu.uptc.PizonAcevedo.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Entity
@Table(name ="restablecer_contraseñas")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PasswordResets {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Integer id;

    @NotNull
    @Getter @Setter
    private String resetCode;

    @NotNull
    @Getter @Setter
    private Date expirationDate;

    @NotNull
    @Getter @Setter
    private Date createDate;

    @NotNull
    @Getter @Setter
    private String token;

    @NotNull
    @Getter @Setter
    private boolean status =true;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Getter @Setter
    private UserEntity user;


    @PrePersist
    public void prePersist() {
        if (!this.status) {
            this.status = true;  // Establecer valor por defecto si no se ha asignado
        }
    }

}
