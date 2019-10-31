<template>
  <div align="center">
    <v-snackbar v-model="snackbar" top color="success">
      Datos Obtenidos!!!
      {{ msg }}
      <v-btn color="pink" text @click="snackbar = false">Close</v-btn>
    </v-snackbar>
    <v-flex xs6>
      <v-card flat>
        <v-card-title class="text-center justify-center py-6">
          <h1 class="display-1 basil--text">Solicitud CUFD</h1>
        </v-card-title>
        <v-card-text>
          <v-col cols="8">
            <v-text-field v-model="admSistema.codigoSistema" label="Codigo Sistema" disabled></v-text-field>
          </v-col>
          <v-col cols="8">
            <v-text-field v-model="admEmpresa.nitEmpresa" label="NIT" disabled></v-text-field>
          </v-col>
          <v-col cols="8">
            <v-text-field v-model="apiDosificacion.cuis" label="CUIS" disabled></v-text-field>
          </v-col>
          <v-col cols="8">
            <v-text-field
              v-model="apiDosificacion.parTipoModalidad.descripcion"
              label="Tipo Modalidad"
              disabled
            ></v-text-field>
          </v-col>
        </v-card-text>
        <v-card-actions class="text-xs-center">
          <v-spacer></v-spacer>
          <v-btn color="deep-purple accent-4" text @click="solicitar">Solicitud CUFD</v-btn>
          <v-spacer></v-spacer>
        </v-card-actions>
        <br />
        <br />
        <br />
        <v-progress-linear v-show="progress" indeterminate color="teal"></v-progress-linear>
        <br />
        <br />
      </v-card>
    </v-flex>
    <v-dialog v-model="dialog" width="500">
      <v-card>
        <v-card-title class="headline grey lighten-2" primary-title>Mensajes SOAP:</v-card-title>
        <v-card-text>
          <v-alert type="error" v-show="error!==''" >{{ error }}</v-alert>   
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
import ApiDosificacionService from "../services/ApiDosificacionService";
import ApiConfiguracionService from "../services/ApiConfiguracionService";
import AdministradorService from "../services/AdministradorService";
import ApiDosificacion from "../models/ApiDosificacion";
import AdmSistema from "../models/AdmSistema";
import AdmEmpresa from "../models/AdmEmpresa";

export default {
  data() {
    return {
      admSistema: AdmSistema,
      admEmpresa: AdmEmpresa,
      apiDosificacion: ApiDosificacion,
      dialog: false,
      snackbar: false,
      listaParMensajeServicio: [],
      progress: false,
      msg: "",
      error: ""
    };
  },
  created() {
    let login = sessionStorage.getItem("usuario");
    AdministradorService.getParametros(login)
      .then(response => {
        //console.log(response.data)
        AdministradorService.getAdmEmpresa(response.data.idEmpresa)
          .then(response => {
            this.admEmpresa = response.data[0];
          })
          .catch(e => {
            console.error(e);
          });

        if (response.data.idPuntoVenta == null) {
          ApiDosificacionService.getDosificacionVigenteBySucursal(
            response.data.idSucursal
          )
            .then(response => {
              this.apiDosificacion = response.data;
            })
            .catch(e => {
              console.error(e);
            });
        } else {
          ApiDosificacionService.getDosificacionVigenteByPuntoVenta(
            response.data.idSucursal
          )
            .then(response => {
              this.apiDosificacion = response.data;
            })
            .catch(e => {
              console.error(e);
            });
        }
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
    solicitar() {
      this.progress = true;
      let login = sessionStorage.getItem("usuario");
      ApiConfiguracionService.solicitudCufd(login)
        .then(response => {
          this.progress = false;
          if (response.data.transaccion) {
            this.snackbar = true;
            this.msg = response.data.codigoCufd;
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
