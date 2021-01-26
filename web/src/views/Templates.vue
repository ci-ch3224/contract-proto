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
      </v-col>
    </v-row>
    <pagination ref="pagination" :pageable="pageable" :executeFunction="search">
    </pagination>
  </v-container>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import 'tui-grid/dist/tui-grid.css'
import { Grid } from '@toast-ui/vue-grid'
import WriteTemplate from '@/components/WriteTemplate.vue'
import { contractTemplateService } from '@/service/ContractTemplateService'
import { ContractTemplate } from '@/model/ContractTemplate'
import { Pageable } from '@/model/Pageable'
import pagination from '@/components/layout/Paginaion.vue'

@Component({
  components: {
    Grid, WriteTemplate, pagination
  }
})
export default class Templates extends Vue {
  dialog = false
  list: ContractTemplate[] = []
  pageable: Pageable = new Pageable()

  gridProps = {
    columns: [
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
        name: 'modifiedDate'
      }
    ],
    options: {
    }
  }

  search (): void {
    contractTemplateService.getPage(this.pageable).then(({ data: responseData }) => {
      const grid = (this.$refs.tuiGrid as Grid)
      grid.invoke('resetData', responseData.content)
      this.pageable.setDataPage(responseData)
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
