import Api from '@/services/Api'

export default {
    ///ApiSucursal
    getApiSucursal(){
        return Api().get("/api/apiSucursal");
    },
    getApiSucursalPorIdEmpresa(idEmpresa){
        return Api().get(`/api/apiSucursal/listarPorIdEmpresa/${idEmpresa}`)
    },
    listaBySucursalNoTieneCuis(idEmpresa){
        return Api().get(`/api/apiSucursal/listaBySucursalNoTieneCuis/${idEmpresa}`)
    },
    listaByPuntoVentaNoTieneCuis(idEmpresa){
        return Api().get(`/api/apiSucursal/listaByPuntoVentaNoTieneCuis/${idEmpresa}`)
    },
    listaByEmpresaTieneCuisVigente(idEmpresa){
        return Api().get(`/api/apiSucursal/listaByEmpresaTieneCuisVigente/${idEmpresa}`)
    },
    postApiSucursal(dato){
        return Api().post("/api/apiSucursal", dato);
    },
    putApiSucursal(dato){
        return Api().put("/api/apiSucursal", dato);
    },

}
