<template>
  <v-app>
    <div id="nav">
      <v-navigation-drawer v-model="drawer" :clipped="$vuetify.breakpoint.lgAndUp" app>
        <v-list dense style="height:800px;overflow-y: auto;">
          <template>
            <v-list-item link :to="{path: '/main'}">
              <v-list-item-icon>
                <v-icon>mouse</v-icon>
              </v-list-item-icon>
              <v-list-item-content>
                <v-list-item-title>About</v-list-item-title>
              </v-list-item-content>
            </v-list-item>
            <v-list-item link :to="{path: '/general'}">
              <v-list-item-icon>
                <v-icon>settings</v-icon>
              </v-list-item-icon>
              <v-list-item-content>
                <v-list-item-title>Datos Generales</v-list-item-title>
              </v-list-item-content>
            </v-list-item>
            <v-list-group no-action>
              <template v-slot:activator>
                <v-list-item-icon>
                  <v-icon>watch</v-icon>
                </v-list-item-icon>
                <v-list-item-content>
                  <v-list-item-title>Sincronizaci&oacute;n</v-list-item-title>
                </v-list-item-content>
              </template>
              <v-list-item link :to="{path: '/catalogos'}">
                <v-list-item-content>
                  <v-list-item-title>C&oacute;odigos y</v-list-item-title>
                  <v-list-item-subtitle>Cat&aacute;logos</v-list-item-subtitle>
                  <v-list-item-subtitle>Sincronizados</v-list-item-subtitle>
                </v-list-item-content>
                <v-list-item-icon>
                  <v-icon>info</v-icon>
                </v-list-item-icon>
              </v-list-item>
              <v-list-item link :to="{path: '/sincronizacionDiaria'}">
                <v-list-item-content>
                  <v-list-item-title>Sincronizaci&oacute;n</v-list-item-title>
                  <v-list-item-subtitle>Diaria C&oacute;digos</v-list-item-subtitle>
                  <v-list-item-subtitle>y Cat&aacute;logos</v-list-item-subtitle>
                </v-list-item-content>
                <v-list-item-icon>
                  <v-icon>info</v-icon>
                </v-list-item-icon>
              </v-list-item>
              <v-list-item link :to="{path: '/sincronizacionSolicitud'}">
                <v-list-item-content>
                  <v-list-item-title>Sincronizaci&oacute;n por</v-list-item-title>
                  <v-list-item-subtitle>Solicitud C&oacute;digos</v-list-item-subtitle>
                  <v-list-item-subtitle>y Cat&aacute;logos</v-list-item-subtitle>
                </v-list-item-content>
                <v-list-item-icon>
                  <v-icon>info</v-icon>
                </v-list-item-icon>
              </v-list-item>
              <v-list-item link :to="{path: '/sincronizacionFecha'}">
                <v-list-item-content>
                  <v-list-item-title>Sincronizar</v-list-item-title>
                  <v-list-item-subtitle>Fecha y Hora</v-list-item-subtitle>
                </v-list-item-content>
              </v-list-item>
            </v-list-group>

            <v-list-item link :to="{path: '/cufd'}">
              <v-list-item-icon>
                <v-icon>widgets</v-icon>
              </v-list-item-icon>
              <v-list-item-content>
                <v-list-item-title>Solictud CUFD</v-list-item-title>
              </v-list-item-content>
            </v-list-item>
            <v-list-group no-action>
              <template v-slot:activator>
                <v-list-item-icon>
                  <v-icon>domain</v-icon>
                </v-list-item-icon>
                <v-list-item-content>
                  <v-list-item-title>Operaciones</v-list-item-title>
                </v-list-item-content>
              </template>

              <v-list-group no-action sub-group>
                <template v-slot:activator>
                  <v-list-item-content>
                    <v-list-item-title>PUNTO DE VENTA</v-list-item-title>
                  </v-list-item-content>
                </template>
                <v-list-item link :to="{path: '/registroPuntoVenta'}">
                  <v-list-item-content>
                    <v-list-item-title>Registro Punto</v-list-item-title>
                    <v-list-item-subtitle>de Venta</v-list-item-subtitle>
                  </v-list-item-content>
                  <v-list-item-icon>
                    <v-icon>info</v-icon>
                  </v-list-item-icon>
                </v-list-item>

                <v-list-item link :to="{path: '/consultaPuntoVenta'}">
                  <v-list-item-content>
                    <v-list-item-title>Consulta Punto</v-list-item-title>
                    <v-list-item-subtitle>de Venta</v-list-item-subtitle>
                  </v-list-item-content>
                  <v-list-item-icon>
                    <v-icon>info</v-icon>
                  </v-list-item-icon>
                </v-list-item>

                <v-list-item link :to="{path: '/cierrePuntoVenta'}">
                  <v-list-item-content>
                    <v-list-item-title>Cierre Punto</v-list-item-title>
                    <v-list-item-subtitle>de Venta</v-list-item-subtitle>
                  </v-list-item-content>
                  <v-list-item-icon>
                    <v-icon>info</v-icon>
                  </v-list-item-icon>
                </v-list-item>
              </v-list-group>

              <v-list-group no-action sub-group>
                <template v-slot:activator>
                  <v-list-item-content>
                    <v-list-item-title>PRODUCTOS/SERVICIOS</v-list-item-title>
                  </v-list-item-content>
                </template>
                <v-list-item link :to="{path: '/nuevoProducto'}">
                  <v-list-item-content>
                    <v-list-item-title>Solicitud Nuevo</v-list-item-title>
                    <v-list-item-subtitle>Producto</v-list-item-subtitle>
                  </v-list-item-content>
                  <v-list-item-icon>
                    <v-icon>info</v-icon>
                  </v-list-item-icon>
                </v-list-item>

                <v-list-item link :to="{path: '/validaNuevoProducto'}">
                  <v-list-item-content>
                    <v-list-item-title>Validacion Solicitud</v-list-item-title>
                    <v-list-item-subtitle>Nuevo Producto</v-list-item-subtitle>
                  </v-list-item-content>
                  <v-list-item-icon>
                    <v-icon>info</v-icon>
                  </v-list-item-icon>
                </v-list-item>
              </v-list-group>

              <v-list-group no-action sub-group>
                <template v-slot:activator>
                  <v-list-item-content>
                    <v-list-item-title>SOLICITUD CUIS</v-list-item-title>
                  </v-list-item-content>
                </template>
                <v-list-item link :to="{path: '/cuisSucursal'}">
                  <v-list-item-content>
                    <v-list-item-title>Solicitud Cuis</v-list-item-title>
                    <v-list-item-subtitle>Sucursal</v-list-item-subtitle>
                  </v-list-item-content>
                  <v-list-item-icon>
                    <v-icon>info</v-icon>
                  </v-list-item-icon>
                </v-list-item>

                <v-list-item link :to="{path: '/cuisPuntoVenta'}">
                  <v-list-item-content>
                    <v-list-item-title>Solicitud Cuis</v-list-item-title>
                    <v-list-item-subtitle>Punto de Venta</v-list-item-subtitle>
                  </v-list-item-content>
                  <v-list-item-icon>
                    <v-icon>info</v-icon>
                  </v-list-item-icon>
                </v-list-item>
              </v-list-group>

              <v-list-group no-action sub-group>
                <template v-slot:activator>
                  <v-list-item-content>
                    <v-list-item-title>EVENTO SIGNIFICATIVO</v-list-item-title>
                  </v-list-item-content>
                </template>
                <v-list-item link :to="{path: '/inicioEvento'}">
                  <v-list-item-content>
                    <v-list-item-title>Registro Inicio</v-list-item-title>
                    <v-list-item-subtitle>Evento</v-list-item-subtitle>
                  </v-list-item-content>
                  <v-list-item-icon>
                    <v-icon>info</v-icon>
                  </v-list-item-icon>
                </v-list-item>

                <v-list-item link :to="{path: '/finEvento'}">
                  <v-list-item-content>
                    <v-list-item-title>Registro Fin</v-list-item-title>
                    <v-list-item-subtitle>Evento</v-list-item-subtitle>
                  </v-list-item-content>
                  <v-list-item-icon>
                    <v-icon>info</v-icon>
                  </v-list-item-icon>
                </v-list-item>

                <v-list-item link :to="{path: '/consultaEvento'}">
                  <v-list-item-content>
                    <v-list-item-title>Consulta Evento</v-list-item-title>
                  </v-list-item-content>
                  <v-list-item-icon>
                    <v-icon>info</v-icon>
                  </v-list-item-icon>
                </v-list-item>
              </v-list-group>

              <v-list-group no-action sub-group>
                <template v-slot:activator>
                  <v-list-item-content>
                    <v-list-item-title>CIERRE</v-list-item-title>
                    <v-list-item-subtitle>DE OPERACIONES</v-list-item-subtitle>
                  </v-list-item-content>
                </template>
                <v-list-item link :to="{path: '/cierreOperacionSucursal'}">
                  <v-list-item-content>
                    <v-list-item-title>Cierre Operaciones</v-list-item-title>
                    <v-list-item-subtitle>Sucursal</v-list-item-subtitle>
                  </v-list-item-content>
                  <v-list-item-icon>
                    <v-icon>info</v-icon>
                  </v-list-item-icon>
                </v-list-item>

                <v-list-item link :to="{path: '/cierreOperacionPuntoVenta'}">
                  <v-list-item-content>
                    <v-list-item-title>Cierre Operaciones</v-list-item-title>
                    <v-list-item-subtitle>Punto de Venta</v-list-item-subtitle>
                  </v-list-item-content>
                  <v-list-item-icon>
                    <v-icon>info</v-icon>
                  </v-list-item-icon>
                </v-list-item>
              </v-list-group>
            </v-list-group>
            <v-list-group no-action>
              <template v-slot:activator>
                <v-list-item-icon>
                  <v-icon>dialpad</v-icon>
                </v-list-item-icon>
                <v-list-item-content>
                  <v-list-item-title>Componentes</v-list-item-title>
                </v-list-item-content>
              </template>
              <v-list-item link :to="{path: '/sucursal'}">
                <v-list-item-content>
                  <v-list-item-title>Sucursales</v-list-item-title>
                </v-list-item-content>
                <v-list-item-icon>
                  <v-icon>info</v-icon>
                </v-list-item-icon>
              </v-list-item>
              <v-list-item link :to="{path: '/puntoVenta'}">
                <v-list-item-content>
                  <v-list-item-title>Puntos de Venta</v-list-item-title>
                </v-list-item-content>
                <v-list-item-icon>
                  <v-icon>info</v-icon>
                </v-list-item-icon>
              </v-list-item>
              <v-list-group no-action sub-group>
                <template v-slot:activator>
                  <v-list-item-content>
                    <v-list-item-title>ADMINISTRACION</v-list-item-title>
                    <v-list-item-subtitle>DE CLIENTES</v-list-item-subtitle>
                  </v-list-item-content>
                </template>
                <v-list-item link :to="{path: '/cliente'}">
                  <v-list-item-content>
                    <v-list-item-title>Clientes</v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
                <v-list-item link :to="{path: '/importarCliente'}">
                  <v-list-item-content>
                    <v-list-item-title>Importar Clientes</v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
              </v-list-group>

              <v-list-group no-action sub-group>
                <template v-slot:activator>
                  <v-list-item-content>
                    <v-list-item-title>ADMINISTRACION</v-list-item-title>
                    <v-list-item-subtitle>DE PRODUCTOS</v-list-item-subtitle>
                    <v-list-item-subtitle>Y SERVICIOS</v-list-item-subtitle>
                  </v-list-item-content>
                </template>
                <v-list-item link :to="{path: '/producto'}">
                  <v-list-item-content>
                    <v-list-item-title>Productos/Servicios</v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
                <v-list-item link>
                  <v-list-item-content>
                    <v-list-item-title>Importar</v-list-item-title>
                    <v-list-item-subtitle>Productos/Servicios</v-list-item-subtitle>
                  </v-list-item-content>
                </v-list-item>
              </v-list-group>

              <v-list-group no-action sub-group>
                <template v-slot:activator>
                  <v-list-item-content>
                    <v-list-item-title>DOSIFICACIONES</v-list-item-title>
                  </v-list-item-content>
                </template>
                <v-list-item link :to="{path: '/dosificacionSucursal'}">
                  <v-list-item-content>
                    <v-list-item-title>Sucursal</v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
                <v-list-item link :to="{path: '/dosificacionPuntoVenta'}">
                  <v-list-item-content>
                    <v-list-item-title>Punto de Venta</v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
              </v-list-group>
            </v-list-group>
            <v-list-group no-action>
              <template v-slot:activator>
                <v-list-item-icon>
                  <v-icon>message</v-icon>
                </v-list-item-icon>
                <v-list-item-content>
                  <v-list-item-title>Gestor de Documentos</v-list-item-title>
                  <v-list-item-subtitle>Fiscales Electr&oacute;nicos</v-list-item-subtitle>
                </v-list-item-content>
              </template>
              <v-list-group no-action sub-group>
                <template v-slot:activator>
                  <v-list-item-content>
                    <v-list-item-title>FACTURA ESTANDAR</v-list-item-title>
                  </v-list-item-content>
                </template>
                <v-list-item link :to="{path: '/facturaElectronicaEstandar'}">
                  <v-list-item-content>
                    <v-list-item-title>Electronica</v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
                <v-list-item link :to="{path: '/facturaComputarizadaEstandar'}">
                  <v-list-item-content>
                    <v-list-item-title>Computarizada</v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
              </v-list-group>
              <v-list-group no-action sub-group>
                <template v-slot:activator>
                  <v-list-item-content>
                    <v-list-item-title>FACTURA COMERCIAL</v-list-item-title>
                    <v-list-item-subtitle>DE EXPORTACION</v-list-item-subtitle>
                  </v-list-item-content>
                </template>
                <v-list-item link :to="{path: '/facturaElectronicaComercialExportacion'}">
                  <v-list-item-content>
                    <v-list-item-title>Electronica</v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
                <v-list-item link :to="{path: '/facturaComputarizadaComercialExportacion'}">
                  <v-list-item-content>
                    <v-list-item-title>Computarizada</v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
              </v-list-group>
              <v-list-group no-action sub-group>
                <template v-slot:activator>
                  <v-list-item-content>
                    <v-list-item-title>NOTA DE</v-list-item-title>
                    <v-list-item-subtitle>DEBITO-CREDITO</v-list-item-subtitle>
                  </v-list-item-content>
                </template>
                <v-list-item link :to="{path: '/NotaFiscalElectronicaCreditoDebito'}">
                  <v-list-item-content>
                    <v-list-item-title>Electronica</v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
                <v-list-item link :to="{path: '/NotaFiscalComputarizadaCreditoDebito'}">
                  <v-list-item-content>
                    <v-list-item-title>Computarizada</v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
              </v-list-group>
            </v-list-group>
          </template>
        </v-list>
      </v-navigation-drawer>

      <v-app-bar
        :clipped-left="$vuetify.breakpoint.lgAndUp"
        app
        color="blue lighten-3"
        dark
        absolute
      >
        <div id="app">
          <img :src="require('../assets/logobap2.png')" height="30" width="80" />
        </div>
        <v-toolbar-title style="width: 300px" class="ml-0 pl-4">
          <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
          <span class="hidden-sm-and-down">BAPTURADOR</span>
        </v-toolbar-title>
        <v-spacer></v-spacer>
        <v-btn color="blue" dark fab fixed right @click="logout">
          <v-icon>home</v-icon>
        </v-btn>
      </v-app-bar>
    </div>
    <v-content>
      <router-view />
    </v-content>
  </v-app>
</template>
<script>
import { mapActions } from "vuex";
export default {
  name: "App",
  data: () => ({
    drawer: null,
    image: "./assets/logo.png"
  }),
  methods: {
    ...mapActions(["logout"])
  }
};
</script>
