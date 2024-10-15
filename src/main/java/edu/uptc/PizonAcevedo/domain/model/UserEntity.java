package edu.uptc.PizonAcevedo.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name ="usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Integer id;

    @Getter @Setter
    @Column(length = 50)
    private String name;

    @Getter @Setter
    @Column(length = 50)
    private String lastName;

    @Getter @Setter
    @Column(length = 2)
    private String typeIdentification;

    @Column(unique = true, length = 20)
    @Getter @Setter
    private String numberIdentification;

    @Column(unique = true, length = 70)
    @Getter @Setter
    private String email;

    @Getter @Setter
    private long phoneNumber;

    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.PERSIST)
    @Getter @Setter
    private Credential credential;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Roles.class, cascade = CascadeType.PERSIST)
    @Getter @Setter
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Roles> roles;

}