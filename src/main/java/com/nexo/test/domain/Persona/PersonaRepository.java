package com.nexo.test.domain.Persona;

import java.util.List;
import java.util.Optional;

public interface PersonaRepository {
    List<PersonaModel> findAll();

    PersonaModel create(PersonaModel personaModel);

    PersonaModel update(PersonaModel personaModel);

    void delete(PersonaModel personaModel);

    Optional<PersonaModel> findByDni(Long dni);

    List<PersonaModel> findByNombre(String nombre);

    List<PersonaModel> findByEdad(int edad);

}
