<template>
  <div align="center">
    <v-flex xs7>
      <v-data-table
        :headers="headers"
        :items="items"
        sort-by="codigoProducto"
        class="elevation-1"
      >
          <template v-slot:top>
              <v-toolbar flat color="white">
                 <v-toolbar-title>Producto Homologado</v-toolbar-title>
                 <v-spacer></v-spacer>
                 <v-dialog v-model="dialog" max-width="1000px">
                   <template v-slot:activator="{ on }">
                     <v-btn color="primary" dark class="mb-2" v-on="on">Adicionar</v-btn>
                   </template>
                   <v-card>
                     <v-card-title>
                       <span class="headline">{{ formTitle }}</span>
                     </v-card-title>
                     <v-card-text>
                        <v-container grid-list-md>
                          <v-layout wrap>

                            <v-flex xs12 sm6 md4>
                              <span v-if="formActualizar">
                                <v-text-field v-model="editedItem.apiItem.codigoProductoSin" label="Codigo SIN" :disabled="true"></v-text-field>
                              </span>
                               <span v-else>
                                  <v-combobox
                                    v-model="apiItem"
                                    :items="listaApiItems"
                                    item-text="codigoProductoSin"
                                    item-value="idItem"
                                    label="Producto"
                                    required
                                  ></v-combobox>
                            </span>
                            </v-flex>
                            <v-flex xs12 sm6 md2>
                            <span v-if="formActualizar">
                                <v-text-field v-model="editedItem.codigoProducto" label="Codigo Interno" :disabled="true"></v-text-field>
                              </span>
                                  <span v-else>
                                    <v-text-field v-model="editedItem.codigoProducto" label="Codigo Interno"></v-text-field>
                                  </span>
                            </v-flex>
                            <v-flex xs12 sm6 md6>
                              <v-text-field v-model="editedItem.descripcion" label="Descripcion"></v-text-field>
                            </v-flex>
                            <v-flex xs12 sm6 md4>
                              <span v-if="formActualizar">
                                <v-text-field v-model="editedItem.parUnidadMedida.descripcion" label="Unidad/Medida" :disabled="true"></v-text-field>
                              </span>
                              <span v-else>
                                  <v-combobox
                                    v-model="parUnidadMedida"
                                    :items="listaParUnidadMedida"
                                    item-text="descripcion"
                                    item-value="codigo"
                                    label="Unidad/Medida"
                                    required
                                  ></v-combobox>
                                </span>


                            </v-flex>

                          </v-layout>

                        </v-container>
                     </v-card-text>
                     <v-card-actions>
                       <v-spacer></v-spacer>
                       <v-btn color="blue darken-1" text @click="close">Cancelar</v-btn>
                       <span v-if="formActualizar">
                             <v-btn color="green darken-1" text @click="saveModificacion">Guardar Modificacion</v-btn>
                       </span>
                       <span v-else>
                             <v-btn color="blue darken-1" text @click="save">Guardar</v-btn>
                       </span>


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
            <v-icon
              small
              class="mr-2"
              @click="deleteItemHomologado(item)"
            >
              delete
            </v-icon>
          </template>

     </v-data-table>
    </v-flex>
  </div>



</template>
<script>
import ApiClienteService from '../services/ApiClienteService'
import AdministradorService from '../services/AdministradorService';
import ApiItemHomologado from '../models/ApiItemHomologado';
import ParametrosService from '../services/ParametrosService';

export default {
  data() {
    return {
      headers:[
        {
          text:'Codigo Interno',
          value : 'codigoProducto',
        },
        {
          text: 'Descripcion',
          value: 'descripcion',
        },
        {
          text:'Codigo SIN',
          value: 'apiItem.codigoProductoSin',
        },
        {
          text:'Descripcion',
          value: 'apiItem.descripcion',
        },
        {
          text: 'Codigo Actividad Economica',
          value: 'apiItem.parActividad.codigo'
        },
        { text: 'Actions', value: 'action', sortable: false },

      ],
      items:[],
        editedIndex: -1,
        editedItem: ApiItemHomologado ,
        listaParUnidadMedida: [],
        listaApiItems:[],
        parUnidadMedida:null,
        apiItem:null,
        dialog:false,
        formActualizar: false, // Ver o no ver el boton Actualizar
      }
    },
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
    created () {  //se ejecuta apenas el components se a creado es como el init
      this.initialize();
    },
    methods: {
          initialize () {
            let login = sessionStorage.getItem('usuario');
            AdministradorService.getParametros(login)
            .then(response => {
              ApiClienteService.getApiProductsByCompany(response.data.idEmpresa)
                  .then(response => {
                      this.listaApiItems = response.data;
                  })
                  .catch(e => { console.error(e)});

               ApiClienteService.getApiHomologado()
                    .then(response => {
                      this.items = response.data;
                    })
                    .catch(e => {
                      console.error(e);
                    });
                    ParametrosService.getParUnidadMedida()
                      .then(response => {
                        this.listaParUnidadMedida = response.data;
                      })
                      .catch(e => {
                        console.error(e);
                      });
            })
            .catch(e => { console.error(e)});

              this.admEmpresa =  null;
              this.parUnidadMedida=null;
          },

          editItem (item) {
            this.editedIndex = this.items.indexOf(item)
            this.editedItem = Object.assign({}, item)
            this.dialog = true
            this.formActualizar = true
          },

          deleteItemHomologado(item ){
            console.log('..elimi--',item);
              alert('eliminando.Homologado.!!')

              ApiClienteService.postBajaHomologado(item)
                .then(() => this.initialize ())
                .catch(e => {
                 console.error(e)
            })
        },

        close () {
          this.dialog = false
          this.formActualizar = false
          setTimeout(() => {
            this.editedItem = Object.assign({}, this.defaultItem)
            this.editedIndex = -1
          }, 300)
         },

              save () {
              //let idEmpresa = 1;
              let apiItemHomologado = ApiItemHomologado;

              apiItemHomologado.codigoProducto= this.editedItem.codigoProducto;
              apiItemHomologado.descripcion= this.editedItem.descripcion;
              apiItemHomologado.apiItem = this.apiItem;
              apiItemHomologado.parUnidadMedida=this.parUnidadMedida;

                console.log("ppprimera forma", apiItemHomologado);
                   ApiClienteService.postApiItemHomologado(apiItemHomologado)
                    .then(() => this.initialize ())
                    .catch(e => {
                     console.error(e)
                  })
                   this.close()
               },

               saveModificacion() {
                   ApiClienteService.postModificarHomologado(this.editedItem)
                     .then(() => this.initialize ())
                     .catch(e => {
                      console.error(e)
                 })
                 this.formActualizar = false
                 this.close()
               },
       }
    }
</script>
