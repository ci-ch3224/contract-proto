<template>
  <layout-normal></layout-normal>
</template>

<script lang="ts">
import { Vue, Component } from 'vue-property-decorator'
import LayoutNormal from '@/components/layout/Normal.vue'

@Component({
  components: {
    LayoutNormal
  }
})
export default class App extends Vue {

}
</script>

<style>
/* v-navigation-drawer 항상위 */
.v-navigation-drawer {
  z-index: 999999 !important;
}
</style>
