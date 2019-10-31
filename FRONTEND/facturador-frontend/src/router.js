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
      path: '/ptoventa',
      name: 'ptoventa',
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
      path: '/cuis',
      name: 'cuis',
      component: () => import(/* webpackChunkName: "cuis" */ './views/SolicitudCuis.vue'),
      meta: {
        autentificado: true
      }
    },
    {
      path: '/rptovta',
      name: 'rptovta',
      component: () => import(/* webpackChunkName: "rptovta" */ './views/RegistroPuntoVenta.vue'),
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
      path: '/fechora',
      name: 'fechora',
      component: () => import(/* webpackChunkName: "fechora" */ './views/SincronizarFecha.vue'),
      meta: {
        autentificado: true
      }
    },
    {
      path: '/sycrdiaria',
      name: 'sycrdiaria',
      component: () => import(/* webpackChunkName: "sycrdiaria" */ './views/SincronizacionDiaria.vue'),
      meta: {
        autentificado: true
      }
    },
    {
      path: '/sycrsolicitud',
      name: 'sycrsolicitud',
      component: () => import(/* webpackChunkName: "sycrsolicitud" */ './views/SincronizacionSolicitud.vue'),
      meta: {
        autentificado: true
      }
    },
    {
      path: '/dosificacion',
      name: 'dosificacion',
      component: () => import(/* webpackChunkName: "dosificacion" */ './views/Dosificacion.vue'),
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
      path: '/apiItemHomologado',
      name: 'apiItemHomologado',
      component: () => import(/* webpackChunkName: "apiItemHomologado" */ './views/ApiItemHomologado.vue'),
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
