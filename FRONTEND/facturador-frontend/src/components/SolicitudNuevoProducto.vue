<template>
  <div align="center">
    <v-flex xs6>
      <v-card flat>
        <v-card-title class="text-center justify-center py-6">
          <h1
            class="display-1 basil--text"
          >Solicitud Nuevo Producto</h1>
        </v-card-title>
        <v-card-text>
          <v-form ref="form" v-model="valid" lazy-validation>    
            <v-text-field
              v-model="descripcion"
              label="Descripcion:"
              :rules="[v => !!v || 'Dato requerido']"
              required
            ></v-text-field>
          </v-form>
        </v-card-text>
        <v-card-actions class="text-xs-center">
          <v-spacer></v-spacer>
          <v-btn :disabled="!valid" color="success" class="mr-4" @click="solicitar">Solicitud Nuevo Producto</v-btn>
          <v-spacer></v-spacer>
        </v-card-actions>
        <v-container style="height: 200px;">
          <v-progress-linear v-show="progress" indeterminate color="teal"></v-progress-linear>
        </v-container>
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
    <v-dialog v-model="dialogSolicitud" width="300">
      <v-card>
        <v-card-title class="headline grey lighten-2" primary-title>Mensajes SOAP:</v-card-title>
        <v-card-text>          
            <v-col cols="5" >
              <v-text-field v-model="codigoSolicitud" label="Codigo Solicitud" disabled></v-text-field>
            </v-col>    
        </v-card-text>
        <v-divider></v-divider>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="primary" text @click="dialogSolicitud = false; error = ''">Continuar</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>  
  </div>
</template>
<script>
import ApiSincronizacionService from "../services/ApiSincronizacionService";
import SolicitudCliente from "../models/SolicitudCliente";

export default {
  data() {
    return {
      valid: true,            
      listaParMensajeServicio: [],
      dialog: false,
      dialogSolicitud: false,
      descripcion: "",
      codigoSolicitud: "",
      snackbar: false,
      progress: false,
      idEmpresa: null,
      error: ""
    };
  },
  methods: {
    solicitar() {
      if (this.$refs.form.validate()) {
        this.progress = true;
        
        let login = sessionStorage.getItem("usuario");
        
        let solicitudCliente = SolicitudCliente;
        solicitudCliente.descripcion = this.descripcion;
        solicitudCliente.login = login;

        ApiSincronizacionService.solicitudNuevoProducto(solicitudCliente)
        .then(response => {
            this.progress = false;
            if (response.data.transaccion) {
              this.snackbar = true;
              this.codigoSolicitud = response.data.codigoSolicitud;
               this.dialog = false;
              this.dialogSolicitud = true;
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

