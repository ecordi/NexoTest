package com.nexo.test.domain.Persona;

import com.nexo.test.domain.Direccion.DireccionModel;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class PersonaModel {
    private Long dni;
    private String nombre;
    private String apellido;
    private int edad;
    private String foto;
    private List<DireccionModel> direccionList;
}
