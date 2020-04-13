import Vue from 'vue'
import Router from 'vue-router'
import store from './store';


Vue.use(Router)

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '*',
      redirect: '/login'
    },
    {
      path: '/',
      redirect: '/login'
    },
    {
      path: '/login',
      name: 'login',
      component: () => import(/* webpackChunkName: "login" */ './views/Login.vue')
    },
    {
      path: '/main',
      name: 'main',
      component: () => import(/* webpackChunkName: "main" */ './views/Main.vue'),
      meta: {
        autentificado: true
      }
    },
    {
      path: '/catalogos',
      name: 'catalogos',
      component: () => import(/* webpackChunkName: "catalogos" */ './views/CodigosCatalogos.vue'),
      meta: {
        autentificado: true
      }
    },
    {
      path: '/sucursal',
      name: 'sucursal',
      component: () => import(/* webpackChunkName: "sucursal" */ './views/Sucursal.vue'),
      meta: {
        autentificado: true
      }
    },
    {
      path: '/puntoVenta',
      name: 'puntoVenta',
      component: () => import(/* webpackChunkName: "ptoventa" */ './views/PuntoVenta.vue'),
      meta: {
        autentificado: true
      }
    },
    {
      path: '/general',
      name: 'general',
      component: () => import(/* webpackChunkName: "general" */ './views/General.vue'),
      meta: {
        autentificado: true
      }
    },
    {
      path: '/cuisSucursal',
      name: 'cuisSucursal',
      component: () => import(/* webpackChunkName: "cuisSucursal" */ './views/SolicitudCuisSucursal.vue'),
      meta: {
        autentificado: true
      }
    },
    {
      path: '/cuisPuntoVenta',
      name: 'cuisPuntoVenta',
      component: () => import(/* webpackChunkName: "cuisPuntoVenta" */ './views/SolicitudCuisPuntoVenta.vue'),
      meta: {
        autentificado: true
      }
    },
    {
      path: '/registroPuntoVenta',
      name: 'registroPuntoVenta',
      component: () => import(/* webpackChunkName: "registroPuntoVenta" */ './views/RegistroPuntoVenta.vue'),
      meta: {
        autentificado: true
      }
    },
    {
      path: '/consultaPuntoVenta',
      name: 'consultaPuntoVenta',
      component: () => import(/* webpackChunkName: "consultaPuntoVenta" */ './views/ConsultaPuntoVenta.vue'),
      meta: {
        autentificado: true
      }
    },
    {
      path: '/cierrePuntoVenta',
      name: 'cierrePuntoVenta',
      component: () => import(/* webpackChunkName: "cierrePuntoVenta" */ './views/CierrePuntoVenta.vue'),
      meta: {
        autentificado: true
      }
    },
    {
      path: '/cufd',
      name: 'cufd',
      component: () => import(/* webpackChunkName: "cufd" */ './views/SolicitudCufd.vue'),
      meta: {
        autentificado: true
      }
    },
    {
      path: '/sincronizacionFecha',
      name: 'sincronizacionFecha',
      component: () => import(/* webpackChunkName: "sincronizacionFecha" */ './views/SincronizarFecha.vue'),
      meta: {
        autentificado: true
      }
    },
    {
      path: '/sincronizacionDiaria',
      name: 'sincronizacionDiaria',
      component: () => import(/* webpackChunkName: "sincronizacionDiaria" */ './views/SincronizacionDiaria.vue'),
      meta: {
        autentificado: true
      }
    },
    {
      path: '/sincronizacionSolicitud',
      name: 'sincronizacionSolicitud',
      component: () => import(/* webpackChunkName: "sincronizacionSolicitud" */ './views/SincronizacionSolicitud.vue'),
      meta: {
        autentificado: true
      }
    },
    {
      path: '/nuevoProducto',
      name: 'nuevoProducto',
      component: () => import(/* webpackChunkName: "nuevoProducto" */ './views/SolicitudNuevoProducto.vue'),
      meta: {
        autentificado: true
      }
    },
    {
      path: '/validaNuevoProducto',
      name: 'validaNuevoProducto',
      component: () => import(/* webpackChunkName: "validaNuevoProducto" */ './views/ValidaNuevoProducto.vue'),
      meta: {
        autentificado: true
      }
    },
    {
      path: '/dosificacionSucursal',
      name: 'dosificacionSucursal',
      component: () => import(/* webpackChunkName: "dosificacion" */ './views/DosificacionSucursal.vue'),
      meta: {
        autentificado: true
      }
    },
    {
      path: '/dosificacionPuntoVenta',
      name: 'dosificacionPuntoVenta',
      component: () => import(/* webpackChunkName: "dosificacion" */ './views/DosificacionPuntoVenta.vue'),
      meta: {
        autentificado: true
      }
    },
    {
      path: '/cierreOperacionSucursal',
      name: 'cierreOperacionSucursal',
      component: () => import(/* webpackChunkName: "cierreOperacionSucursal" */ './views/CierreOperacionSucursal.vue'),
      meta: {
        autentificado: true
      }
    },
    {
      path: '/cierreOperacionPuntoVenta',
      name: 'cierreOperacionPuntoVenta',
      component: () => import(/* webpackChunkName: "cierreOperacionPuntoVenta" */ './views/CierreOperacionPuntoVenta.vue'),
      meta: {
        autentificado: true
      }
    },
    {
      path: '/facturaComputarizadaEstandar',
      name: 'facturaComputarizadaEstandar',
      component: () => import(/* webpackChunkName: "facturaEstandr" */ './views/FacturaComputarizadaEstandar.vue'),
      meta: {
        autentificado: true
      }
    },
    {
      path: '/facturaElectronicaEstandar',
      name: 'facturaElectronicaEstandar',
      component: () => import(/* webpackChunkName: "facturaEstandr" */ './views/FacturaElectronicaEstandar.vue'),
      meta: {
        autentificado: true
      }
    },
    {
      path: '/cliente',
      name: 'cliente',
      component: () => import(/* webpackChunkName: "dosificacion" */ './views/Cliente.vue'),
      meta: {
        autentificado: true
      }
    },
    {
      path: '/producto',
      name: 'producto',
      component: () => import(/* webpackChunkName: "apiItemHomologado" */ './views/ApiItemHomologado.vue'),
      meta: {
        autentificado: true
      }

    },
    {
      path: '/inicioEvento',
      name: 'inicioEvento',
      component: () => import(/* webpackChunkName: "inicioEvento" */ './views/InicioEventoSignificativo.vue'),
      meta: {
        autentificado: true
      }

    },
    {
      path: '/finEvento',
      name: 'finEvento',
      component: () => import(/* webpackChunkName: "finEvento" */ './views/FinEventoSignificativo.vue'),
      meta: {
        autentificado: true
      }

    },
    {
      path: '/consultaEvento',
      name: 'consultaEvento',
      component: () => import(/* webpackChunkName: "consultaEvento" */ './views/ConsultaEventoSignificativo.vue'),
      meta: {
        autentificado: true
      }

    },
    {
      path: '/importarCliente',
      name: 'importarCliente',
      component: () => import(/* webpackChunkName: "Clientes" */ './views/ImportarCliente.vue'),
      meta: {
        autentificado: true
      },
    },
    {
      path: '/facturaComputarizadaComercialExportacion',
      name: 'facturaComputarizadaComercialExportacion',
      component: () => import(/* webpackChunkName: "facturaComercialExportacion" */ './views/FacturaComputarizadaComercialExportacion.vue'),
      meta: {
        autentificado: true
      }
    },
    {
      path: '/facturaElectronicaComercialExportacion',
      name: 'facturaElectronicaComercialExportacion',
      component: () => import(/* webpackChunkName: "facturaComercialExportacion" */ './views/FacturaElectronicaComercialExportacion.vue'),
      meta: {
        autentificado: true
      }
    },
    {
      path: '/notaFiscalComputarizadaCreditoDebito',
      name: 'notaFiscalComputarizadaCreditoDebito',
      component: () => import(/* webpackChunkName: "NotaFiscalComputarizadaCreditoDebito" */ './views/NotaFiscalComputarizadaCreditoDebito.vue'),
      meta: {
        autentificado: true
      }
    },
    {
      path: '/notaFiscalElectronicaCreditoDebito',
      name: 'notaFiscalElectronicaCreditoDebito',
      component: () => import(/* webpackChunkName: "notaFiscalElectronicaCreditoDebito" */ './views/NotaFiscalElectronicaCreditoDebito.vue'),
      meta: {
        autentificado: true
      }
    },
  ]
})
router.beforeEach((to, from, next) => {
  let usuario = JSON.parse(sessionStorage.getItem("user"));
  let autorizacion = to.matched.some(record => record.meta.autentificado);
  store.dispatch('fetchAccessToken');
  if (autorizacion && !store.state.accessToken) {
    next('login')
  } else if (!autorizacion && usuario != null) {
    next('main');
  } else {
    next();
  }

});
// router.beforeEach((to, from, next) => {
//   store.dispatch('fetchAccessToken');
//   if (to.fullPath === '/main') {
//     if (!store.state.accessToken) {
//       store.dispatch('logout');
//       next('/login');
//     }
//   }
//   if (to.fullPath === '/login') {
//     if (store.state.accessToken) {
//       next('/main');
//     }
//   }
//   next();
// });
export default router;
