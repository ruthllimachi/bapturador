<template>
<div align="center">
  <br><br>
    <v-flex xs11>        
        <v-data-table
            :headers="headers"
            :items="items"
            sort-by="nombrePuntoVenta"
            class="elevation-1"
        >
            <template v-slot:top>
                <v-toolbar flat color="white">
                    <v-toolbar-title>Punto de Venta</v-toolbar-title>
                    <v-divider
                        class="mx-4"
                        inset
                        vertical
                    ></v-divider>
                    <v-spacer></v-spacer>
                </v-toolbar>    
            </template>           
        </v-data-table>
    </v-flex>         
 </div>
</template>
<script>
  import AdministradorService from '../services/AdministradorService';
  import ApiPuntoVentaService from '../services/ApiPuntoVentaService';
  import ApiSucursalService from '../services/ApiSucursalService';
  import ApiPuntoVenta from '../models/ApiPuntoVenta';
  export default {
    data: () => ({      
      dialog: false,
      headers: [
        { text: 'Codigo', value: 'apiSucursal.codigoSucursal', width:40 },       
        { text: 'Nombre Sucursal', value: 'apiSucursal.nombreSucursal', width:250 },           
        { text: 'Codigo', value: 'codigoPuntoVenta', width:80 },        
        {
          text: 'Nombre Punto Venta',
          align: 'left',
          sortable: false,
          value: 'nombrePuntoVenta',
          width:250
        },        
        { text: 'Tipo Punto Venta', value: 'parTipoPuntoVenta.descripcion', width:200 },                        
        { text: 'Descripcion', value: 'descripcion' },                        
      ],
      items: [],      
    }),
    created () {
      this.initialize()
    },

    methods: {
      initialize () {              
        let login = sessionStorage.getItem('usuario');
        AdministradorService.getParametros(login)
        .then(response => {          
            ApiPuntoVentaService.getApiPuntoVentaPorIdEmpresa(response.data.idEmpresa)
            .then(response => {
                this.items = response.data;                             
            })       
            .catch(e => { console.error(e)});                      
        })
        .catch(e => { console.error(e)});  
      },
    },
  }
</script>




