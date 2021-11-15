package com.nexo.test.database.entities;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "direcciones")
public class Direccion implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "calle")
    private String calle;
    @Column(name = "numCalle")
    private int numCalle;
    @Column(name = "ciudad")
    private String ciudad;
    @JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@Id")
    @ManyToOne
    @JoinColumn(name = "persona")
    @JsonIgnore
    private Persona persona;
}
