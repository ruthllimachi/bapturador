
<template>  
  <v-container grid-list-md>
    <h2>Cat&aacute;logos y C&oacute;digos</h2>
    <v-layout wrap>      
      <v-flex xs3>        
          <v-card max-width="500"  tile>  
            <v-list dense style="height:700px;overflow-y: auto;">    
              <v-subheader>Datos Sincronizados</v-subheader>
              <v-list-item-group v-model="item" color="primary">
                <v-list-item
                  v-for="(item, i) in items" :key="i">           
                   <v-btn icon @click="selected(item)">
                      <v-icon color="grey lighten-1">info</v-icon>
                    </v-btn>                
                  <v-list-item-content @click="selected(item)">                    
                      <v-list-item-title v-html="item.descripcion"></v-list-item-title>                              
                  </v-list-item-content>
                </v-list-item>
              </v-list-item-group>        
            </v-list>  
          </v-card>          
      </v-flex>
      <v-flex xs9>
        <v-card>
          <v-card tile>  
            <v-card-title>                          
              Sincronizaci&oacute;n:  {{ description }}
            </v-card-title>                          
            <v-card-title>              
              <v-spacer></v-spacer> 
               <v-text-field
                v-model="search"
                append-icon="search"
                label="Search"
                single-line
                hide-details
              ></v-text-field>           
            </v-card-title> 
            <v-data-table
              :headers="headers"
              :items="datos"
              :search="search"              
            ></v-data-table>            
          </v-card>  
      </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>
<script>
import ParametrosService from '../services/ParametrosService';
import ApiSincronizacionService from '../services/ApiSincronizacionService';
export default { 
   data: () => ({
    search: '', 
    description: '',           
    item: -1,
    items: [],        
    catalogos: [       
      { text: 'Codigo', value: 'codigo', width:70 },
      {
          text: 'Descripcion',
          align: 'left',
          sortable: true,
          value: 'descripcion',
      }       
    ],
    codigos: [       
      { text: 'Code Producto SIN', value: 'codigo', width:70 },
      {
          text: 'Nombre Producto',
          align: 'left',
          sortable: true,
          value: 'descripcion',
      },
      { text: 'Code Actividad', value: 'parActividad.codigo', width:70 },       
      { text: 'Descripcion',  value: 'parActividad.descripcion' }
    ],
    datos: [],
    headers:[]
   }),
   created: function() {

      ParametrosService.getParSincronizacion()
      .then(response => {
        this.items = response.data;        
        this.item = this.items.length;
      })       
      .catch(e => { console.error(e)});    
    },
     methods: {      
      selected(dato) {        
        this.description = dato.descripcion;
        this.headers = dato.grupo=='PRODUCTO_SERVICIO' ? this.codigos : this.catalogos;        
        ApiSincronizacionService.getDatosSincronizados(dato.grupo)
       .then(response => {
         //console.log(response)
         this.datos = response.data;
       })
       .catch(e => { console.error(e)});    
      }
    }    
}
</script>




