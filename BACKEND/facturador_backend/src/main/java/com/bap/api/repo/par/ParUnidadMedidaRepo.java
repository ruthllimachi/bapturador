package com.bap.api.repo.par;

import com.bap.api.model.par.ParUnidadMedida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParUnidadMedidaRepo extends JpaRepository<ParUnidadMedida, Long> {

    ParUnidadMedida findByCodigo(Long codigo);

}
