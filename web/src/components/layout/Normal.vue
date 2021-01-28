<template>
  <v-app>
    <v-app-bar
      id="home-app-bar"
      app
      color="white"
      elevation="1"
      height="80"
    >
      <v-icon class="mx-1" color="primary">mdi-file-document-edit-outline</v-icon>
      <v-toolbar-title id="toolbar-title">전자계약서관리시스템</v-toolbar-title>
      <v-spacer />

      <div>
        <v-tabs
          id="home-app-tab"
          class="hidden-sm-and-down"
          optional
          icons-and-text
        >
          <v-tab
            v-for="([icon, text, to], i) in items"
            :key="i"
            :to="to"
            :ripple="false"
            active-class="text--primary"
            class="font-weight-bold"
            min-width="96"
          >
            {{ text }}
          <v-icon class="mx-1" small>{{ icon }}</v-icon>
          </v-tab>
        </v-tabs>
      </div>

    </v-app-bar>
    <v-main>
      <router-view />
    </v-main>
  </v-app>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'

@Component
export default class LayoutNormal extends Vue {
  drawer = false
  items = [
    ['mdi-home', 'Home', '/'],
    ['mdi-folder-multiple', 'Templete', '/templates'],
    ['mdi-pen-plus', 'Contract', '/contracts'],
    ['mdi-folder-search', 'History', '/history']
  ]
}
</script>
<style lang="sass">
#home-app-bar
  .v-tabs-slider
    max-width: 24px
    margin: 0 auto
  .v-tab
    &::before
      display: none

#toolbar-title
  font-family: 'Nanum Square'
  font-weight: 700
  color: #32325d
</style>
