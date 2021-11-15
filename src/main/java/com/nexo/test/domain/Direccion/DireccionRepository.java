package com.nexo.test.domain.Direccion;

import java.util.List;
import java.util.Optional;

public interface DireccionRepository {
    List<DireccionModel> findAll();

    DireccionModel create(DireccionModel direccionModel);

    DireccionModel update(DireccionModel direccionModel);

    void delete(DireccionModel model);

    Optional<DireccionModel> findById(Long id);
}
