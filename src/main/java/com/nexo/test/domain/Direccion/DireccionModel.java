package com.nexo.test.domain.Direccion;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DireccionModel {
    private Long id;
    private String calle;
    private int numCalle;
    private String ciudad;
    private Long persona;
}
