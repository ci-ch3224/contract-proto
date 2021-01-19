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
        <grid ref="tuiGrid" :data="list" :columns="gridProps.columns" :options="gridProps.options" />
        <v-pagination
          v-model="page"
          :length="6"
        ></v-pagination>
      </v-col>
    </v-row>
  </v-container>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import 'tui-grid/dist/tui-grid.css'
import { Grid } from '@toast-ui/vue-grid'
import WriteTemplate from '@/components/WriteTemplate.vue'
import { contractTemplateService } from '@/service/ContractTemplateService'
import { ContractTemplate } from '@/model/ContractTemplate'

@Component({
  components: {
    Grid, WriteTemplate
  }
})
export default class Templates extends Vue {
  dialog = false
  list: ContractTemplate[] = []
  page = 1

  gridProps = {
    columns: [
      {
        header: '계약서명',
        name: 'title'
      },
      {
        header: '등록자',
        name: 'id'
      },
      {
        header: '최근수정일',
        name: 'subTitle'
      }
    ],
    options: {
      bodyHeight: 'fitToParent'
    }
  }

  search (): void {
    contractTemplateService.getAll().then(({ data: list }) => {
      console.log(list)
      const grid = (this.$refs.tuiGrid as Grid)
      grid.invoke('resetData', list)
    })
  }

  created () {
    this.search()
  }

  closeDialog () {
    this.dialog = false
  }
}
</script>

<style>

</style>
