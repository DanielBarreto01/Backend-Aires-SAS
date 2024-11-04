package edu.uptc.PizonAcevedo.domain.model.equipmentModel;

import edu.uptc.PizonAcevedo.domain.model.clientModel.ClientEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name ="equipos")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipmentEntity {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Integer id;

    @NotNull
    @Column(length = 150)
    @Getter @Setter
    private String name;

    @NotNull
    @Getter @Setter
    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private EnumEquipment equipmentType;

    @NotNull
    @Getter @Setter
    @Column(unique = true, length = 100)
    private String serialNumber;

    @NotNull
    @Getter @Setter
    private String brand;

    @NotNull
    @Getter @Setter
    @Column(length = 100)
    private String modelNumber;

    @NotNull
    @Getter @Setter
    private int iventoryNumber;

    @Column(length = 600, nullable = true)
    @Getter @Setter
    private String pathImage;

    @ManyToOne
    @JoinColumn(name = "id_client", nullable = true)  // Clave foránea que apunta a la tabla Client
    private ClientEntity client;
}
