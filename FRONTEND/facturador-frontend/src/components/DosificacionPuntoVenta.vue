<template>
<v-flex xs10>        
  <v-data-table
    :headers="headers"
    :items="items"    
    class="elevation-1"
  >
    <template v-slot:top>
      <v-toolbar flat color="white">
        <v-toolbar-title>Dosificaci&oacute;n Punto Venta</v-toolbar-title>
        <v-divider
          class="mx-4"
          inset
          vertical
        ></v-divider>
        <v-spacer></v-spacer>
        <v-dialog v-model="dialog" max-width="800px">
          <v-card flat>
              <v-card-title>
                <span class="headline">{{ formTitle }}</span>
              </v-card-title>

              <v-card-text>
               <v-col cols="2" >
                  <v-text-field v-model="editedItem.apiPuntoVenta.apiSucursal.codigoSucursal" label="Codigo Sucursal" disabled></v-text-field>
                </v-col>           
                <v-col cols="8" >
                  <v-text-field v-model="editedItem.apiPuntoVenta.apiSucursal.nombreSucursal" label="Nombre Sucursal" disabled></v-text-field>
                </v-col>             
                
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
  </v-data-table>
 </v-flex>         
</template>
<script>
  import ApiDosificacionPuntoVenta from '../models/ApiDosificacionPuntoVenta' 
  import ApiDosificacionService from '../services/ApiDosificacionService' 
  import AdministradorService from '../services/AdministradorService';
  export default {
    data: () => ({
      dialog: false,
      headers: [
        { text: 'Codigo', value: 'apiPuntoVenta.apiSucursal.codigoSucursal', width:40 },        
        {
          text: 'Nombre Sucural',
          align: 'left',
          sortable: false,
          value: 'apiPuntoVenta.apiSucursal.nombreSucursal',
        },                        
        { text: 'Codigo', value: 'apiPuntoVenta.codigoPuntoVenta', width:40 },        
        {
          text: 'Nombre Punto Venta',
          align: 'left',
          sortable: false,
          value: 'apiPuntoVenta.nombrePuntoVenta',
        },                        
        { text: 'Cuis', value: 'apiDosificacion.cuis' },        
        { text: 'Fecha Vigencia', value: 'apiDosificacion.fechaVigencia'},        
        { text: 'Estado', value: 'apiDosificacion.estadoDosificacion.descripcion' },        
        { text: 'Tipo Modalidad', value: 'apiDosificacion.parTipoModalidad.descripcion' },        
        { text: 'Certificado', value: 'apiDosificacion.archivoCertificado' },        
        { text: 'Complemento', value: 'apiDosificacion.archivoClavePrivada' },        
        { text: 'Actions', value: 'action', sortable: false },
      ],
      items: [],
      editedIndex: -1,
      editedItem: ApiDosificacionPuntoVenta,
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
            ApiDosificacionService.listaDosificacionPuntoVentaPorIdEmpresa(response.data.idEmpresa)
            .then(response => {                         
                this.items = response.data;                                                              
                console.log("itms", response);
            })       
            .catch(e => { console.error(e)});   
        })
        .catch(e => { console.error(e)});                             
      },

      editItem (item) {   
        console.log(item);
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
        // ApiSucursalService.postApiSucursal(this.editedItem)
        // .then(() => this.initialize ())
        // .catch(e => {                
        //     console.error(e)
        // })         
        this.close()        
      },
    },
  }
</script>




