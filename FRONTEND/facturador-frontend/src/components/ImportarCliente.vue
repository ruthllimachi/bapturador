<template>

<div align="center">
<v-flex xs7>
  <form>
      <div class="form-group-row">
          <h3>Subir Excel</h3>
      </div>

      <div class="form-group-row">
          <div class="col-sm-10">
              <input type="file" id="file" ref="file" v-on:change="handleFileUpload()" accept=".XLSX, .CSV" class="form-control">
          </div>
      </div>

      <br>
      <v-btn v-on:click="EventSubir()" class="btn btn-primary">Subir</v-btn>
  </form>
</v-flex>
</div>

</template>
<script>
import ApiClienteService from '../services/ApiClienteService'

export default {

  data(){
          return {
              file: ''
          }
      },
      methods: {
          EventSubir(){
            console.log('...en SUBIR ARCHIVO ===',this.file);
              let formData = new FormData();
              formData.append('file', this.file);
              ApiClienteService.uploadExcelFile(this.file).then(function(){
                      console.log('SUCCESS!!');
                  })
                  .catch(function(){
                      console.log('FAILURE!!');
                  });
          },
          handleFileUpload(){
            console.log('...en handle......',this.$refs.file.files[0]);
              this.file = this.$refs.file.files[0];
          }
      }
}
</script>
