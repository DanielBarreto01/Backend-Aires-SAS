package edu.uptc.PizonAcevedo.domain.model.clientModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name ="personas_juridicas")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter @Setter
public class JuridicalPersons extends ClientEntity{

    @NotNull(message = "El nombre de la empresa es obligatorio")
    @Column(unique = true, length = 50)
    private String nameCompany;

    @NotNull(message = "El numero de identificacion de la empresa es obligatorio")
    @Column(unique = true, length = 20)
    private String numberIdentificationCompany;

    @NotNull(message = "La razon social es obligatoria")
    @Column(unique = true, length = 20)
    private String socialReason;

    @NotNull(message = "El nombre del representante legal es obligatorio")
    @Column(length = 50)
    private String nameLegalRepresentative;


    @NotNull(message = "El telefono del representante legal es obligatorio")
    @Column(length = 10)
    private long phoneNumberLegalRepresentative;

    @NotNull(message = "El correo del representante legal es obligatorio")
    @Column(length = 70)
    private String emailLegalRepresentative;

}
