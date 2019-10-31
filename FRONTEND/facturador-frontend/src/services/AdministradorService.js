import Api from '@/services/Api'

export default {
    loginUsuario(usuario){
        return Api().post("/admin/admUsuario/authenticate", usuario);
    },
    getAdmSistema(){
        return Api().get("/admin/admSistema");
    },
    getAdmEmpresa(idEmpresa){
        return Api().get("/admin/admEmpresa", { params: { idEmpresa: idEmpresa }});
    },
    getParametros(login){
        // return Api().get("/admin/admUsuario/getParametros", { params: { login: login }});
        return Api().get(`/admin/admUsuario/getParametros/${login}`);
    },
    getEmpresaAll(){
    return Api().get(`/admin/admEmpresa`);
   }
}
