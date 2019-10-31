<template>
  <div align="center">
    <v-snackbar v-model="snackbar" top>
      Datos Registrados!!!
      <v-btn color="pink" text @click="snackbar = false">Close</v-btn>
    </v-snackbar>
    <v-flex xs6>
      <v-card flat>
        <v-card-title class="text-center justify-center py-6">
          <h1 class="display-1 basil--text">Registro Punto de Venta</h1>
        </v-card-title>
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

          <v-text-field
            v-model="nombrePuntoVenta"
            label="Nombre Punto Venta"
            :rules="rules"
            required
          ></v-text-field>

          <v-text-field v-model="descripcion" label="Descripcion" :rules="rules" required></v-text-field>

          <v-combobox
            v-model="parTipoPuntoVenta"
            :items="listaTipoPuntoVenta"
            item-text="descripcion"
            item-value="codigo"
            label="Tipo Punto Venta"
            :rules="rules"
            required
          ></v-combobox>

          <v-btn
            :disabled="!valid"
            color="success"
            class="mr-4"
            @click="registrar"
          >Registrar Punto de Venta</v-btn>

          <br />
          <br />
          <br />
          <v-progress-linear v-show="progress" indeterminate color="teal"></v-progress-linear>
          <br />
          <br />
        </v-form>
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
          <v-btn color="primary" text @click="dialog = false;error = ''">Continuar</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>
<script>
import SolicitudCliente from "../models/SolicitudCliente";
import ApiPuntoVentaService from "../services/ApiPuntoVentaService";
import ApiSucursalService from "../services/ApiSucursalService";
import AdministradorService from "../services/AdministradorService";
import ParametrosService from "../services/ParametrosService";

export default {
  data() {
    return {
      valid: true,
      rules: [v => !!v || "Dato requerido"],
      listaSucursal: [],
      listaTipoPuntoVenta: [],
      apiSucursal: null,
      parTipoPuntoVenta: null,
      listaParMensajeServicio: [],
      admSistema: {},
      admEmpresa: {},
      dialog: false,
      nombrePuntoVenta: "",
      descripcion: "",
      snackbar: false,
      progress: false,
      error: ""
    };
  },
  created() {
    //let idEmpresa = 1;
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

    ParametrosService.getParTipoPuntoVenta()
      .then(response => {
        this.listaTipoPuntoVenta = response.data;
      })
      .catch(e => {
        console.error(e);
      });
  },
  methods: {
    registrar() {
      if (this.$refs.form.validate()) {
        let solicitudCliente = SolicitudCliente;
        solicitudCliente.codigoAmbiente = this.admEmpresa.codigoAmbiente;
        solicitudCliente.codigoSistema = this.admSistema.codigoSistema;
        solicitudCliente.codigoSucursal = this.apiSucursal.codigoSucursal;
        solicitudCliente.codigoTipoPuntoVenta = this.parTipoPuntoVenta.codigo;
        solicitudCliente.apiSucursal = this.apiSucursal;
        solicitudCliente.parTipoPuntoVenta = this.parTipoPuntoVenta;
        solicitudCliente.nitEmpresa = this.admEmpresa.nitEmpresa;
        solicitudCliente.nombrePuntoVenta = this.nombrePuntoVenta;
        solicitudCliente.descripcion = this.descripcion;
        solicitudCliente.cuis = this.apiSucursal.cuis;
        solicitudCliente.codigoModalidad = this.apiSucursal.codigoModalidad;
        solicitudCliente.usuario = "admin";
        //console.log(solicitudCliente)
        this.progress = true;
        ApiPuntoVentaService.registrarPuntoVenta(solicitudCliente)
          .then(response => {
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
  }
};
</script>
