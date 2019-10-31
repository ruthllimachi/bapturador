package com.bap.api.repo.par;

import com.bap.api.model.par.ParCondicion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParCondicionRepo extends JpaRepository<ParCondicion, String> {

    ParCondicion findByCodigo(String codigo);
}
