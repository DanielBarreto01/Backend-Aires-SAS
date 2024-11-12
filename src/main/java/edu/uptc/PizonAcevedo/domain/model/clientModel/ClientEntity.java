package edu.uptc.PizonAcevedo.domain.model.clientModel;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name ="clientes")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class ClientEntity {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Integer id;

    @NotNull
    @Getter @Setter
    private long phoneNumber;

    @NotNull
    @Column(unique = true, length = 150)
    @Getter @Setter
    private String email;

    @NotNull
    @Column(length = 256)
    @Getter @Setter
    private String address;

    @Column(length = 600)
    @Getter @Setter
    private String pathImage;

    @NotNull
    @Getter @Setter
    private boolean clientState = true;

    @PrePersist
    public void prePersist() {
        if (!this.clientState) {
            this.clientState = true;  // Establecer valor por defecto si no se ha asignado
        }
    }
}
