<template>
  <v-card>
    <v-card-title>     
      <div class="flex-grow-1"></div>
      <v-text-field
        v-model="search"
        append-icon="search"
        label="Search"
        single-line                
        hide-details
      ></v-text-field>
    </v-card-title>

    <v-data-table       
       :headers="headers"
       :items="facturas"       
      :search="search"            
      class="elevation-1"
      style="font-size:10px;"
    >
    <template v-slot:top>
      <v-toolbar flat color="white">
        <v-toolbar-title> Lista de Facturas Electr&oacute;nicas Estandar</v-toolbar-title>
        <v-divider
          class="mx-4"
          inset
          vertical
        ></v-divider>
        <div class="flex-grow-1"></div>
        <v-dialog v-model="dialog" max-width="80%">         
          <v-card>
            <v-card-title>
              <span class="headline">Factura Electr&oacute;nica Estandar</span>
            </v-card-title>
            <v-card-text style="height: 800px;">
              <v-container>
                <v-row justify="center">
                  <v-col cols="1" sm="1" md="1">
                    <v-text-field style="font-size:14px;" v-model="apiFactura.numeroFactura" label="Numero Factura"></v-text-field>
                  </v-col>
                  <v-col cols="3" sm="3" md="3">
                    <v-text-field style="font-size:12px;" v-model="apiFactura.cuf" label="CUF"></v-text-field>
                  </v-col>
                  <v-col cols="2" sm="2" md="2">
                    <v-text-field style="font-size:14px;" v-model="apiFactura.utcFechaEmision" label="Fecha Emision"></v-text-field>
                  </v-col>                    
                   <v-col cols="1" sm="1" md="1">
                    <v-text-field style="font-size:14px;" v-model="apiFactura.apiDosificacion.cuis" label="CUIS"></v-text-field>
                  </v-col>                                  
                   <v-col cols="3" sm="3" md="3">
                    <v-text-field style="font-size:12px;" v-model="apiFactura.apiConfiguracion.cufd" label="CUFD"></v-text-field>
                  </v-col>                   
                   <v-col cols="2" sm="2" md="2">
                    <v-text-field style="font-size:14px;" v-model="apiFactura.apiConfiguracion.fechaVigencia" label="Fecha Vigencia"></v-text-field>
                  </v-col>                  
                </v-row>
                <v-row justify="center">
                  <v-col cols="2" sm="2" md="2">
                    <v-text-field style="font-size:14px;" v-model="apiFactura.apiCliente.codigoCliente" label="Codigo Cliente"></v-text-field>
                  </v-col>
                  <v-col cols="3" sm="3" md="3">
                    <v-text-field style="font-size:14px;" v-model="apiFactura.apiCliente.nombreRazonSocial" label="Nombre Razon Social"></v-text-field>
                  </v-col>
                  <v-col cols="2" sm="2" md="2">
                    <v-text-field style="font-size:14px;" v-model="apiFactura.apiCliente.parTipoDocumentoIdentidad.descripcion" label="Tipo Documento Identidad"></v-text-field>
                  </v-col>
                  <v-col cols="2" sm="2" md="2">
                    <v-text-field style="font-size:14px;" v-model="apiFactura.apiCliente.numeroDocumento" label="Numero Documento"></v-text-field>
                  </v-col>
                  <v-col cols="3" sm="3" md="3">
                    <v-text-field style="font-size:14px;" v-model="apiFactura.apiCliente.correoElectronico" label="Correo Electronico"></v-text-field>
                  </v-col>
                </v-row>  
                 <v-row justify="center">
                  <v-col cols="2" sm="2" md="2">
                    <v-text-field style="font-size:14px;" v-model="apiFactura.parTipoMetodoPago.descripcion" label="Tipo Metodo Pago"></v-text-field>
                  </v-col>
                  <v-col cols="2" sm="2" md="2">
                    <v-text-field style="font-size:14px;" v-model="apiFactura.numeroTarjeta" label="Numero Tarjeta"></v-text-field>
                  </v-col>
                  <v-col cols="2" sm="2" md="2">
                    <v-text-field style="font-size:14px;" v-model="apiFactura.parTipoMoneda.descripcion" label="Tipo Moneda"></v-text-field>
                  </v-col>
                  <v-col cols="2" sm="2" md="2">
                    <v-text-field style="font-size:14px;" v-model="apiFactura.tipoCambio" label="Tipo de Cambio"></v-text-field>
                  </v-col>
                  <v-col cols="2" sm="2" md="2">
                    <v-text-field style="font-size:14px;" v-model="apiFactura.parTipoEmision.descripcion" label="Tipo de Emision"></v-text-field>
                  </v-col>
                  <v-col cols="2" sm="2" md="2">
                    <v-text-field style="font-size:14px;" v-model="apiFactura.usuario" label="Usuario"></v-text-field>
                  </v-col>
                </v-row>    
                <v-row justify="center">
                  <v-col cols="2" sm="2" md="2">
                    <v-text-field style="font-size:14px;" v-model="apiFactura.montoTotal" label="Monto Total"></v-text-field>
                  </v-col>
                  <v-col cols="2" sm="2" md="2">
                    <v-text-field style="font-size:14px;" v-model="apiFactura.montoDescuento" label="Monto Descuento"></v-text-field>
                  </v-col>
                  <v-col cols="2" sm="2" md="2">
                    <v-text-field style="font-size:14px;" v-model="apiFactura.montoTotalMoneda" label="Monto Total Moneda"></v-text-field>
                  </v-col>
                  <v-col cols="2" sm="2" md="2">
                    <v-text-field style="font-size:14px;" v-model="apiFactura.codigoRecepcion" label="Codigo de Recepcion"></v-text-field>
                  </v-col>
                </v-row>    
                <v-row>
                    <v-col cols="12" sm="12" md="13">
                    <v-text-field style="font-size:14px;" v-model="apiFactura.parLeyendaFactura.descripcion" label="Leyenda Factura"></v-text-field>
                  </v-col>
                </v-row>       
              </v-container>              
               <v-container>
                 <template>
                   <span class="headline">Detalle</span>
                    <v-simple-table fixed-header height="300px">
                      <template v-slot:default>
                        <thead>
                          <tr>
                            <th class="text-left">CODIGO SIN</th>                            
                            <th class="text-left">CODIGO</th>
                            <th class="text-left">DESCRIPCION</th>
                            <th class="text-left">U.M.</th>                            
                            <th class="text-left">NUMERO SERIE</th>
                            <th class="text-left">NUMERO IMEI</th>
                            <th class="text-left">CANTIDAD</th>
                            <th class="text-left">UNITARIO</th>
                            <th class="text-left">DESCUENTO</th>
                            <th class="text-left">SUB TOTAL</th>
                          </tr>
                        </thead>
                        <tbody>
                          <tr v-for="item in apiFactura.apiFacturaDetalle" :key="item.idFacturaDetalle">
                            <td>{{ item.apiItemHomologado.apiItem.codigoProductoSin }}</td>                            
                            <td>{{ item.apiItemHomologado.codigoProducto }}</td>
                            <td>{{ item.apiItemHomologado.descripcion }}</td>
                            <td>{{ item.apiItemHomologado.parUnidadMedida.descripcion }}</td>
                            <td>{{ item.apiItemHomologado.numeroSerie }}</td>
                            <td>{{ item.apiItemHomologado.numeroImei }}</td>
                            <td>{{ item.cantidad }}</td>
                            <td>{{ item.precioUnitario }}</td>
                            <td>{{ item.montoDescuento }}</td>
                            <td>{{ item.subTotal }}</td>
                          </tr>
                        </tbody>
                      </template>
                    </v-simple-table>
                  </template>
               </v-container>  
            </v-card-text>
            <v-divider></v-divider>
            <v-card-actions>
              <div class="flex-grow-1"></div>
              <v-btn color="blue darken-1" text @click="close">Salir</v-btn>              
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-toolbar>
    </template>
    
     <template v-slot:item.action="{ item }">
      <v-icon
        medium
        class="mr-2"
        @click="viewItem(item)"
      >
        search
      </v-icon>
      <v-icon
        medium
        class="mr-2"
        @click="descargarReporte(item)"
      >      
        printer
      </v-icon>
      </template>
      <template v-slot:item.parCondicion.codigo="{ item }">
          <v-chip :color="getColor(item.parCondicion.codigo)" dark>{{ item.parCondicion.codigo }}</v-chip>
      </template>
    </v-data-table>
  </v-card>
</template>
<script>
  import ApiFacturacionService from '../services/ApiFacturacionService';
  import ApiFactura from '../models/ApiFactura';  

  export default {
    data () {
      return {
        dialog: false,        
        selected: [], 
        search: '',
        headers: [
          {
            text: 'Nro Factura',
            align: 'left',
            sortable: false,
            value: 'numeroFactura',
          },                    
          { text: 'CUF', value: 'cuf' },
          { text: 'Fecha Emision', value: 'utcFechaEmision', width:200 },
          { text: 'Nombre Razon Social', value: 'apiCliente.nombreRazonSocial', width:300 },
          { text: 'Metodo Pago', value: 'parTipoMetodoPago.descripcion' },
          { text: 'Monto Total', value: 'montoTotal' },
          { text: 'Moneda', value: 'parTipoMoneda.descripcion' },
          { text: 'Tipo Cambio', value: 'tipoCambio' },
          { text: 'Anulado', value: 'parCondicion.codigo' },
          { text: 'Actions', value: 'action', sortable: false },          
        ],
        facturas: [],
        apiFactura: ApiFactura,        
      }
    },   
    created () {
      this.initialize();      
    },
     methods: {
      initialize () {       
        let login = sessionStorage.getItem('usuario');       
        const codigoDocumentoFiscal = 1;
        const codigoDocumentoSector = 1;
        const codigoTipoModalidad = 1;
        ApiFacturacionService.leerPorLogin(login, codigoDocumentoFiscal, codigoDocumentoSector, codigoTipoModalidad)
        .then(response => {
            this.facturas = response.data;    
           // console.log("datos",  response.data);                         
        })       
        .catch(e => { console.error(e)});                    
      },
      viewItem (item) {      
        //console.log("item", item)
        this.apiFactura = item;        
        //console.log("cuis", this.apiFactura.apiDosificacion.cuis)
        this.dialog = true
      },
      descargarReporte (item) {
        ApiFacturacionService.generarReporte(item.idFactura)
        .then(response => {            
            const url = window.URL.createObjectURL(response.data);
            //console.log(url);
            const a = document.createElement('a');
            a.setAttribute('style', 'display:none');
            document.body.appendChild(a);
            a.href = url;
            a.download = 'FacturaElectronica.pdf'
            a.click();
        })       
        .catch(e => { console.error(e)});                    
      },
      getColor (anulado) {
        console.log("anulado es ", anulado);
        if (anulado == "SI") return 'red'
        else return 'green'        
      },
      close () {
        this.dialog = false;     
      },
     }
  }
</script>

