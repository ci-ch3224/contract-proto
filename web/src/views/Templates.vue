<template>
  <v-container>
    <v-row>
      <v-col align="end">
        <v-dialog
          v-model="dialog"
          fullscreen
          hide-overlay
          transition="dialog-bottom-transition"
        >
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              color="primary"
              dark
              v-bind="attrs"
              v-on="on"
            >
              등록
            </v-btn>
          </template>
          <write-template v-on:close-dialog="closeDialog" />
        </v-dialog>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <grid ref="tuiGrid" :data="gridProps.data" :columns="gridProps.columns" />
      </v-col>
    </v-row>
  </v-container>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import 'tui-grid/dist/tui-grid.css'
import { Grid } from '@toast-ui/vue-grid'
import WriteTemplate from '@/components/WriteTemplate.vue'

@Component({
  components: {
    Grid, WriteTemplate
  }
})
export default class Templates extends Vue {
  dialog = false
  gridProps = {
    data: [ // for rowData prop
      {
        title: 'Beautiful Lies',
        createdBy: 'Birdy',
        lastModifiedAt: '2020-01-01'
      },
      {
        title: 'X',
        createdBy: 'Ed Sheeran',
        lastModifiedAt: '2020-01-01'
      },
      {
        title: 'X',
        createdBy: 'Ed Sheeran',
        lastModifiedAt: '2020-01-01'
      }
    ],
    columns: [ // for columnData prop
      {
        header: '계약서명',
        name: 'title'
      },
      {
        header: '등록자',
        name: 'createdBy'
      },
      {
        header: '최근수정일',
        name: 'lastModifiedAt'
      }
    ]
  }

  closeDialog () {
    this.dialog = false
  }
}
</script>

<style>

</style>
