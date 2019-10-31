
<template>
<div align="center">
  <br><br>
    <v-flex xs9>        
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
                     <v-dialog v-model="dialog" max-width="800px">                    
                        <v-card>
                            <v-card-title>
                                <span class="headline">{{ formTitle }}</span>
                            </v-card-title>
                            <v-card-text>
                                <v-layout row wrap >
                                    <v-col cols="8" >
                                      <v-text-field v-model="editedItem.apiSucursal.nombreSucursal" label="Nombre Sucursal" disabled></v-text-field>
                                    </v-col>                                    
                                     <v-col cols="8">
                                        <v-text-field v-model="editedItem.codigoPuntoVenta" label="Codigo Punto Venta" disabled></v-text-field>
                                    </v-col>                  
                                     <v-col cols="8">
                                        <v-text-field v-model="editedItem.nombrePuntoVenta" label="Nombre Punto Venta" disabled ></v-text-field>
                                    </v-col>
                                     <v-col cols="8">
                                        <v-text-field v-model="editedItem.descripcion" label="Descripcion"></v-text-field>
                                    </v-col>
                                    <v-col cols="8">
                                        <v-text-field v-model="editedItem.parTipoPuntoVenta.descripcion" label="Tipo de Punto de Venta" disabled></v-text-field>
                                    </v-col>    
                                 </v-layout>   
                            </v-card-text>    
                            <v-card-actions>
                                <v-spacer></v-spacer>
                                <v-btn color="blue darken-1" text @click="close">Cancelar</v-btn>
                                <v-btn color="blue darken-1" text @click="save">Guardar</v-btn>
                            </v-card-actions>                            
                        </v-card>    
                     </v-dialog>    
                </v-toolbar>    
            </template>   
            <template v-slot:item.action="{ item }">
                <v-icon
                    small
                    class="mr-2"
                    @click="editItem(item)"
                >
                    edit
                </v-icon>              
            </template>
            <template v-slot:no-data>
                <v-btn color="primary" @click="initialize">Reset</v-btn>
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
        { text: 'Codigo Sucursal', value: 'apiSucursal.codigoSucursal', width:40 },       
        { text: 'Nombre Sucursal', value: 'apiSucursal.nombreSucursal' },           
        { text: 'Codigo Punto Venta', value: 'codigoPuntoVenta', width:100 },        
        {
          text: 'Nombre Punto Venta',
          align: 'left',
          sortable: false,
          value: 'nombrePuntoVenta',
        },        
        { text: 'Tipo Punto Venta', value: 'parTipoPuntoVenta.descripcion' },                   
        { text: 'Actions', value: 'action', sortable: false },
      ],
      items: [],      
      editedIndex: -1,
      editedItem: ApiPuntoVenta,      
    }),

    computed: {
      formTitle () {
        return 'Modificar';
      },
    },
    watch: {
      dialog (val) {
        val || this.close()
      },
    },
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
      editItem (item) {      
        this.editedIndex = this.items.indexOf(item);
        this.editedItem = item;
        this.dialog = true;
      },
      close () {
        this.dialog = false
        setTimeout(() => {          
          this.editedItem = ApiPuntoVenta;
          this.editedIndex = -1;
        }, 300)
      },      
      save () {        
         if (this.editedIndex > -1) {              
          ApiSucursalService.putApiSucursal(this.editedItem)
          .then(() => this.initialize ())
          .catch(e => {                
              console.error(e)
          })                  
         }   
        this.close()        
      },
    },
  }
</script>




