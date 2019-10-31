package com.bap.api.repo.par;

import com.bap.api.model.par.ParPaisOrigen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParPaisOrigenRepo extends JpaRepository<ParPaisOrigen, Long> {

    ParPaisOrigen findByCodigo(Long codigo);
}
