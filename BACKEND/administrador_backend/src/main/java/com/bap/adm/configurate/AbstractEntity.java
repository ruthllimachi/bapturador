package com.bap.adm.configurate;

import java.util.List;

/**
 *
 * @author ruth
 */
public interface AbstractEntity<T> {

    T registrar(T t);

    T modificar(T t);

    T leerPorId(Long id);

    List<T> listar();

    void eliminar(T t);
}
