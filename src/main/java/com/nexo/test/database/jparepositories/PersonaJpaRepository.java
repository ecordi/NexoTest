package com.nexo.test.database.jparepositories;

import com.nexo.test.database.entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PersonaJpaRepository extends JpaRepository<Persona, Long> {
    Optional<Persona> findPersonaByDni(Long dni);
}
