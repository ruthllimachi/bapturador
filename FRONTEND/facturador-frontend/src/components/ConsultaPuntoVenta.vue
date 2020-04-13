<template>
  <div align="center">
      <v-snackbar v-model="snackbar" top>
      ¡¡¡ No existe Datos !!!
      <v-btn color="pink" text @click="snackbar = false">Close</v-btn>
    </v-snackbar>
    <v-flex xs6>
      <v-card flat>
        <v-card-title class="text-center justify-center py-6">
          <h1 class="display-1 basil--text">Consulta Punto de Venta</h1>
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
          <br/>
          <v-simple-table
            :dense="dense"
            :fixed-header="fixedHeader"
            :height="height"
            >
            <template v-slot:default>
              <thead>
                  <tr>
                  <th class="text-left">Codigo</th>
                  <th class="text-left">Nombre Punto Venta</th>
                  <th class="text-left">Tipo Punto Venta</th>
                  </tr>
              </thead>
              <tbody>
                  <tr v-for="item in listaApiPuntoVenta" :key="item.codigo">
                  <td>{{ item.codigoPuntoVenta }}</td>
                  <td>{{ item.nombrePuntoVenta }}</td>
                  <td>{{ item.descripcion }}</td>
                  </tr>
              </tbody>
            </template>  
          </v-simple-table>
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

export default {
  data() {
    return {
      dense: false,
      fixedHeader: true,
      height: 500,
      valid: true,
      rules: [v => !!v || "Dato requerido"],
      listaSucursal: [],
      listaApiPuntoVenta: [],
      apiSucursal: null,      
      parMensajeFacturador: ParMensajeFacturador,
      listaParMensajeServicio: [],
      admSistema: {},
      admEmpresa: {},
      dialog: false,
      progress: false,
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
      if (this.$refs.form.validate()) {        
        let login = sessionStorage.getItem("usuario");
        let solicitudCliente = SolicitudCliente;
        solicitudCliente.codigoAmbiente = this.admEmpresa.codigoAmbiente;
        solicitudCliente.codigoSistema = this.admSistema.codigoSistema;
        solicitudCliente.codigoSucursal = this.apiSucursal.codigoSucursal;
        solicitudCliente.apiSucursal = this.apiSucursal;
        solicitudCliente.cuis = this.apiSucursal.cuis;
        solicitudCliente.nitEmpresa = this.admEmpresa.nitEmpresa;
        solicitudCliente.login = login;
       
        this.progress = true;        
        ApiPuntoVentaService.consultaPuntoVenta(solicitudCliente)
          .then(response => {           
            this.progress = false;            
            if (response.data.transaccion) {
              this.listaApiPuntoVenta = response.data.listaApiPuntoVenta;              
              if (response.data.listaApiPuntoVenta.length == 0){
                this.snackbar = true;
              }
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
      }
    }
  }
};
</script>
