<template>
<div align="center">
<v-flex xs7>
  <v-data-table
    :headers="headers"
    :items="items"
    sort-by="nombreSucursal"
    class="elevation-1"
  >
  <template v-slot:top>

    <v-toolbar flat color="white">
      <v-toolbar-title>Clientes</v-toolbar-title>
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
                  <v-flex>
                    <span v-if="formActualizar">
                        <v-text-field v-model="editedItem.parTipoCliente.descripcion" label="Tipo Cliente" :disabled="true"></v-text-field>
                    </span>
                   <span v-else>
                         <v-radio-group v-model="tipoCliente" :mandatory="false">
                           <v-radio label="Juridico" value="JUR"></v-radio>
                           <v-radio label="Natural" value="NAT"></v-radio>
                         </v-radio-group>
                  </span>
                  </v-flex>
                  <v-spacer></v-spacer>
                  <v-spacer></v-spacer>
                  <v-flex xs12 sm6 md4>
                    <span v-if="formActualizar">
                        <v-text-field v-model="editedItem.codigoCliente" label="Codigo Cliente" :disabled="true"></v-text-field>
                    </span>
                    <span v-else>
                       <v-text-field v-model="editedItem.codigoCliente" label="Codigo Cliente" counter maxlength="100"  required></v-text-field>
                    </span>
                  </v-flex>
                  <v-flex xs12 sm6 md4>
                    <v-text-field v-model="editedItem.nombreRazonSocial" label="Nombre " required></v-text-field>
                  </v-flex>
                      <v-flex xs12 sm6 md4>
                        <span v-if="formActualizar">
                            <v-text-field v-model="editedItem.numeroDocumento" label="Nro Documento" :disabled="true"></v-text-field>
                        </span>
                        <span v-else>
                          <v-text-field v-model="editedItem.numeroDocumento" label="Nro Documento"></v-text-field>
                        </span>
                      </v-flex>
                  <v-flex xs12 sm6 md4>
                    <span v-if="formActualizar">
                      <v-text-field v-model="editedItem.parTipoDocumentoIdentidad.descripcion" label="Tipo Documento" :disabled="true"></v-text-field>
                    </span>
                    <span v-else>
                      <v-combobox
                        v-model="parTipoDocumentoIdentidad"
                        :items="listaDocumentoIdentidad"
                        item-text="descripcion"
                        item-value="codigo"
                        label="Tipo Documento"
                        required
                      ></v-combobox>
                  </span>
                  </v-flex>
                  <v-flex xs12 sm6 md4>
                      <v-text-field
                        v-model="editedItem.correoElectronico"
                        placeholder="Email"
                        :rules='emailRules'
                        >
                      </v-text-field>
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
       @click="deleteCliente(item)"
     >
       delete
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
import ApiClienteService from '../services/ApiClienteService'
import AdministradorService from '../services/AdministradorService';
import ApiCliente from '../models/ApiCliente';
import ParTipoCliente from '../models/ParTipoCliente';
import ParametrosService from '../services/ParametrosService';

export default {

data () {
  return{
headers: [
  {
    text:'Codigo',
    value : 'codigoCliente',
  },
  {
    text:'Razon Social',
    value : 'nombreRazonSocial',
  },
  {
    text:'Nro Documento',
    value : 'numeroDocumento',
  },
  {
    text:'Tipo',
    value : 'parTipoDocumentoIdentidad.descripcion',
  },
  { text: 'Actions', value: 'action', sortable: false },

],
items:[],
  editedIndex: -1,
  editedItem: ApiCliente,
  listaEmpresa: [],
  listaDocumentoIdentidad: [],
  admEmpresa:  {},
  parTipoDocumentoIdentidad: null,
  parTipoCliente:null,
  tipoCliente: 'NAT',
  dialog:false,
  // Ver o no ver el boton Actualizar
  formActualizar: false,
  files: [],
  fileName: '',
  attachment: '',
    //components:{fileInput},
    emailRules: [
        v => /.+@.+\..+/.test(v) || 'E-mail must be valid',
      ],
      clienteAux:{},



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
        ApiClienteService.getApiClientePorIdEmpresa(response.data.idEmpresa)
        .then(response => {
            this.items = response.data;
        })
        .catch(e => { console.error(e)});
        AdministradorService.getEmpresaAll()
          .then(response => {
            this.listaEmpresa = response.data;
          })
          .catch(e => {
            console.error(e);
          });

          ParametrosService.getParTipoDocumentoIdentidad()
            .then(response => {
              this.listaDocumentoIdentidad = response.data;
            })
            .catch(e => {
              console.error(e);
            });
    })
    .catch(e => { console.error(e)});

      this.admEmpresa =  null;
      this.parTipoDocumentoIdentidad= null;
      this.parTipoCliente=null;
  },

  editItem (item) {
    this.editedIndex = this.items.indexOf(item)
    this.editedItem = Object.assign({}, item)
    this.dialog = true
    this.formActualizar = true
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
  let idEmpresa = 1;
  let apiCliente       = ApiCliente;
  let parTipoCliente  = ParTipoCliente;

parTipoCliente.codigo=this.tipoCliente;

  apiCliente = this.editedItem;
  apiCliente.idEmpresa = idEmpresa;

  apiCliente.parTipoDocumentoIdentidad   = this.parTipoDocumentoIdentidad;
  apiCliente.parTipoCliente = parTipoCliente;
  apiCliente.codigoCliente =this.editedItem.codigoCliente;

  //ApiCliente apiCliente2 = new ApiCliente();
  //this.editedItem.idEmpresa=idEmpresa;
//this.editedItem.parTipoDocumentoIdentidad = this.parTipoDocumentoIdentidad;
//  this.editedItem.parTipoCliente.codigo = this.tipoCliente;

       ApiClienteService.postApiCliente(apiCliente)
        .then(() => this.initialize ())
        .catch(e => {
         console.error(e)
      })
      this.editedItem={};
       this.close()
   },

    deleteCliente(item ){
        alert('eliminando..!!',item)

        ApiClienteService.postBajaCliente(item)
          .then(() => this.initialize ())
          .catch(e => {
           console.error(e)
      })
    },

    saveModificacion() {
        ApiClienteService.postModificarCliente(this.editedItem)
          .then(() => this.initialize ())
          .catch(e => {
           console.error(e)
      })
      this.formActualizar = false
      this.close()
    },
},


}
</script>
