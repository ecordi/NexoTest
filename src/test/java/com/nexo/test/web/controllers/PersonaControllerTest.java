package com.nexo.test.web.controllers;

import com.nexo.test.database.entities.Direccion;
import com.nexo.test.database.entities.Persona;
import com.nexo.test.database.jparepositories.PersonaJpaRepository;
import com.nexo.test.domain.Direccion.DireccionModel;
import com.nexo.test.domain.Persona.PersonaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PersonaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonaJpaRepository repository;

    @MockBean
    private PersonaService service;

    private List<Direccion> testListDirecciones() {
        List<Direccion> direcciones = new ArrayList<>();
        DireccionModel direccionModel = new DireccionModel();
        direccionModel.setId(1L);
        direccionModel.setCalle("chacabuco");
        direccionModel.setNumCalle(7);
        direccionModel.setCiudad("cba");
        direccionModel.setPersona(1L);
        return direcciones;
    }

    private  List<Persona> testEntityList() {
        Persona persona = new Persona();
        List<Persona> list = new ArrayList<>();
        persona.setDni(1L);
        persona.setNombre("TestName");
        persona.setApellido("Test");
        persona.setEdad(55);
        persona.setFoto("");
        persona.setDireccionList(testListDirecciones());
        list.add(persona);
        return list;
    }

    @Test
    void findAll()throws Exception {
        List<Persona> list = testEntityList() ;
        when(repository.findAll()).thenReturn(list);
        mockMvc.perform(get("/personas")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].dni", is(1L)))
                .andExpect(jsonPath("$[0].nombre", is("TestName")))
                .andExpect(jsonPath("$[0].apellido", is("Test")))
                .andExpect(jsonPath("$[0].edad", is(55)))
                .andExpect(jsonPath("$[0].foto", is("")))
                .andExpect(jsonPath("$[0].direccionesList", is(testListDirecciones())));
    }


    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void findByDni() {
    }

    @Test
    void findByNombre() {
    }

    @Test
    void findByEdad() {
    }

    @Test
    void exportToExcel() {
    }
}