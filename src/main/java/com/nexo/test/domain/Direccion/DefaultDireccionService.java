package com.nexo.test.domain.Direccion;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultDireccionService implements DireccionService {
    private DireccionRepository direccionRepository;

    public DefaultDireccionService(DireccionRepository direccionRepository) {
        this.direccionRepository = direccionRepository;
    }

    @Override
    public List<DireccionModel> findAll() {
        return direccionRepository.findAll();
    }

    @Override
    public DireccionModel create(DireccionModel direccionModel) {
        return direccionRepository.create(direccionModel);
    }

    @Override
    public DireccionModel update(DireccionModel direccionModel) {
        return direccionRepository.update(direccionModel);
    }

    @Override
    public void delete(DireccionModel direccionModel) {
        direccionRepository.delete(direccionModel);
    }

    @Override
    public Optional<DireccionModel> findById(Long id) {
        return direccionRepository.findById(id);
    }
}
