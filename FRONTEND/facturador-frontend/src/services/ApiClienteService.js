import Api from '@/services/Api'

export default {  
// ApiCliente
    postApiCliente(dato){
    return Api().post(`/api/apiCliente`, dato);
},

putApiCliente(dato){
    return Api().put(`/api/apiCliente`, dato);

},
getApiClientePorIdEmpresa(idEmpresa){
    return Api().get(`/api/apiCliente/listarClientePorIdEmpresa/${idEmpresa}`);
},
postBajaCliente(dato){
    return Api().post(`/api/apiCliente/darBajaCliente`, dato);
},

postModificarCliente(dato){
    return Api().post(`/api/apiCliente/modificaCliente`, dato);
},

putCliente(dato){
    return Api().put(`/api/apiCliente/registrarApiCliente`, dato);

},

uploadExcelFile(file){
  return Api().post('/api/apiImport/uploadExcelFile/xls',file);
},

//ApiItemHomologado
getApiHomologado(){
    return Api().get(`/api/apiItemHomologado`);
},
getApiHomologadoByItem(idItem){
    return Api().get(`/api/apiItemHomologado/listarHomologadoByItem/${idItem}`);
},
postApiItemHomologado(dato){
    return Api().post('/api/apiItemHomologado', dato);
},
putApiItemHomologado(dato){
    return Api().put('/api/apiItemHomologado', dato);
},
postBajaHomologado(dato){
    return Api().post('/api/apiItemHomologado/darBajaHomologado', dato);
},

postModificarHomologado(dato){
    return Api().post(`/api/apiItemHomologado/modificaHomologado`, dato);
},

//ApiItem =Productos

getApiProductsByCompany(idEmpresa){
    return Api().get(`/api/apiItem/listarItemPorIdEmpresa/${idEmpresa}`);
},

}
