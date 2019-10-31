import ParTipoDocumentoIdentidad from './ParTipoDocumentoIdentidad';
import ParTipoCliente from './ParTipoCliente';

let ApiCliente = {
    idCliente: null,
    idEmpresa: null,
    codigoCliente: '',
    nombreRazonSocial: '',
    parTipoDocumentoIdentidad: ParTipoDocumentoIdentidad,
    numeroDocumento: 0,
    correoElectronico: '',
    tipoCliente: null,
    parTipoCliente: ParTipoCliente,
    complemento:''

}
export default ApiCliente;
