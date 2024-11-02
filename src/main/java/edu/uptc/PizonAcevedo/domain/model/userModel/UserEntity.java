package edu.uptc.PizonAcevedo.domain.model.userModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import java.util.Set;

@Entity
@Table(name ="usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Integer id;

    @Getter @Setter
    @NotNull(message = "El nombre es obligatorio")
    @Column(length = 50, nullable = false)
    private String name;

    @NotNull(message = "El apellido es obligatorio")
    @Getter @Setter
    @Column(length = 50, nullable = false)
    private String lastName;

    @Getter @Setter
    @NotNull(message = "El tipo de identificacion es obligatorio")
    @Column(length = 2, nullable = false)
    private String typeIdentification;

    @NotNull(message = "El numero de identificacion es obligatorio")
    @Column(unique = true, length = 20, nullable = false)
    @Getter @Setter
    private String numberIdentification;

    @NotNull(message = "El tipo de identificacion es obligatorio")
    @Column(unique = true, length = 70 , nullable = false)
    @Email
    @Getter @Setter
    private String email;

    @NotNull(message = "El telefono es obligatorio")
    @Column(length = 10, nullable = false)
    @Getter @Setter
    private long phoneNumber;

    @Column(length = 50, nullable = false)
    @NotNull(message = "la direccion es obligatorio")
    @Getter @Setter
    private String address;

    @Column
    @NotNull(message = "la estado del usuario es obligatorio")
    @Getter @Setter
    private boolean userStatus = true;


    @Column(length = 600, nullable = true)
    @Getter @Setter
    private String pathImage;


    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.PERSIST)
    @Getter @Setter
    private Credential credential;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Roles.class, cascade = CascadeType.PERSIST)
    @Getter @Setter
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Roles> roles;

    @PrePersist
    public void prePersist() {
        if (!this.userStatus) {
            this.userStatus = true;  // Establecer valor por defecto si no se ha asignado
        }
    }


}