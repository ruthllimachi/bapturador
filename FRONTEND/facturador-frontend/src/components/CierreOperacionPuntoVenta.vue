<template>
  <div align="center">
    <v-snackbar v-model="snackbar" top color="success">
       ¡¡¡ Cierre de Operaciones !!!    
      <v-btn color="pink" text @click="snackbar = false">Close</v-btn>
    </v-snackbar>
    <v-flex xs5>
      <v-card flat>
        <v-card-title>
          <span class="headline">CIERRE DE OPERACIONES - PUNTO DE VENTA </span>
        </v-card-title>
        <v-card-text>    
           <v-col cols="8">    
              <v-combobox
                v-model="apiSucursal"
                :items="listaSucursal"
                item-text="nombreSucursal"
                item-value="codigoSucursal"
                v-on:change="cambioSucursal(apiSucursal)"
                label="Sucursales"
                :rules="[v => !!v || 'Dato requerido']"
                required
              ></v-combobox>
           </v-col>
           <v-col cols="8">    
              <v-combobox
                v-model="apiPuntoVenta"
                :items="listaPuntoVenta"
                item-text="nombrePuntoVenta"
                item-value="idPuntoVenta"
                label="Puntos de Venta"
                :rules="[v => !!v || 'Dato requerido']"
                required
              ></v-combobox>
           </v-col>
           <v-col cols="8">    
              <v-text-field v-model="admSistema.codigoSistema" label="Codigo Sistema" disabled></v-text-field>
           </v-col>   
           <v-col cols="8">    
              <v-text-field v-model="admEmpresa.nitEmpresa" label="NIT" disabled></v-text-field>
           </v-col>   
          </v-card-text>    
          <v-card-actions>  
            <v-col cols="8">
              <v-btn
                :disabled="!valid"
                color="success"
                class="mr-4"
                @click="cierre"
                >Cierre de Operaciones
            </v-btn>
            </v-col>
          </v-card-actions>  
          <v-container style="height: 300px;">
            <v-progress-linear indeterminate v-show="progress" color="teal"></v-progress-linear>
          </v-container>  
        </v-card>
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
        </v-card-text>
        <v-divider></v-divider>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="primary" text @click="dialog = false; error = ''">Continuar</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>
<script>

import ApiSucursalService from "../services/ApiSucursalService";
import ApiPuntoVentaService from "../services/ApiPuntoVentaService";
import ApiDosificacionService from "../services/ApiDosificacionService";
import AdministradorService from "../services/AdministradorService";
import SolicitudCliente from "../models/SolicitudCliente";

export default {
  data() {
    return {
      valid: true,
      listaSucursal: [],
      listaSucursalPuntoVenta: [],
      listaPuntoVenta: [],
      apiSucursal: null,
      apiPuntoVenta: null,
      admSistema: {},
      admEmpresa: {},      
      listaParMensajeServicio: [],
      dialog: false,
      snackbar: false,      
      progress: false,
      error: ""
    };
  },
  created() {
    AdministradorService.getAdmSistema()
      .then(response => {
        this.admSistema = response.data[0];
      })
      .catch(e => {
        console.error(e);
      });

    let login = sessionStorage.getItem("usuario");
    AdministradorService.getParametros(login)
      .then(response => {
        AdministradorService.getAdmEmpresa(response.data.idEmpresa)
          .then(response => {
            this.admEmpresa = response.data[0];
          })
          .catch(e => {
            console.error(e);
          });

        ApiSucursalService.listaByEmpresaTieneCuisVigente(response.data.idEmpresa)
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
  },
  methods: {
    inicializa() {
      this.listaParMensajeServicio = [];
      this.apiSucursal = null;
      this.apiPuntoVenta = null;
      this.parTipoModalidad = null;
      this.snackbar = false;
      this.valid = true;      
    },
    cambioSucursal(sucursal) {
      ApiPuntoVentaService.getApiPuntoVentaPorIdSucursal(sucursal.idSucursal)
        .then(response => {
          this.listaPuntoVenta = response.data;
        })
        .catch(e => {
          console.error(e);
        });
    },
    save() {
      this.progress = true;

      let login = sessionStorage.getItem("usuario");

      let solicitudCliente = SolicitudCliente;
      solicitudCliente.codigoAmbiente = this.admEmpresa.codigoAmbiente;
      solicitudCliente.codigoModalidad = this.apiPuntoVenta.codigoModalidad
      solicitudCliente.codigoPuntoVenta = this.apiPuntoVenta.codigoPuntoVenta;
      solicitudCliente.codigoSistema = this.admSistema.codigoSistema;
      solicitudCliente.codigoSucursal = this.apiSucursal.codigoSucursal;
      solicitudCliente.nit = this.admEmpresa.nit;
      solicitudCliente.apiSucursal = this.apiSucursal;
      solicitudCliente.apiPuntoVenta = this.apiPuntoVenta;
      solicitudCliente.login = login;
     
      //console.log(solicitudCliente)
      ApiDosificacionService.cierreOperacion(solicitudCliente)
        .then(response => {
          this.progress = false;
          this.progress = false;
          if (response.data.transaccion) {
            this.snackbar = true;
          } else {
            this.listaParMensajeServicio =
              response.data.listaParMensajeServicio;
            this.dialog = true;
          }
        })
        .catch(e => {
          console.error(e);
          this.error = "A OCURRIDO UN ERROR DE RED U OTRO !!!!";
          this.progress = false;
          this.dialog = true;
        });
    }
  }
};
</script>



