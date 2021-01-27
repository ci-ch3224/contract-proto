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
        <grid ref="tuiGrid"
          @click="clickedRow"
          :data="list" :columns="gridProps.columns"
          :options="gridProps.options"
          :theme="gridProps.theme"/>
      </v-col>
    </v-row>
    <pagination ref="pagination" :pageable="pageable" :executeFunction="search">
    </pagination>
    <v-dialog
      v-model="viewDialog"
      hide-overlay
      transition="dialog-top-transition"
      scrollable
    >
      <viewTemplate :selectedTemplate="selectedTemplate" :closeFunc="closeViewDialog"/>
    </v-dialog>
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
import viewTemplate from '@/components/ViewTemplate.vue'

class ViewRenderer {
  el = document.createElement('a')
  constructor (props: any) {
    const { grid, rowKey, columnInfo } = props

    this.el.addEventListener('click', (ev) => {
      columnInfo.renderer.options.clickEventFunc(grid.getRow(rowKey))
    })

    this.el.style.marginLeft = '10px'
    // this.el.style.fontSize = '16px'

    this.render(props)
  }

  getElement () {
    return this.el
  }

  render (props: any) {
    this.el.text = String(props.value)
  }
}

@Component({
  components: {
    Grid, WriteTemplate, pagination, viewTemplate
  }
})
export default class Templates extends Vue {
  dialog = false
  viewDialog = false
  selectedTemplate: ContractTemplate = (null as any)
  list: ContractTemplate[] = []
  pageable: Pageable = new Pageable()

  gridProps = {
    columns: [
      {
        header: '계약서명',
        name: 'title',
        renderer: {
          type: ViewRenderer,
          options: {
            clickEventFunc: this.selectRow
          }
        }
      },
      {
        header: '등록자',
        name: ''
      },
      {
        header: '최근수정일',
        name: ''
      }
    ],
    options: {
      scrollX: false,
      scrollY: false
    },
    theme: {
      name: 'myTheme',
      value: {
        row: {
          odd: {
            background: '#ffffff'
          }
        },
        cell: {
          focused: {
            border: ''
          },
          focusedInactive: {
            border: ''
          },
          selectedHeader: {
            background: ''
          },
          header: {
            background: '#eee'
          },
          rowHeader: {
            background: '#eee'
          }
        }
      }
    }
  }

  search (): void {
    contractTemplateService.getPage(this.pageable).then(({ data: responseData }) => {
      const grid = (this.$refs.tuiGrid as Grid)
      grid.invoke('resetData', responseData.content)
      this.pageable.setDataPage(responseData)
    })
  }

  clickedRow (ev: any): void {
    const record = {
      start: [ev.rowKey, 0],
      end: [ev.rowKey, ev.instance.getColumns().length]
    }
    ev.instance.setSelectionRange(record)
  }

  selectRow (row: any): void {
    this.selectedTemplate = row
    this.viewDialog = true
  }

  created () {
    this.search()
  }

  closeDialog () {
    this.dialog = false
  }

  closeViewDialog () {
    this.viewDialog = false
  }
}

</script>

<style scoped>
</style>
