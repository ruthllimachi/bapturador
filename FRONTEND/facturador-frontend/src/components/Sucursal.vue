
<template>
<div align="center">
  <br><br>
<v-flex xs7>        
  <v-data-table
    :headers="headers"
    :items="items"
    sort-by="nombreSucursal"
    class="elevation-1"
  >
    <template v-slot:top>
      <v-toolbar flat color="white">
        <v-toolbar-title>Sucursales</v-toolbar-title>
        <v-divider
          class="mx-4"
          inset
          vertical
        ></v-divider>
        <v-spacer></v-spacer>
        <v-dialog v-model="dialog" max-width="800px">
          <template v-slot:activator="{ on }">
            <v-btn color="primary" dark class="mb-2" v-on="on">Nuevo</v-btn>
          </template>
          <v-card>
            <v-card-title>
              <span class="headline">{{ formTitle }}</span>
            </v-card-title>

            <v-card-text>
              <v-container grid-list-md>
                <v-layout wrap>
                  <v-flex xs12 sm6 md4>
                    <v-text-field v-model="editedItem.codigoSucursal" label="Codigo Sucursal"></v-text-field>
                  </v-flex>                  
                  <v-flex xs12 sm6 md4>
                    <v-text-field v-model="editedItem.nombreSucursal" label="Nombre Sucursal"></v-text-field>
                  </v-flex>                  
                  <v-flex xs12 sm6 md4>
                    <v-text-field v-model="editedItem.direccion" label="Direccion"></v-text-field>
                  </v-flex>                 
                </v-layout>
              </v-container>
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
  import ApiSucursalService from '../services/ApiSucursalService'
  import AdministradorService from '../services/AdministradorService';
  export default {
    data: () => ({
      dialog: false,
      headers: [
        { text: 'Codigo', value: 'codigoSucursal', width:40 },        
        {
          text: 'Nombre Sucural',
          align: 'left',
          sortable: false,
          value: 'nombreSucursal',
        },                        
        { text: 'Direccion', value: 'direccion' },        
        { text: 'Actions', value: 'action', sortable: false },
      ],
      items: [],
      editedIndex: -1,
      editedItem: {       
        idSucursal:0, 
        codigoSucursal: 0,
        idEmpresa:0,
        nombreSucursal: '',
        direccion: '',  
      },
      defaultItem: {
        idSucursal:0, 
        codigoSucursal: 0,
        idEmpresa:0,
        nombreSucursal: '',
        direccion: '',        
      },      
    }),

    computed: {
      formTitle () {
        return 'Modificar'
      },
    },

    watch: {
      dialog (val) {
        val || this.close()
      },
    },

    created () {
      this.initialize();      
    },

    methods: {
      initialize () {
        let login = sessionStorage.getItem('usuario');
        AdministradorService.getParametros(login)
        .then(response => {                    
            ApiSucursalService.getApiSucursalPorIdEmpresa(response.data.idEmpresa)
            .then(response => {
                this.items = response.data;                             
            })       
            .catch(e => { console.error(e)});                      
        })
        .catch(e => { console.error(e)});                             
      },

      editItem (item) {      
        this.editedIndex = this.items.indexOf(item)
        this.editedItem = Object.assign({}, item)         
        this.dialog = true
      },

      close () {
        this.dialog = false
        setTimeout(() => {
          this.editedItem = Object.assign({}, this.defaultItem)
          this.editedIndex = -1
        }, 300)
      },      
      save () {                 
        let idEmpresa = 1;
        this.editedItem.idEmpresa = idEmpresa;
        ApiSucursalService.postApiSucursal(this.editedItem)
        .then(() => this.initialize ())
        .catch(e => {                
            console.error(e)
        })         
        this.close()        
      },
    },
  }
</script>




