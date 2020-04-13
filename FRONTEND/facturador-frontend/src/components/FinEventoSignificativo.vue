<template>
  <div align="center">
    <v-snackbar v-model="snackbar" top>
      ¡¡¡ Evento iniciado !!!
      {{codigoRecepcion}}
      <v-btn color="pink" text @click="snackbar = false">Close</v-btn>
    </v-snackbar>
    <v-flex xs6>
      <v-card flat>
        <v-card-title class="text-center justify-center py-6">
          <h1
            class="display-1 basil--text"
          >Fin Evento Significativo</h1>
        </v-card-title>
        <v-card-text>
          <v-form ref="form" v-model="valid" lazy-validation>              
              <v-col cols="8">
                <v-text-field
                  v-model="codigoRecepcion"
                  label="Codigo de Recepcion:"
                  :rules="rules"
                  required
                ></v-text-field>
              </v-col>  
              <v-col cols="8">
                <v-menu
                  v-model="menu"
                  :close-on-content-click="false"
                  :nudge-right="40"
                  transition="scale-transition"
                  offset-y
                  min-width="290px"
                >
                  <template v-slot:activator="{ on }">
                    <v-text-field
                      v-model="fechaEvento"
                      label="Fecha Inicio Evento"
                      prepend-icon="event"
                      readonly
                      v-on="on"
                      required
                      :rules="rules"
                    ></v-text-field>
                  </template>
                  <v-date-picker v-model="fechaEvento" @input="menu = false"></v-date-picker>
              </v-menu>            
              </v-col>  
              <v-col cols="8">
                <v-menu
                  ref="menu"
                  v-model="menu2"
                  :close-on-content-click="false"
                  :nudge-right="40"
                  :return-value.sync="horaEvento"
                  transition="scale-transition"
                  offset-y
                  max-width="290px"
                  min-width="290px"
                >
                  <template v-slot:activator="{ on }">
                    <v-text-field
                      v-model="horaEvento"
                      label="Hora Inicio Evento"
                      prepend-icon="access_time"
                      readonly
                      v-on="on"
                      required
                      :rules="rules"
                    ></v-text-field>
                  </template>
                  <v-time-picker
                    v-if="menu2"
                    v-model="horaEvento"
                    full-width
                    @click:minute="$refs.menu.save(horaEvento)"
                  ></v-time-picker>
                </v-menu>
              </v-col>
             </v-form>
          </v-card-text>
          <v-card-actions class="text-xs-center">
            <v-spacer></v-spacer>
            <v-btn :disabled="!valid" color="success" class="mr-4" @click="evento">Fin Evento Significativo</v-btn>
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
  </div>
</template>
<script>
import ApiEventoSignificativoService from "../services/ApiEventoSignificativoService";
import ParametrosService from "../services/ParametrosService";
import SolicitudCliente from "../models/SolicitudCliente";

export default {
  data() {
    return {
      valid: true,
      rules: [v => !!v || "Dato requerido"],
      listaParMensajeServicio:[],
      listaParEventoSignificativo: [],
      parEventoSignificativo: null,
      dialog: false,            
      fechaEnvio: new Date(),
      snackbar: false,
      progress: false,           
      error: "",
      fechaEvento: new Date().toISOString().substr(0, 10),      
      menu: false,
      horaEvento: null,
      menu2: false,
      codigoRecepcion: null
    };
  },     
  created() {
    ParametrosService.getParEventoSignificativo()
    .then(response => {
      this.listaParEventoSignificativo = response.data;
    })
    .catch(e => {
      console.error(e);
    });
  },
  methods: {
    evento() {
        if (this.$refs.form.validate()) {
            let anio = this.fechaEvento.substr(0,4);
            let mes = this.fechaEvento.substr(5,2);
            let dia = this.fechaEvento.substr(8,2);
            let hora = this.horaEvento.substr(0,2);
            let minuto = this.horaEvento.substr(3,2);
            let fechaEvento = new  Date(anio, mes, dia, hora, minuto).toISOString();
            let login = sessionStorage.getItem("usuario");
            let solicitudCliente = SolicitudCliente;

            solicitudCliente.codigoRecepcion = this.codigoRecepcion;
            solicitudCliente.login = login;
            solicitudCliente.fechaEnvio = fechaEvento;

            this.progress = true;       
            ApiEventoSignificativoService.finEventoSignificativo(solicitudCliente)
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

