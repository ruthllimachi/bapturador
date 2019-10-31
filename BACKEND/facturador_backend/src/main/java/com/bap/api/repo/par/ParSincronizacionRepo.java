package com.bap.api.repo.par;

import com.bap.api.model.par.ParSincronizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("parSincronizacionRepo")
public interface ParSincronizacionRepo extends JpaRepository<ParSincronizacion, String> {
    
    //@Query("select o from ParSincronizacion o where o.codigo = :codigo")
    //ParSincronizacion findByCode(@Param("codigo") String codigo);
    ParSincronizacion findByCodigo(String codigo);
}
