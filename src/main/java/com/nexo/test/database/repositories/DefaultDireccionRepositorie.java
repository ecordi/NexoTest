package com.nexo.test.database.repositories;

import com.nexo.test.database.entities.Direccion;
import com.nexo.test.database.jparepositories.DireccionJpaRepository;
import com.nexo.test.database.jparepositories.PersonaJpaRepository;
import com.nexo.test.domain.Direccion.DireccionModel;
import com.nexo.test.domain.Direccion.DireccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import static java.util.stream.Collectors.toList;

@Repository
public class DefaultDireccionRepositorie implements DireccionRepository {
    @Autowired
    DireccionJpaRepository direccionJpaRepository;
    @Autowired
    PersonaJpaRepository personaJpaRepository;

    @Override
    public List<DireccionModel> findAll() {
        return direccionJpaRepository.findAll().stream()
                .map(this::toModel)
                .collect(toList());
    }

    @Override
    public DireccionModel create(DireccionModel direccionModel) {
        return toModel(direccionJpaRepository.save(toEntity(direccionModel)));
    }

    @Override
    public DireccionModel update(DireccionModel direccionModel) {
        return toModel(direccionJpaRepository.save(toEntity(direccionModel)));
    }

    @Override
    public void delete(DireccionModel direccion) {
        direccionJpaRepository.delete(toEntity(direccion));
    }

    @Override
    public Optional<DireccionModel> findById(Long id) {
        return direccionJpaRepository.findById(id)
                .map(this::toModel);
    }

    private DireccionModel toModel(Direccion direccion) {
        DireccionModel direccionModel = new DireccionModel();
        direccionModel.setId(direccion.getId());
        direccionModel.setCalle(direccion.getCalle());
        direccionModel.setNumCalle(direccion.getNumCalle());
        direccionModel.setCiudad(direccion.getCiudad());
        direccionModel.setPersona(direccion.getPersona().getDni());
        return direccionModel;
    }

    private Direccion toEntity(DireccionModel direccionModel) {
        Direccion direccionUpdate = new Direccion();
        direccionUpdate.setId(direccionModel.getId());
        direccionUpdate.setCalle(direccionModel.getCalle());
        direccionUpdate.setNumCalle(direccionModel.getNumCalle());
        direccionUpdate.setCiudad(direccionModel.getCiudad());
        direccionUpdate.setPersona(personaJpaRepository.findById(direccionModel.getPersona()).get());
        return direccionUpdate;
    }
}
