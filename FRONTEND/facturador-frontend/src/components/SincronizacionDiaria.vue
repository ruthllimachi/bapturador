<template>
  <div align="center">
    <v-snackbar
    v-model="snackbar"
    top
    color="success"
    >
      C&oacute;digos y Cat&aacute;logos Sincronizados!!!
      <v-btn
          color="pink"         
          text
          @click="snackbar = false"
      >
      Close
      </v-btn>
    </v-snackbar>          
    <v-flex xs6>  
       <v-card flat>
         <v-card-title class="text-center justify-center py-6">
            <h1 class="display-1 basil--text">Sincronizaci&oacute;n Diaria de C&oacute;digos y Cat&aacute;logos</h1>
         </v-card-title>      
         <v-card-text>   
            <v-col cols="8" >
                <v-text-field  v-model="admSistema.codigoSistema" label="Codigo Sistema" disabled ></v-text-field>
            </v-col>                                      
            <v-col cols="8" >
               <v-text-field  v-model="admEmpresa.nitEmpresa" label="NIT" disabled></v-text-field>
            </v-col>                             
            <v-col cols="8" >
               <v-text-field  v-model="apiDosificacion.cuis" label="CUIS" disabled></v-text-field>
            </v-col>                             
            <v-col cols="8" >
               <v-text-field  v-model="apiDosificacion.parTipoModalidad.descripcion" label="Tipo Modalidad" disabled></v-text-field>
            </v-col>                            
         </v-card-text>              
         <v-card-actions class="text-xs-center">                                            
              <v-spacer></v-spacer>
             <v-btn color="deep-purple accent-4" text @click="sincronizar">Sincronizar</v-btn>             
              <v-spacer></v-spacer>
         </v-card-actions>
         <br><br><br>
            <v-progress-linear
            v-show="progress"
            indeterminate
            color="teal"
            ></v-progress-linear>
         <br><br>
       </v-card>     
      </v-flex>    
     <v-dialog
      v-model="dialog"
      width="500"
      >
      <v-card>
          <v-card-title class="headline grey lighten-2" primary-title>
              Mensajes SOAP:
          </v-card-title>
          <v-card-text>
             <v-alert type="error" v-show="error!==''" >{{ error }}</v-alert>            
            <div v-for="item in lista" :key="item.grupo">
                {{item.grupo}}
               <v-simple-table>
                  <thead>
                  <tr>
                      <th class="text-left">Codigo</th>                      
                      <th class="text-left">Descripcion</th>                      
                  </tr>
                  </thead>
                  <tbody>
                  <tr v-for="dato in item.listaParMensajeServicio" :key="dato.codigo">
                      <td>{{ dato.codigo }}</td>
                      <td>{{ dato.descripcion }}</td>
                  </tr>
                  </tbody>
              </v-simple-table>
            </div>
          </v-card-text>
          <v-divider></v-divider>
          <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="primary" text @click="dialog = false; error = ''">
                  Continuar
              </v-btn>
          </v-card-actions>
       </v-card>
     </v-dialog>             
    </div>  
</template>
<script>
import ApiDosificacionService from '../services/ApiDosificacionService' 
import ApiSincronizacionService from '../services/ApiSincronizacionService' 
import AdministradorService from '../services/AdministradorService' 
import ApiDosificacion from '../models/ApiDosificacion'
import AdmSistema from '../models/AdmSistema'
import AdmEmpresa from '../models/AdmEmpresa'

export default {
  data () {
    return {    
        admSistema:AdmSistema, 
        admEmpresa:AdmEmpresa,
        apiDosificacion:ApiDosificacion,        
        dialog: false,               
        snackbar:false,  
        lista:[],   
        progress:false,   
        error: ''        
      }
    },
    created () {
        let login = sessionStorage.getItem('usuario');
          AdministradorService.getParametros(login)
          .then(response => {     
            //console.log(response.data)
              AdministradorService.getAdmEmpresa(response.data.idEmpresa)
          .then(response => {            
              this.admEmpresa = response.data[0];                             
          })           
          .catch(e => { console.error(e)});                 

          if (response.data.idPuntoVenta == null){
             ApiDosificacionService.getDosificacionVigenteBySucursal(response.data.idSucursal)
            .then(response => {          
                this.apiDosificacion = response.data;                  
            })       
            .catch(e => { console.error(e)});   
          }else {
            ApiDosificacionService.getDosificacionVigenteByPuntoVenta(response.data.idSucursal)
            .then(response => {          
                this.apiDosificacion = response.data;                                                       
            })       
            .catch(e => { console.error(e)});   
          }
        })
        .catch(e => { console.error(e)});    

         AdministradorService.getAdmSistema()
        .then(response => {
          this.admSistema = response.data[0];                             
        })       
        .catch(e => { console.error(e)});  

    },       
    methods: {
       sincronizar(){  
          //console.log("iingreso a sincronizacion");
           this.progress = true;
            let login = sessionStorage.getItem("usuario");
            ApiSincronizacionService.sincronizacionDiaria(login)
            .then((response) => {   
              //console.log("dato es", response.data);
                this.progress = false;
                this.lista = response.data;
                this.dialog = true;                    
            })
            .catch(e => {   
               console.error(e);
              this.error = "A OCURRIDO UN ERROR DE RED U OTRO !!!!";
              this.progress = false;
              this.dialog = true;
            })
       }    
    }     
}   
</script>
