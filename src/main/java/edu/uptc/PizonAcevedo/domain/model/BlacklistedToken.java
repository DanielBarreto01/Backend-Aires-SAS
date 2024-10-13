package edu.uptc.PizonAcevedo.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name ="sesiones")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter @Setter
public class BlacklistedToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 600)
    private String token;
}
