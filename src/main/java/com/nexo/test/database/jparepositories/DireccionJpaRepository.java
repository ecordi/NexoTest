package com.nexo.test.database.jparepositories;

import com.nexo.test.database.entities.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DireccionJpaRepository extends JpaRepository<Direccion, Long> {
}
