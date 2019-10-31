/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.impl.api;

import com.bap.api.model.api.ApiCliente;
import com.bap.api.repo.api.ApiClienteRepo;
import com.bap.api.services.api.AdmConsultasService;
import com.bap.api.services.api.ApiClienteService;
import com.bap.api.services.par.ParTipoClienteService;
import com.bap.api.services.par.ParTipoDocumentoIdentidadService;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ruth
 */
@Service
public class ApiClienteServiceImpl implements ApiClienteService {

    @Autowired
    private ApiClienteRepo repo;

    @Autowired
    ParTipoDocumentoIdentidadService parTipoDocumentoIdentidadService;

    @Autowired
    ParTipoClienteService parTipoClienteService;

    @Autowired
    AdmConsultasService admConsultasService;

    @Override
    public ApiCliente registrar(ApiCliente t) {
        t.setUsuarioAlta("admin");
        t.setFechaAlta(new Date());
        return repo.save(t);
    }

    @Override
    public ApiCliente modificar(ApiCliente t) {
        t.setUsuarioModificacion("admin");
        t.setFechaModificacion(new Date());
        return repo.save(t);
    }

    @Override
    public ApiCliente leerPorId(Long id) {
        if (repo.findById(id).isPresent()) {
            return repo.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public List<ApiCliente> listar() {
        return repo.findAll();
    }

    @Override
    public void eliminar(ApiCliente t) {
        t.setFechaBaja(new Date());
        t.setUsuarioBaja("admin");
        repo.save(t);
    }

    @Override
    public ApiCliente leerPorIdEmpresaAndCodigoCliente(Long idEmpresa, String codigoCliente) {
        return repo.findByIdEmpresaAndCodigoCliente(idEmpresa, codigoCliente);
    }

    @Override
    public List<ApiCliente> listarClientePorIdEmpresa(Long idEmpresa) {
        return repo.listarClienteByIdEmpresa(idEmpresa);
    }

//    public int lookForRowWithValue(Sheet sheet, String term) {       //Iterate through each rows one by one
//        Iterator<Row> rowIterator = sheet.iterator();
//        boolean found = false;
//        Cell cell = null;
//        while (rowIterator.hasNext()) {
//            Row row = rowIterator.next();
//            Iterator<Cell> cellIterator = row.cellIterator();
//            while (cellIterator.hasNext()) {
//                cell = cellIterator.next();
//                if (cell.getCellType() == Cell.CELL_TYPE_STRING && cell.getStringCellValue().equals(term)) {
//                    found = true;
//                    break;
//                }
//            }
//            if (found) {
//                break;
//            }
//        }
//        if (cell != null) {
//            return cell.getRowIndex();
//        } else {
//            return -1;
//        }
//    }

    @Override
    public void cargarClientes(InputStream fileInputStream) throws Exception {
//        try {
////            System.out.println("...cargando clientes...................................");
//            List<ApiCliente> listaCliente = new ArrayList<ApiCliente>();
//            Workbook workbook = WorkbookFactory.create(fileInputStream);
//            Date fecha;
//            Sheet sheet = workbook.getSheetAt(0);
//            int startingRow = 1;
//            int endingRow = lookForRowWithValue(sheet, "");
//            sheet = workbook.getSheetAt(0);
//
//            ApiCliente apiCliente;
//            ApiCliente cliente;
//            ApiCliente cliente2;
//            ParTipoDocumentoIdentidad parTipoDocumentoIdentidad;
//            ParTipoCliente parTipoCliente;
//            ConsultaParametros consultaParametros;
//            boolean swContinuar;
//
////            System.out.println("...startin Row :::" + startingRow);
//            while (startingRow <= endingRow) {
//                Row row = sheet.getRow(startingRow);
//                Iterator<Cell> cellIterator = row.cellIterator();
//                apiCliente = new ApiCliente();
//                parTipoDocumentoIdentidad = new ParTipoDocumentoIdentidad();
//                parTipoCliente = new ParTipoCliente();
//                consultaParametros = new ConsultaParametros();
//
//                while (cellIterator.hasNext()) {
//                    Cell cell = cellIterator.next();
//                    switch (cell.getCellType()) {
//                        case Cell.CELL_TYPE_STRING:
//
//                            if (cell.getColumnIndex() == 0) { //empresa
//                                apiCliente.setEmpresa(cell.getStringCellValue());
//                            }
//                            if (cell.getColumnIndex() == 1) { //codigoCliente
//                                apiCliente.setCodigoCliente(cell.getStringCellValue());
//                            }
//                            if (cell.getColumnIndex() == 2)//Nombre/Razon Social
//                            {
//                                apiCliente.setNombreRazonSocial(cell.getStringCellValue());
//                            }
//                            if (cell.getColumnIndex() == 5) //numero Documento
//                            {
//                                apiCliente.setNumeroDocumento(cell.getStringCellValue());
//                            }
//                            if (cell.getColumnIndex() == 6) //complemento
//                            {
//                                apiCliente.setComplemento(cell.getStringCellValue());
//                            }
//                            if (cell.getColumnIndex() == 7) //email
//                            {
//                                apiCliente.setCorreoElectronico(cell.getStringCellValue());
//                            }
//                            if (cell.getColumnIndex() == 8)//tipo cliente
//                            {
//                                apiCliente.setTipoCliente(cell.getStringCellValue());
//                            }
//                            if (cell.getColumnIndex() == 9)//usuario
//                            {
//                                apiCliente.setUsuarioAux(cell.getStringCellValue());
//                            }
//                            break;
//                        case Cell.CELL_TYPE_NUMERIC:
//                            if (cell.getColumnIndex() == 3) { //Nro
//                                apiCliente.setNitEmisor((long) cell.getNumericCellValue());
//                            }
//                            if (cell.getColumnIndex() == 4) { //tipo documento
//                                apiCliente.setTipoDocumento((long) cell.getNumericCellValue());
//                            }
//
//                            break;
//                    }
//                }
//                swContinuar = true;
//                consultaParametros = admConsultasService.consultarDatosUsuario(apiCliente.getUsuarioAux());
//                parTipoDocumentoIdentidad = parTipoDocumentoIdentidadService.leerPorCodigo(apiCliente.getTipoDocumento());
//                parTipoCliente = parTipoClienteService.leerPorCodigo(apiCliente.getTipoCliente());
//                if (consultaParametros == null) {
//                    swContinuar = false;
//                }
//
//                if (parTipoDocumentoIdentidad == null) {
//                    swContinuar = false;
//                }
//                if (parTipoCliente == null) {
//                    swContinuar = false;
//                }
//                cliente = checkIfThereCustomerByNit(apiCliente.getNitEmisor());
////                System.out.println("cliente ==" + cliente);
//                if (cliente == null) {
//                    swContinuar = false;
//                }
//                cliente2 = checkIfThereCustomerByDoc(apiCliente.getNumeroDocumento());
////                System.out.println("cliente2 ==" + cliente2);
//                if (cliente == null) {
//                    swContinuar = false;
//                }
//
//                if ((!swContinuar)) {
//                    apiCliente.setIdEmpresa(consultaParametros.getIdEmpresa());
//                    apiCliente.setParTipoDocumentoIdentidad(parTipoDocumentoIdentidad);
//                    apiCliente.setParTipoCliente(parTipoCliente);
//                    registrar(apiCliente);
//
//                    System.out.println("...ser registro cliente ==>" + registrar(apiCliente).getIdCliente());
//                }
//
//                startingRow++;
//            }
//
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//
//        } finally {
//            // release resources, if any
////            log.info("LIST::: " + listaConciliacionDebitoFiscal);
//        }

    }

//    @Override
//    public ApiCliente checkIfThereCustomerByNit(Long nit) throws Exception {
//        return repo.getClienteByNit(nit);
//    }

    @Override
    public ApiCliente checkIfThereCustomerByDoc(String numeroDocumento) throws Exception {
        return repo.getClienteByNroDocumento(numeroDocumento);
    }

}
