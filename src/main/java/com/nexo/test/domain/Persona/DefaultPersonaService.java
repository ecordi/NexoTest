package com.nexo.test.domain.Persona;

import com.nexo.test.database.jparepositories.PersonaJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultPersonaService implements PersonaService {
    @Autowired
    private PersonaRepository personaRepository;
    @Autowired
    private PersonaJpaRepository jpaRepository;

    @Override
    public List<PersonaModel> findAll() {
        return personaRepository.findAll();
    }

    @Override
    public PersonaModel create(PersonaModel personaModel) {
        return personaRepository.create(personaModel);
    }

    @Override
    public PersonaModel update(PersonaModel personaModel) {
        return personaRepository.update(personaModel);
    }

    @Override
    public void delete(Long dni) {
        personaRepository.delete(personaRepository.findByDni(dni).get());
    }

    @Override
    public Optional<PersonaModel> findByDni(Long dni) {
        return personaRepository.findByDni(dni);
    }

    @Override
    public List<PersonaModel> findByNombre(String nombre) {
        return personaRepository.findByNombre(nombre);
    }

    @Override
    public List<PersonaModel> findByEdad(int edad) {
        return personaRepository.findByEdad(edad);
    }
}
