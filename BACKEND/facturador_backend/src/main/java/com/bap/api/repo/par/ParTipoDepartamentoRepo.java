package com.bap.api.repo.par;

import com.bap.api.model.par.ParTipoDepartamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParTipoDepartamentoRepo extends JpaRepository<ParTipoDepartamento, Long> {

    ParTipoDepartamento findByCodigo(Long codigo);
}
