import Api from '@/services/Api'

export default {
    ///ApiPuntoVenta
    getApiPuntoVenta(){
        return Api().get("/api/apiPuntoVenta");
    },
    getApiPuntoVentaPorIdEmpresa(idEmpresa){
        return Api().get(`/api/apiPuntoVenta/listaPorIdEmpresa/${idEmpresa}`);
    },
    getApiPuntoVentaPorIdSucursal(idSucursal){
        return Api().get(`/api/apiPuntoVenta/listarPorIdSucursal/${idSucursal}`);
    },
    putApiPuntoVenta(dato){
        return Api().put("/api/apiPuntoVenta", dato);
    },
    registrarPuntoVenta(dato){
        return Api().post("/api/apiPuntoVenta/registrarPuntoVenta", dato);
    },
}
