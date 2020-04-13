<template>
  <div align="center">
    <v-snackbar v-model="snackbar" top color="success">
      Punto de Venta Cerrado      
      <v-btn color="pink" text @click="snackbar = false">Close</v-btn>
    </v-snackbar>
    <v-flex xs6>
      <v-card flat>
        <v-card-title class="text-center justify-center py-6">
          <h1 class="display-1 basil--text">Cierre Punto de Venta</h1>
        </v-card-title>
        <v-card-text>
            <v-form ref="form" v-model="valid" lazy-validation>
                <v-combobox
                    v-model="apiSucursal"
                    :items="listaSucursal"
                    item-text="nombreSucursal"
                    item-value="codigoSucursal"
                    label="Sucursales"
                    :rules="rules"
                    required                    
                ></v-combobox>
                <v-btn
                  :disabled="!valid"
                  color="success"
                  class="mr-4"  
                  @click="consultar"        
                  >Consultar Punto de Venta
                </v-btn>
            </v-form>
            <br/><br/>
            <v-data-table
                :headers="headers"
                :items="listaApiPuntoVenta"
                :items-per-page="5"
                class="elevation-1"
            >
            <template v-slot:top>              
                <v-dialog v-model="dlgCierre" max-width="600px" >
                  <v-card flat>
                      <v-card-title>
                        <span class="headline">Cierre Punto de Venta</span>
                      </v-card-title>
                      <v-card-text>
                        <v-col cols="2" >
                          <v-text-field v-model="apiSucursal.codigoSucursal" label="Codigo Sucursal" disabled></v-text-field>
                        </v-col>                                 
                        <v-col cols="12" >
                          <v-text-field v-model="apiSucursal.nombreSucursal" label="Nombre Sucursal" disabled></v-text-field>
                        </v-col>           
                        <v-col cols="5" >
                          <v-text-field v-model="apiPuntoVenta.codigoPuntoVenta" label="Codigo Punto Venta" disabled></v-text-field>
                        </v-col>                         
                        <v-col cols="12" >
                          <v-text-field v-model="apiPuntoVenta.nombrePuntoVenta" label="Nombre Punto Venta" disabled></v-text-field>
                        </v-col>                   
                        <v-col cols="8" >
                          <v-text-field v-model="apiPuntoVenta.descripcion" label="Tipo Punto venta" disabled></v-text-field>
                        </v-col>                         
                      </v-card-text>
                      <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="blue darken-1" text @click="close">Cancelar</v-btn>
                        <v-btn color="blue darken-1" text @click="cerrar">Cerrar</v-btn>
                      </v-card-actions>        
                      <br/> 
                      <v-container style="height: 30px;">
                            <v-progress-linear v-show="progressCierre" indeterminate color="teal"></v-progress-linear>
                      </v-container>                    
                      <br/> <br/>                                   
                  </v-card>                                      
                </v-dialog>              
            </template>

            <template v-slot:item.action="{ item }">                 
              <v-icon
                small
                @click="deleteItem(item)"
              >
                delete
              </v-icon>
            </template>            
            </v-data-table>
        </v-card-text>        
      </v-card>
      <v-container style="height: 400px;">
        <v-progress-linear v-show="progress" indeterminate color="teal"></v-progress-linear>
      </v-container>
    </v-flex>
    <v-dialog v-model="dialog" width="500">
      <v-card>
        <v-card-title class="headline grey lighten-2" primary-title>Mensajes SOAP:</v-card-title>
        <v-card-text>
          <v-alert type="error" v-show="error!==''">{{ error }}</v-alert>
          <v-simple-table>
            <thead>
              <tr>
                <th class="text-left">Codigo</th>
                <th class="text-left">Descripcion</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="item in listaParMensajeServicio" :key="item.codigo">
                <td>{{ item.codigo }}</td>
                <td>{{ item.descripcion }}</td>
              </tr>
            </tbody>
          </v-simple-table>

          <v-text-field
              v-model="parMensajeFacturador.descripcion"
              label="Respuesta"
              disabled
          ></v-text-field>
        </v-card-text>        
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="primary" text @click="dialog = false;error = ''">Continuar</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>
<script>

import ApiPuntoVentaService from "../services/ApiPuntoVentaService";
import ApiSucursalService from "../services/ApiSucursalService";
import AdministradorService from "../services/AdministradorService";
import ParMensajeFacturador  from "../models/ParMensajeFacturador"; 
import SolicitudCliente from "../models/SolicitudCliente";
import ApiSucursal from "../models/ApiSucursal";
import ApiPuntoVenta from "../models/ApiPuntoVenta";

export default {
  data() {
    return {
        headers: [
        {
            text: 'Codigo',            
            sortable: true,
            value: 'codigoPuntoVenta',
            },
            { text: 'Nombre Punto Venta', value: 'nombrePuntoVenta' },
            { text: 'Tipo Punto Venta', value: 'descripcion' },
             { text: 'Actions', value: 'action', sortable: false },
        ],   
        valid: true,
        rules: [v => !!v || "Dato requerido"],
        listaSucursal: [],
        listaApiPuntoVenta: [],
        apiSucursal: ApiSucursal,      
        apiPuntoVenta: ApiPuntoVenta,
        parMensajeFacturador: ParMensajeFacturador,
        listaParMensajeServicio: [],
        admSistema: {},
        admEmpresa: {},
        dialog: false,
        dlgCierre: false,
        progress: false,
        progressCierre: false,
        snackbar: false,      
        error: ""
    };
  },
  created() {
    let login = sessionStorage.getItem("usuario");
    AdministradorService.getParametros(login)
      .then(response => {
        AdministradorService.getAdmEmpresa(response.data.idEmpresa)
          .then(response => {
            this.admEmpresa = response.data[0];
            ApiSucursalService.listaByEmpresaTieneCuisVigente(
              this.admEmpresa.idEmpresa
            )
              .then(response => {
                this.listaSucursal = response.data;
                console.log("lista sucursales ", this.listaSucursal);
              })
              .catch(e => {
                console.error(e);
              });
          })
          .catch(e => {
            console.error(e);
          });
      })
      .catch(e => {
        console.error(e);
      });

    AdministradorService.getAdmSistema()
      .then(response => {
        this.admSistema = response.data[0];
      })
      .catch(e => {
        console.error(e);
      });
  },
  methods: {
    consultar() {
        let solicitudCliente = SolicitudCliente;
        solicitudCliente.codigoAmbiente = this.admEmpresa.codigoAmbiente;
        solicitudCliente.codigoSistema = this.admSistema.codigoSistema;
        solicitudCliente.codigoSucursal = this.apiSucursal.codigoSucursal;
        solicitudCliente.apiSucursal = this.apiSucursal;
        solicitudCliente.cuis = this.apiSucursal.cuis;
        solicitudCliente.nitEmpresa = this.admEmpresa.nitEmpresa;
        solicitudCliente.login = this.login;
        
        this.progress = true;        
        ApiPuntoVentaService.consultaPuntoVenta(solicitudCliente)
        .then(response => {
        this.progress = false;            
        if (response.data.transaccion) {
            this.listaApiPuntoVenta = response.data.listaApiPuntoVenta;
        } else {
            this.listaParMensajeServicio =
            response.data.listaParMensajeServicio;
            this.parMensajeFacturador = response.data.parMensajeFacturador;  
            this.dialog = true;
        }
        })
        .catch(e => {
          console.error(e);
          this.error = "A OCURRIDO UN ERROR !!!!";
          this.progress = false;
          this.dialog = true;
        });
    },  
    deleteItem (item) {
      console.log("item es ", item);
      this.apiPuntoVenta = item;
      this.dlgCierre = true;
    },   
   close () {
     this.dlgCierre = false;        
    },
    cerrar() {      
      let login = sessionStorage.getItem("usuario");

      let solicitudCliente = SolicitudCliente;
      solicitudCliente.codigoAmbiente = this.admEmpresa.codigoAmbiente;
      solicitudCliente.codigoPuntoVenta = this.apiPuntoVenta.codigoPuntoVenta;
      solicitudCliente.codigoSistema = this.admSistema.codigoSistema;
      solicitudCliente.codigoSucursal = this.apiSucursal.codigoSucursal;
      solicitudCliente.apiSucursal = this.apiSucursal;
      solicitudCliente.cuis = this.apiSucursal.cuis;
      solicitudCliente.nitEmpresa = this.admEmpresa.nitEmpresa;
      solicitudCliente.login = login;
      //console.log(solicitudCliente);

      this.progressCierre = true;        
      ApiPuntoVentaService.cierrePuntoVenta(solicitudCliente)
      .then(response => {        
        this.progressCierre = false;         
        this.dlgCierre = false;
        if (response.data.transaccion) {                   
          this.listaApiPuntoVenta = [];
          this.listaSucursal = [];
          this.snackbar = true;
        } else {
          this.listaParMensajeServicio =
            response.data.listaParMensajeServicio;
          this.parMensajeFacturador = response.data.parMensajeFacturador;  
          this.dialog = true;
        }
      })
      .catch(e => {
        console.error(e);
        this.error = "A OCURRIDO UN ERROR !!!!";
        this.progressCierre = false;         
        this.dialog = true;
      });      
    }
  }
};
</script>//vaca nic
