<template>

<div class="container" align="center">

        <div class="login-wrapper border border-light">
        <div class="col-sm-5">
            <h2>Ingresar a BAPTURADOR</h2>
            <br>
            <v-alert
              v-if="!appStart"
              color="primary"
              dark
              type="error"
              border="left"
              prominent
              >
              Error Autenticaci&oacute;n
            </v-alert>

            <v-form  @submit.prevent="loginSubmit" >
            <v-text-field prepend-icon="person" name="Username" label="Username" v-model="login"></v-text-field>
            <v-text-field prepend-icon="lock"  name="Password" label="Password" type="password" v-model="password"></v-text-field>
            <v-card-actions>
              <v-btn primary large block color="blue darken-2"  type="submit">Login</v-btn>
            </v-card-actions>
            </v-form>
        </div>
        <div id="app" style ="alignment: center ;filter:alpha(opacity=20);-moz-opacity:.20;opacity:.20">
         <img :src="require('../assets/logo_bap.jpg')" height="450" width="950"  style="background:transparent"/>
        </div>
    </div>

  </div>

</template>
<script>
 import { mapState, mapActions } from 'vuex';

export default {
  name: 'Login',
  data() {
    return {
      login: '',
      password: ''
    };
  },
  computed: {
    ...mapState([
      'appStart'
    ])
  },
  methods: {
      ...mapActions([
        'doLogin'
      ]),
       loginSubmit() {
        this.doLogin({
          login: this.login,
          password: this.password
       });
      }
    }
};
</script>
<style>
  body {
    background: #605b56;
  }

  .login-wrapper {
    background: #fff;
    width: 90%;
    margin: 12% auto;

    max-width: 100%;
    padding: 5% 15px;
    margin: 0 auto;
  }

.form-signin {
  max-width: 330px;
  padding: 10% 15px;
  margin: 0 auto;
}

</style>
