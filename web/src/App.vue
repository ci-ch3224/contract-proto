<template>
  <layout-normal></layout-normal>
</template>

<script lang="ts">
import { Vue, Component } from 'vue-property-decorator'
import LayoutNormal from '@/components/layout/normal.vue'

@Component({
  components: {
    LayoutNormal
  }
})
export default class App extends Vue {

}
</script>
