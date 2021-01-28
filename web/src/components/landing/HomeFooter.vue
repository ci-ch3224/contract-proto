<template>
  <v-footer padless>
    <v-col class="text-center" cols="12">
      <br/>
      <v-divider></v-divider>
    </v-col>
    <v-col
      class="text-center"
      cols="12"
    >
      &copy; {{ new Date().getFullYear() }} — <strong>COMMERCE i</strong>
    </v-col>
  </v-footer>
</template>
<script>
import { Component, Vue } from 'vue-property-decorator'

@Component
export default class HomeFooter extends Vue {
}
</script>
