package edu.uptc.PizonAcevedo.domain.model.maintenanceRequest;


import edu.uptc.PizonAcevedo.domain.model.clientModel.ClientEntity;
import edu.uptc.PizonAcevedo.domain.model.equipmentModel.EquipmentEntity;
import edu.uptc.PizonAcevedo.domain.model.userModel.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;


@Entity
@Table(name ="solicitud_mantenimiento")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter @Setter
public class MaintenanceRequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity client; // Un solo cliente asociado

    @ManyToOne
    @JoinColumn(name = "technician_id", nullable = false)
    private UserEntity technician; // Un solo técnico (de la tabla User)

    @ManyToMany
    @JoinTable(
            name = "maintenance_request_equipments",
            joinColumns = @JoinColumn(name = "maintenance_request_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id")
    )
    @NotNull
    private Set<EquipmentEntity> equipments; // Varios equipos asociados

    @NotNull
    private Date requestDate;

    @NotNull
    @Column(length = 150)
    private String requestNumber;

    @NotNull
    @Column(length = 15)
    private String requestMaintenanceStatus = "pendiente";


    @PrePersist
    public void prePersist() {
        if (this.requestMaintenanceStatus == null){
            this.requestMaintenanceStatus = "pendiente";  // Establecer valor por defecto si no se ha asignado
        }
    }

}
