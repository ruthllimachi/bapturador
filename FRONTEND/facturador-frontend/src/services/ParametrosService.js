import Api from '@/services/Api'

export default {
    getParSincronizacion() {
        return Api().get('/api/parSincronizacion');
    },
    getParCondicion() {
        return Api().get('/api/parCondicion');
    },
    getParTipoModalidad() {
        return Api().get('/api/parTipoModalidad');
    },
    getParTipoPuntoVenta() {
        return Api().get('/api/parTipoPuntoVenta');
    },
    getParTipoDocumentoIdentidad() {
        return Api().get('/api/parTipoDocumentoIdentidad');
    },
    getParUnidadMedida() {
        return Api().get('/api/parUnidadMedida');
    },
    getParEventoSignificativo() {
        return Api().get('/api/parEventoSignificativo');
    }
}
