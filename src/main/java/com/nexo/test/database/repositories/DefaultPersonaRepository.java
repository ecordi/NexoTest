package com.nexo.test.database.repositories;

import com.nexo.test.database.entities.Direccion;
import com.nexo.test.database.entities.Persona;
import com.nexo.test.database.jparepositories.DireccionJpaRepository;
import com.nexo.test.database.jparepositories.PersonaJpaRepository;
import com.nexo.test.domain.Direccion.DireccionModel;
import com.nexo.test.domain.Persona.PersonaModel;
import com.nexo.test.domain.Persona.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Repository
public class DefaultPersonaRepository implements PersonaRepository {
    @Autowired
    PersonaJpaRepository personaJpaRepository;
    @Autowired
    private DireccionJpaRepository direccionJpaRepository;

    @Override
    public List<PersonaModel> findAll() {
        return personaJpaRepository.findAll().stream()
                .map(this::toModel)
                .collect(toList());
    }

    @Override
    public PersonaModel create(PersonaModel persona) {
        List<Direccion> direcciones = new ArrayList<>();
        Persona newPersona = new Persona();
        newPersona.setDni(persona.getDni());
        newPersona.setNombre(persona.getNombre());
        newPersona.setApellido(persona.getApellido());
        newPersona.setEdad(persona.getEdad());
        newPersona.setFoto(persona.getFoto());
        personaJpaRepository.save(newPersona);
        for (DireccionModel d : persona.getDireccionList()) {
            direcciones.add(toEntityFromModel(d));
        }
        newPersona.setDireccionList(direcciones);
        return toModel(personaJpaRepository.save(newPersona));
    }

    private DireccionModel toModelFromEntityD(Direccion direccion) {
        DireccionModel direccionUpdate = new DireccionModel();
        direccionUpdate.setId(direccion.getId());
        direccionUpdate.setCalle(direccion.getCalle());
        direccionUpdate.setNumCalle(direccion.getNumCalle());
        direccionUpdate.setCiudad(direccion.getCiudad());
        direccionUpdate.setPersona(direccion.getPersona().getDni());
        return direccionUpdate;
    }

    private Direccion toEntityFromModel(DireccionModel direccionModel) {
        Direccion direccionUpdate = new Direccion();
        direccionUpdate.setId(direccionModel.getId());
        direccionUpdate.setCalle(direccionModel.getCalle());
        direccionUpdate.setNumCalle(direccionModel.getNumCalle());
        direccionUpdate.setCiudad(direccionModel.getCiudad());
        direccionUpdate.setPersona(personaJpaRepository.findById(direccionModel.getPersona()).get());
        return direccionUpdate;
    }

    @Override
    public PersonaModel update(PersonaModel personaModel) {
        return toModel(personaJpaRepository.save(toEntity(personaModel)));
    }

    @Override
    public void delete(PersonaModel persona) {
        for (DireccionModel d : persona.getDireccionList()) {
            direccionJpaRepository.delete(toEntityFromModel(d));
        }
        personaJpaRepository.delete(toEntity(persona));
    }

    @Override
    public Optional<PersonaModel> findByDni(Long dni) {
        return personaJpaRepository.findPersonaByDni(dni)
                .map(this::toModel);
    }

    @Override
    public List<PersonaModel> findByNombre(String nombre) {
        List<PersonaModel> list = new ArrayList<>();
        for (Persona m : personaJpaRepository.findAll()) {
            if (m.getNombre().equals(nombre)) {
                list.add(toModel(m));
            }
        }
        return list;
    }

    @Override
    public List<PersonaModel> findByEdad(int edad) {
        List<PersonaModel> list = new ArrayList<>();
        for (Persona m : personaJpaRepository.findAll()) {
            if (m.getEdad() == edad) {
                list.add(toModel(m));
            }
        }
        return list;
    }

    private PersonaModel toModel(Persona persona) {
        PersonaModel personaModel = new PersonaModel();
        List<DireccionModel> direcciones = new ArrayList<>();
        personaModel.setDni(persona.getDni());
        personaModel.setNombre(persona.getNombre());
        personaModel.setApellido(persona.getApellido());
        personaModel.setEdad(persona.getEdad());
        personaModel.setFoto(persona.getFoto());
        for (Direccion d : persona.getDireccionList()) {
            direcciones.add(toModelFromEntityD(d));
        }
        personaModel.setDireccionList(direcciones);
        return personaModel;
    }

    private Persona toEntity(PersonaModel persona) {
        List<Direccion> direcciones = new ArrayList<>();
        Persona personaEntity = new Persona();
        personaEntity.setDni(persona.getDni());
        personaEntity.setNombre(persona.getNombre());
        personaEntity.setApellido(persona.getApellido());
        personaEntity.setEdad(persona.getEdad());
        personaEntity.setFoto(persona.getFoto());
        for (DireccionModel d : persona.getDireccionList()) {
            direcciones.add(toEntityFromModel(d));
        }
        personaEntity.setDireccionList(direcciones);
        return personaEntity;
    }
}
