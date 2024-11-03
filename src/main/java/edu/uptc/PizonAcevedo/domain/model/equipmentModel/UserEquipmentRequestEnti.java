package edu.uptc.PizonAcevedo.domain.model.equipmentModel;

import edu.uptc.PizonAcevedo.domain.model.userModel.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Entity
@Table(name ="user_equipment_request")
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserEquipmentRequestEnti {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Integer idRequest;

    @ManyToOne
    @NotNull
    @Getter @Setter
    @JoinColumn(name = "id_user", nullable = false)
    private UserEntity user;

    @ManyToOne
    @NotNull
    @Getter @Setter
    @JoinColumn(name = "id_equipment", nullable = false)
    private EquipmentEntity equipment;

    @NotNull
    @Getter @Setter
    private boolean state = true;

    @NotNull
    @Getter @Setter
    private Date generationDate = new Date();

    @PrePersist
    public void prePersist() {
        if (!this.state) {
            this.state = true;  // Establecer valor por defecto si no se ha asignado
        }
    }
}
