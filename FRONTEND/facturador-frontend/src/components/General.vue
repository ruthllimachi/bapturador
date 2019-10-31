<template>
  <v-card color="basil">
    <v-card-title class="text-center justify-center py-6">
      <h1 class="display-1 basil--text">Datos Generales</h1>
    </v-card-title>
    <div align="center">
        <v-tabs
        fixed-tabs
        background-color="indigo"
        dark   
        >
        <v-tab ripple>
            Sistema
        </v-tab>
        <v-tab ripple>
            Empresa
        </v-tab>        
        <v-tab ripple>
          Datos Configuraci&oacute;n
        </v-tab>        
        <v-tab-item>
          <v-flex xs9>                    
            <v-card flat>
              <v-card-text>
               <v-layout row wrap>
                <v-col cols="8" >
                    <v-text-field  v-model="apiSistema.nombreSistema" label="Nombre del Sistema" disabled></v-text-field>
                </v-col>                                    
                    <v-col cols="8">
                    <v-text-field  v-model="apiSistema.version" label="Version" disabled></v-text-field>
                </v-col>                  
                    <v-col cols="8">
                    <v-text-field  v-model="apiSistema.codigoSistema" label="Codigo Sistema" disabled ></v-text-field>
                </v-col>
               </v-layout>                   
              </v-card-text>
           </v-card>
          </v-flex>                   
        </v-tab-item>
        <v-tab-item>
          <v-flex xs9>                  
            <v-card flat>
              <v-card-text>
                <v-layout row wrap>
                    <v-col cols="8" >
                        <v-text-field  v-model="apiEmpresa.razonSocial" label="Razon Social" disabled></v-text-field>
                    </v-col>                                    
                        <v-col cols="8">
                        <v-text-field  v-model="apiEmpresa.nitEmpresa" label="NIT" disabled></v-text-field>
                    </v-col>                  
                    <v-col cols="8">
                        <v-text-field  v-model="apiEmpresa.direccionFiscal" label="Direccion Fiscal" disabled ></v-text-field>
                    </v-col>
                    <v-col cols="8">
                        <v-text-field  v-model="apiEmpresa.ciudad" label="Ciudad" disabled ></v-text-field>
                    </v-col>
                    <v-col cols="8">
                        <v-text-field  v-model="apiEmpresa.contacto" label="Contacto" disabled ></v-text-field>
                    </v-col>
                    <v-col cols="8">
                        <v-text-field  v-model="apiEmpresa.telefonoEmpresa" label="Telefono Empresa" disabled ></v-text-field>
                    </v-col>
                </v-layout>                   
              </v-card-text>
            </v-card>
          </v-flex>                   
        </v-tab-item>          
        <v-tab-item>
          <v-flex xs9>                  
            <v-card flat>
              <v-card-text>
                <v-layout row wrap>
                    <v-col cols="8" >
                        <v-text-field  v-model="apiConfiguracion.cufd" label="CUFD" disabled></v-text-field>
                    </v-col>                                    
                        <v-col cols="8">
                        <v-text-field  v-model="apiConfiguracion.utcFechaVigencia" label="Fecha Vigencia" disabled></v-text-field>
                    </v-col>                  
                    <v-col cols="8">
                        <v-text-field  v-model="apiConfiguracion.utcFechaHora" label="Fecha Servidor" disabled ></v-text-field>
                    </v-col>                    
                </v-layout>                   
              </v-card-text>
            </v-card>
          </v-flex>                               
        </v-tab-item>    
     </v-tabs>
   </div>   
  </v-card>
</template>
<script>
  import AdministradorService from '../services/AdministradorService'  
  import ApiConfiguracionService from '../services/ApiConfiguracionService'    
  export default {
    data () {
      return {            
        apiSistema:{},
        apiEmpresa:{},                
        apiConfiguracion:{}, 
        //consultaParametros:null       
      }      
    },    
    created () {        
        let login = sessionStorage.getItem('usuario');
        AdministradorService.getParametros(login)
        .then(response => {          
            //console.log(response.data)
            AdministradorService.getAdmEmpresa(response.idEmpresa)
            .then(response => {            
                this.apiEmpresa = response.data[0];                             
            })       
            .catch(e => { console.error(e)});          
            if (response.data.idPuntoVenta == null){
                ApiConfiguracionService.getConfiguracionVigenteBySucursal(response.data.idSucursal)
                .then(response => {            
                    console.log("respuesta", response.data);
                    this.apiConfiguracion = response.data;                                                 
                })       
                .catch(e => { console.error(e)});          
            } else {
                ApiConfiguracionService.getConfiguracionVigenteByPuntoVenta(response.data.idPuntoVenta)
                .then(response => {            
                    this.apiConfiguracion = response.data;                             
                })       
                .catch(e => { console.error(e)});          
            }
        })       
        .catch(e => { console.error(e)});         

        AdministradorService.getAdmSistema()
        .then(response => {
          this.apiSistema = response.data[0];                             
        })       
        .catch(e => { console.error(e)});   
    },
    methods: {
       
    }
   
  }
</script>
