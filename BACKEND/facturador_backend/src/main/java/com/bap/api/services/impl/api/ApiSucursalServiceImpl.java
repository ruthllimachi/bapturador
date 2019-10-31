/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.impl.api;

import com.bap.api.enums.EnumParCondicion;
import com.bap.api.model.api.ApiSucursal;
import com.bap.api.model.par.ParCondicion;
import com.bap.api.repo.api.ApiSucursalRepo;
import com.bap.api.services.api.ApiSucursalService;
import com.bap.api.services.par.ParCondicionService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ruth
 */
@Service
public class ApiSucursalServiceImpl implements ApiSucursalService {

    @Autowired
    private ApiSucursalRepo repo;

    @Autowired
    private ParCondicionService parCondicionService;

    @Override
    public ApiSucursal registrar(ApiSucursal t) {
        ParCondicion parCondicion = parCondicionService.leerPorCodigo(EnumParCondicion.CONDICION_NO.getCodigo());
        t.setTieneCuis(parCondicion);
        t.setFechaAlta(new Date());
        t.setUsuarioAlta("admin");
        return repo.save(t);
    }

    @Override
    public ApiSucursal modificar(ApiSucursal t) {
        ApiSucursal obj = leerPorId(t.getIdSucursal());
        obj.setNombreSucursal(t.getNombreSucursal());
        obj.setDireccion(t.getDireccion());
        obj.setFechaModificacion(new Date());
        obj.setUsuarioModificacion("admin");
        return repo.save(obj);
    }

    @Override
    public void eliminar(ApiSucursal t) {
        t.setFechaBaja(new Date());
        t.setUsuarioBaja("admin");
        repo.save(t);
    }

    @Override
    public List<ApiSucursal> listar() {
        return repo.findAll();
    }

    @Override
    public ApiSucursal leerPorId(Long id) {
        if (repo.findById(id).isPresent()) {
            return repo.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public List<ApiSucursal> listarPorIdEmpresa(Long idEmpresa) {
        return repo.listaByIdEmpresa(idEmpresa);
    }

    @Override
    public List<ApiSucursal> listarByNoTieneCuis(Long idEmpresa) {
        return repo.listaBySucursalNoTieneCuis(idEmpresa);
    }

    @Override
    public List<ApiSucursal> listaByPuntoVentaNoTieneCuis(Long idEmpresa) {
        List<ApiSucursal> lista = new ArrayList<>();
        repo.listaByPuntoVentaNoTieneCuis(idEmpresa).forEach(x -> {
            long id = Long.parseLong(String.valueOf(x[0]));
            ApiSucursal apiSucursal = leerPorId(id);
            lista.add(apiSucursal);
        });
        return lista;
    }

    @Override
    public List<ApiSucursal> listaByEmpresaTieneCuisVigente(Long idEmpresa) {
        List<ApiSucursal> lista = new ArrayList<>();
        repo.listaByEmpresaTieneCuisVigente(idEmpresa).forEach(x -> {
            long id = Long.parseLong(String.valueOf(x[0]));
            ApiSucursal apiSucursal = leerPorId(id);
            apiSucursal.setCuis(String.valueOf(x[1]));
            apiSucursal.setCodigoModalidad((Integer.parseInt(String.valueOf(x[2]))));
            lista.add(apiSucursal);
        });
        return lista;
    }

    @Override
    public ApiSucursal leerPorIdEmpresaAndCodigoSucursal(Long idEmpresa, Long codigoSucursal) {
        return repo.findByIdEmpresaAndCodigoSucursal(idEmpresa, codigoSucursal);
    }

}
