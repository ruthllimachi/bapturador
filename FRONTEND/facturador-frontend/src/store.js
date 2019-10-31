import Vue from 'vue'
import Vuex from 'vuex'
import router from './router';
import AdministradorService from './services/AdministradorService';

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    accessToken: null,
    loggingIn: false,
    loginError: null,
    layout: 'simple-layout',
    appStart: true    
  },
  mutations: {
    loginStart: state => state.loggingIn = true,
    entryApp: (state, valor) => {
      state.appStart = valor
    },
    loginStop: (state, errorMessage) => {
      state.loggingIn = false;
      state.loginError = errorMessage;
    },
    updateAccessToken: (state, accessToken) => {
      state.accessToken = accessToken;
    },
    logout: (state) => {
      state.accessToken = null;
      state.loggingIn = false;
    },
    SET_LAYOUT (state, payload) {
      state.layout = payload
    }
  },
  getters: {
    layout (state) {
      return state.layout
    }
  },
  actions: {
    doLogin({ commit }, loginData) {          
      commit('loginStart');
      AdministradorService.loginUsuario(loginData)
      .then(response => {
        sessionStorage.setItem('accessToken', response.data.token);
        //let usuario  = JSON.stringify(response.data);
        //console.log("usuario", usuario.login)
        sessionStorage.setItem('usuario', response.data.login);      
        commit('updateAccessToken', response.data.token);
        commit('SET_LAYOUT', 'app-layout');      
        router.push('/main');        
      })
      .catch(error => {                
        commit('entryApp', false);
        commit('updateAccessToken', null);                      
        commit('loginStop', error.response.data.error);
      })
    },
    fetchAccessToken({ commit }) {
      //console.log('ingresoooooo fetc');
      commit('updateAccessToken', sessionStorage.getItem('accessToken'));
    },
    logout({ commit }) {
      //console.log('ingresoooooo logout');
      sessionStorage.removeItem('accessToken');
      sessionStorage.removeItem('user');
      commit('logout');
      commit('SET_LAYOUT', 'simple-layout');
      commit('entryApp', true);
      router.push('/login');
    },
    
  }
})
