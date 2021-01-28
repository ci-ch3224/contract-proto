<template>
  <section>
  <div class="wrapper">
    <Parallax></Parallax>
    <Feature></Feature>
    <Insight></Insight>
    <br/><br/>
    <HomeFooter></HomeFooter>
  </div>
  </section>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'

import Parallax from '@/components/landing/Parallax.vue'
import Insight from '@/components/landing/Insight.vue'
import Feature from '@/components/landing/Feature.vue'
import HomeFooter from '@/components/landing/HomeFooter.vue'

@Component({
  components: {
    Parallax, Insight, Feature, HomeFooter
  }
})
export default class Home extends Vue {
}
</script>
