package com.nexo.test.domain.Persona;

import com.nexo.test.database.entities.Persona;

import java.util.List;
import java.util.Optional;

public interface PersonaService {
    List<PersonaModel> findAll();

    PersonaModel create(PersonaModel personaModel);

    PersonaModel update(PersonaModel personaModel);

    void delete(Long dni);

    Optional<PersonaModel> findByDni(Long dni);

    List<PersonaModel> findByNombre(String nombre);

    List<PersonaModel> findByEdad(int edad);
}
