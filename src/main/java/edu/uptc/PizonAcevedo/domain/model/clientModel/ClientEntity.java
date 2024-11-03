package edu.uptc.PizonAcevedo.domain.model.clientModel;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name ="clientes")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientEntity {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Integer id;

    @NotNull
    @Column(length = 70)
    @Getter @Setter
    private String name;

    @NotNull
    @Column(length = 3)
    @Getter @Setter
    private String typeIdentification;

    @NotNull
    @Getter @Setter
    @Column(unique = true)
    private long numberIdentification;

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

    @NotNull
    @Column(length = 600)
    @Getter @Setter
    private String pathImage;
}
