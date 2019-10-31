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
        <v-toolbar-title> Lista de Notas Fiscal Computarizada Credito/Debito </v-toolbar-title>
        <v-divider
          class="mx-4"
          inset
          vertical
        ></v-divider>
        <div class="flex-grow-1"></div>
        <v-dialog v-model="dialog" max-width="80%">         
          <v-card>
            <v-card-title>
              <span class="headline">Nota Fiscal Computarizada Credito/Debito</span>
            </v-card-title>
            <v-card-text style="height: 800px;">
              <v-container>
                <v-row justify="center">
                  <v-col cols="1" sm="1" md="1">
                    <v-text-field style="font-size:14px;" v-model="apiNotaFiscalCreditoDebito.numeroNotaCreditoDebito" label="Numero NCD"></v-text-field>
                  </v-col>
                  <v-col cols="3" sm="3" md="3">
                    <v-text-field style="font-size:12px;" v-model="apiNotaFiscalCreditoDebito.cuf" label="CUF"></v-text-field>
                  </v-col>
                  <v-col cols="2" sm="2" md="2">
                    <v-text-field style="font-size:14px;" v-model="apiNotaFiscalCreditoDebito.utcFechaEmision" label="Fecha Emision"></v-text-field>
                  </v-col>                    
                   <v-col cols="1" sm="1" md="1">
                    <v-text-field style="font-size:14px;" v-model="apiNotaFiscalCreditoDebito.apiDosificacion.cuis" label="CUIS"></v-text-field>
                  </v-col>                                  
                   <v-col cols="3" sm="3" md="3">
                    <v-text-field style="font-size:12px;" v-model="apiNotaFiscalCreditoDebito.apiConfiguracion.cufd" label="CUFD"></v-text-field>
                  </v-col>                   
                   <v-col cols="2" sm="2" md="2">
                    <v-text-field style="font-size:14px;" v-model="apiNotaFiscalCreditoDebito.apiConfiguracion.fechaVigencia" label="Fecha Vigencia"></v-text-field>
                  </v-col>                  
                </v-row>        
                 <v-row justify="center">
                  <v-col cols="2" sm="2" md="2">
                    <v-text-field style="font-size:14px;" v-model="apiNotaFiscalCreditoDebito.apiFactura.parTipoMetodoPago.descripcion" label="Tipo Metodo Pago"></v-text-field>
                  </v-col>
                  <v-col cols="2" sm="2" md="2">
                    <v-text-field style="font-size:14px;" v-model="apiNotaFiscalCreditoDebito.apiFactura.numeroTarjeta" label="Numero Tarjeta"></v-text-field>
                  </v-col>
                  <v-col cols="2" sm="2" md="2">
                    <v-text-field style="font-size:14px;" v-model="apiNotaFiscalCreditoDebito.apiFactura.parTipoMoneda.descripcion" label="Tipo Moneda"></v-text-field>
                  </v-col>
                  <v-col cols="2" sm="2" md="2">
                    <v-text-field style="font-size:14px;" v-model="apiNotaFiscalCreditoDebito.apiFactura.tipoCambio" label="Tipo de Cambio"></v-text-field>
                  </v-col>
                  <v-col cols="2" sm="2" md="2">
                    <v-text-field style="font-size:14px;" v-model="apiNotaFiscalCreditoDebito.apiFactura.parTipoEmision.descripcion" label="Tipo de Emision"></v-text-field>
                  </v-col>
                  <v-col cols="2" sm="2" md="2">
                    <v-text-field style="font-size:14px;" v-model="apiNotaFiscalCreditoDebito.usuario" label="Usuario"></v-text-field>
                  </v-col>
                </v-row>    
                <v-row justify="center">
                  <v-col cols="2" sm="2" md="2">
                    <v-text-field style="font-size:14px;" v-model="apiNotaFiscalCreditoDebito.montoTotalOriginal" label="Monto Total"></v-text-field>
                  </v-col>
                  <v-col cols="2" sm="2" md="2">
                    <v-text-field style="font-size:14px;" v-model="apiNotaFiscalCreditoDebito.montoTotalDevuelto" label="Monto Descuento"></v-text-field>
                  </v-col>
                  <v-col cols="2" sm="2" md="2">
                    <v-text-field style="font-size:14px;" v-model="apiNotaFiscalCreditoDebito.montoEfectivoCreditoDebito" label="Monto Total Moneda"></v-text-field>
                  </v-col>
                  <v-col cols="2" sm="2" md="2">
                    <v-text-field style="font-size:14px;" v-model="apiNotaFiscalCreditoDebito.codigoRecepcion" label="Codigo de Recepcion"></v-text-field>
                  </v-col>
                </v-row>    
                <v-row>
                    <v-col cols="12" sm="12" md="13">
                    <v-text-field style="font-size:14px;" v-model="apiNotaFiscalCreditoDebito.parLeyendaFactura.descripcion" label="Leyenda Factura"></v-text-field>
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
                            <th class="text-left">CANTIDAD</th>
                            <th class="text-left">UNITARIO</th>                            
                            <th class="text-left">SUB TOTAL</th>
                            <th class="text-left">TIPO TRANSACCION</th>
                          </tr>
                        </thead>
                        <tbody>
                          <tr v-for="item in apiNotaFiscalCreditoDebito.apiNotaFiscalCreditoDebitoDetalle" :key="item.idNotaCreditoDebitoDetalle">
                            <td>{{ item.apiItemHomologado.apiItem.codigoProductoSin }}</td>                            
                            <td>{{ item.apiItemHomologado.codigoProducto }}</td>
                            <td>{{ item.apiItemHomologado.descripcion }}</td>
                            <td>{{ item.apiItemHomologado.parUnidadMedida.descripcion }}</td>
                            <td>{{ item.cantidad }}</td>
                            <td>{{ item.precioUnitario }}</td>                            
                            <td>{{ item.subTotal }}</td>
                            <td>{{ item.parTipoTransaccion.descripcion }}</td>
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
    </template>
    <template v-slot:item.parCondicion.codigo="{ item }">
          <v-chip :color="getColor(item.parCondicion.codigo)" dark>{{ item.parCondicion.codigo }}</v-chip>
     </template>
    </v-data-table>
  </v-card>
</template>
<script>
  import ApiNotaFiscalCreditoDebitoService from '../services/ApiNotaFiscalCreditoDebitoService';
  import ApiNotaFiscalCreditoDebito from '../models/ApiNotaFiscalCreditoDebito';  

  export default {
    data () {
      return {
        dialog: false,
        search: '',
        headers: [
          {
            text: 'Nro NCD ',
            align: 'left',
            sortable: false,
            value: 'numeroNotaCreditoDebito',
          },                    
          { text: 'CUF', value: 'cuf' },
          { text: 'Fecha Emision', value: 'utcFechaEmision', width:200 },
          { text: 'Nombre Razon Social', value: 'apiFactura.apiCliente.nombreRazonSocial', width:300 },
          { text: 'Metodo Pago', value: 'apiFactura.parTipoMetodoPago.descripcion' },
          { text: 'Monto Total Original', value: 'montoTotalOriginal' },
          { text: 'Moneda', value: 'apiFactura.parTipoMoneda.descripcion' },     
           { text: 'Anulado', value: 'parCondicion.codigo' },     
          { text: 'Actions', value: 'action', sortable: false },          
        ],
        facturas: [],
        apiNotaFiscalCreditoDebito: ApiNotaFiscalCreditoDebito,        
      }
    },   
    created () {
      this.initialize();      
    },
     methods: {
      initialize () {       
        let login = sessionStorage.getItem('usuario');       
        const codigoDocumentoFiscal = 2;
        const codigoDocumentoSector = 18;
        const codigoTipoModalidad = 2;
        ApiNotaFiscalCreditoDebitoService.leerPorLogin(login, codigoDocumentoFiscal, codigoDocumentoSector, codigoTipoModalidad)
        .then(response => {
            this.facturas = response.data;    
            console.log("datos",  response.data);                         
        })       
        .catch(e => { console.error(e)});                    
      },
      viewItem (item) {      
        //console.log("item", item)
        this.apiNotaFiscalCreditoDebito = item;        
        //console.log("cuis", this.apiNotaFiscalCreditoDebito.apiDosificacion.cuis)
        this.dialog = true
      },
      getColor (anulado) {      
        if (anulado == "SI") return 'red'
        else return 'green'        
      },
      close () {
        this.dialog = false;     
      },
     }
  }
</script>

