package com.mycompany.citasmedicas.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "perfiles_medicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PerfilMedico {

    @Id
    @Column(name = "usuario_id")
    private Long usuarioId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "especialidad_id", nullable = false)
    private Especialidad especialidad;

    @Column(name = "num_colegiado", nullable = false, unique = true, length = 50)
    private String numColegiado;
}