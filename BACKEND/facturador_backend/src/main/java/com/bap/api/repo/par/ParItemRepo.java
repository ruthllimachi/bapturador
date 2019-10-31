package com.bap.api.repo.par;

import com.bap.api.model.par.ParItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParItemRepo extends JpaRepository<ParItem, Long> {

    ParItem findByCodigo(Long codigo);
}
