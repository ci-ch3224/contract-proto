<template>
  <v-parallax
    id="parallax-first"
    class="white--text"
    src="@/../dist/img/home-hero.jpg"
    height="600"
    max-width="700"
    width="100%"
    >
    <v-row
    align="center"
    class="ma-0 fill-height text-center"
    justify="center">
    <v-col cols="12">
        <h1 id="parallax-title">{{ parallaxTitle }}</h1>
    </v-col>
    </v-row>
  </v-parallax>
</template>
<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'

@Component
export default class Parallax extends Vue {
  parallaxTitle = 'A Better Way for Signature'
}
</script>
<style lang='sass'>
#parallax-first
  .v-parallax__content
    background: linear-gradient(to right, rgba(5, 11, 31, .8), rgba(5, 11, 31, .8))

#parallax-title
  font-family: 'Work Sans'
  letter-spacing: 0.1875rem
  font-size: 2rem
  font-weight: 700
  &::after
    content: ""
    display: block
    height: 40px
    width: 30px
    border-bottom: 3px solid #a9a9a9
    margin: 0 auto
</style>
