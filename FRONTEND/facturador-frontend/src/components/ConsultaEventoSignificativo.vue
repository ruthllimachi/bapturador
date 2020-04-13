<template>
  <div align="center">
    <v-flex xs6>
      <v-card flat>
        <v-card-title class="text-center justify-center py-6">
          <h1 class="display-1 basil--text">Consulta Evento Significativo</h1>
        </v-card-title>
        <v-card-text>          
            <v-btn                
                color="success"
                class="mr-4"  
                @click="consultar"        
                >Consultar Evento Significativo
            </v-btn>          
          <br/>
          <!-- <v-simple-table
            :dense="dense"
            :fixed-header="fixedHeader"
            :height="height"
            >
            <template v-slot:default>
              <thead>
                  <tr>
                  <th class="text-left">Codigo Evento</th>
                  <th class="text-left">Descripcion </th>
                  <th class="text-left">Fecha Evento</th>
                  </tr>
              </thead>
              <tbody>
                  <tr v-for="item in listaApiEventoSignificativo" :key="item.codigo">
                  <td>{{ item.codigoEvento }}</td>
                  <td>{{ item.descripcion }}</td>
                  <td>{{ item.utcFechaEventoInicio }}</td>
                  </tr>
              </tbody>
            </template>  
          </v-simple-table> -->
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

          <!-- <v-text-field
              v-model="parMensajeFacturador.descripcion"
              label="Respuesta"
              disabled
          ></v-text-field> -->
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

import ParMensajeFacturador from "../models/ParMensajeFacturador";
import ApiEventoSignificativoService from "../services/ApiEventoSignificativoService";
import SolicitudCliente from "../models/SolicitudCliente";

export default {
  data() {
    return {
      dense: false,
      fixedHeader: true,
      height: 500,
      parMensajeFacturador: ParMensajeFacturador,
      listaParMensajeServicio: [],
      listaApiEventoSignificativo: [],
      dialog: false,
      progress: false,
      error: ""
    };
  },
  methods: {
    consultar() {     
        let login = sessionStorage.getItem("usuario");            
        this.progress = true;        
        ApiEventoSignificativoService.consultaEventoSignificativo(login)
        .then(response => {
            this.progress = false;            
            if (response.data.transaccion) {
                console.log("dato", response.data);
                this.listaApiEventoSignificativo = response.data.listaApiEventoSignificativo;
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
};
</script>
