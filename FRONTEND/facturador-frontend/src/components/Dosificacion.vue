<template>
  <div align="center">
    <v-card color="basil">
        <v-card-title class="text-center justify-center py-6">
          <h1 class="display-1 basil--text">Dosificaci&oacute;n</h1>
        </v-card-title>              
        <v-tabs
        fixed-tabs
        background-color="indigo"
        dark   
        >
         <v-tab ripple>
            Dosificaci&oacute;n Sucursal
         </v-tab>
         <v-tab ripple>
            Dosificaci&oacute;n Punto Venta
          </v-tab>        
            <v-tab-item>                    
                <v-flex xs9>                    
                  <v-simple-table>
                      <thead>
                      <tr>
                          <th class="text-left">Codigo Sucursal</th>
                          <th class="text-left">Nombre Sucursal</th>
                          <th class="text-left">Cuis</th>
                          <th class="text-left">Tipo Modalidad</th>
                          <th class="text-left">Estado</th>
                      </tr>
                      </thead>
                      <tbody>
                      <tr v-for="item in listaDosificacionSucursal" :key="item.idDosificacionSucursal">
                          <td>{{ item.apiSucursal.codigoSucursal }}</td>                        
                          <td>{{ item.apiSucursal.nombreSucursal }}</td>                        
                          <td>{{ item.apiDosificacion.cuis }}</td>
                          <td>{{ item.apiDosificacion.parTipoModalidad.descripcion }}</td>
                          <td>{{ item.apiDosificacion.estadoDosificacion.descripcion }}</td>
                      </tr>
                      </tbody>
                  </v-simple-table>                    
                </v-flex>               
            </v-tab-item>
            <v-tab-item> 
                <v-flex xs9>                    
                  <v-simple-table>
                      <thead>
                      <tr>
                          <th class="text-left">Codigo Sucursal</th>
                          <th class="text-left">Nombre Sucursal</th>
                          <th class="text-left">Codigo Punto Venta</th>
                          <th class="text-left">Nombre Punto Venta</th>
                          <th class="text-left">Cuis</th>
                          <th class="text-left">Tipo Modalidad</th>
                          <th class="text-left">Estado</th>
                      </tr>
                      </thead>
                      <tbody>
                      <tr v-for="item in listaDosificacionPuntoVenta" :key="item.idDosificacionPuntoVenta">
                          <td>{{ item.apiPuntoVenta.apiSucursal.codigoSucursal }}</td>                        
                          <td>{{ item.apiPuntoVenta.apiSucursal.nombreSucursal }}</td>                        
                          <td>{{ item.apiPuntoVenta.codigoPuntoVenta }}</td>                        
                          <td>{{ item.apiPuntoVenta.nombrePuntoVenta }}</td>                        
                          <td>{{ item.apiDosificacion.cuis }}</td>
                          <td>{{ item.apiDosificacion.parTipoModalidad.descripcion }}</td>
                          <td>{{ item.apiDosificacion.estadoDosificacion.descripcion }}</td>
                      </tr>
                      </tbody>
                  </v-simple-table>                                        
                    
                </v-flex>                               
            </v-tab-item>                
         </v-tabs>    
       </v-card>             
   </div>   
 </template>
<script>
//Sucursales y puntos de venta sin cuis
import ApiDosificacionService from '../services/ApiDosificacionService' 
import AdministradorService from '../services/AdministradorService' 

export default {
   data () {
      return {    
        valid: true,           
        listaDosificacionSucursal:[],     
        listaDosificacionPuntoVenta:[],             
      }
    },
    created () {                
        let login = sessionStorage.getItem('usuario');
        AdministradorService.getParametros(login)
        .then(response => {                            

            ApiDosificacionService.listaDosificacionSucursalPorIdEmpresa(response.data.idEmpresa)
            .then(response => {          
                this.listaDosificacionSucursal = response.data;                             
            })       
            .catch(e => { console.error(e)});   

            ApiDosificacionService.listaDosificacionPuntoVentaPorIdEmpresa(response.data.idEmpresa)
            .then(response => {            
                this.listaDosificacionPuntoVenta = response.data;                             
            })       
            .catch(e => { console.error(e)}); 

        })
        .catch(e => { console.error(e)});  
    },
    methods: {
      
    }     
}
</script>



