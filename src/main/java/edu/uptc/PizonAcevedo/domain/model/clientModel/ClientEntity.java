package edu.uptc.PizonAcevedo.domain.model.clientModel;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
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
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "clientType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = NaturalPerson.class, name = "NaturalPerson"),
        @JsonSubTypes.Type(value = JuridicalPersons.class, name = "JuridicalPersons")
})
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

    @Transient
    private String clientType;

    @PostLoad
    public void setClientType() {
        // Aquí asignas el tipo de cliente según la clase concreta.
        if (this instanceof NaturalPerson) {
            this.clientType = "NaturalPerson";
        } else if (this instanceof JuridicalPersons) {
            this.clientType = "JuridicalPersons";
        }
    }

    @JsonProperty("clientType")
    public String getClientType() {
        if (this instanceof NaturalPerson) {
            return "NaturalPerson";
        } else if (this instanceof JuridicalPersons) {
            return "JuridicalPersons";
        }
        return null; // Por si hay algún caso no esperado
    }
}
