import Api from '@/services/Api'

export default {
    inicioEventoSignificativo(dato){
        return Api().post("/api/apiEventoSignificativo/inicioEventoSignificativo", dato);
    },
    finEventoSignificativo(dato){
        return Api().post("/api/apiEventoSignificativo/finEventoSignificativo", dato);
    },
    consultaEventoSignificativo(login){        
        return Api().get(`/api/apiEventoSignificativo/consultaEventoSignificativo/${login}`);
    },

}
