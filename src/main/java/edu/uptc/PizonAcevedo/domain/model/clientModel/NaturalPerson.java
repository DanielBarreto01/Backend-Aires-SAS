package edu.uptc.PizonAcevedo.domain.model.clientModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Entity
@Table(name ="personas_naturales")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter @Setter
public class NaturalPerson extends ClientEntity {

    @NotNull(message = "El nombre es obligatorio")
    @Column(length = 50, nullable = false)
    private String name;

    @NotNull(message = "El apellido es obligatorio")
    @Column(length = 50, nullable = false)
    private String lastName;

    @NotNull
    @Column(length = 2)
    private String typeIdentification;

    @NotNull
    @Column(unique = true)
    private long numberIdentification;
}
