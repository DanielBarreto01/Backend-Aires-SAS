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
    @Column(length = 15)
    @Getter @Setter
    private EnumEquipment equipmentType;

    @NotNull
    @Getter @Setter
    private int serialNumber;

    @NotNull
    @Column(length = 50)
    @Getter @Setter
    private String brand;

    @NotNull
    @Getter @Setter
    private int modelNumber;

    @NotNull
    @Getter @Setter
    private int iventoryNumber;

    @ManyToOne
    @JoinColumn(name = "id_client", nullable = true)  // Clave foránea que apunta a la tabla Client
    private ClientEntity client;
}
