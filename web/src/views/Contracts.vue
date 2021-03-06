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
          <write-contract v-on:close-dialog="closeDialog" />
        </v-dialog>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <grid ref="tuiGrid"
        @click="clickedRow"
        :data="list" :columns="gridProps.columns"
        :options="gridProps.options"
        :theme="gridProps.theme" />
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
      <viewContract :selectedContract="selectedContract" :closeFunc="closeViewDialog"/>
    </v-dialog>
  </v-container>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import 'tui-grid/dist/tui-grid.css'
import { Grid } from '@toast-ui/vue-grid'
import WriteContract from '@/components/WriteContract.vue'
import { contractService } from '@/service/ContractService'
import { Pageable } from '@/model/Pageable'
import pagination from '@/components/layout/Paginaion.vue'
import { Contract } from '@/model/Contract'
import gridThemeConfig from '@/config/tui-grid.theme.ts'
import viewContract from '@/components/ViewContract.vue'

class ViewRenderer {
  el = document.createElement('a')
  constructor (props: any) {
    const { grid, rowKey, columnInfo } = props

    this.el.addEventListener('click', () => {
      columnInfo.renderer.options.clickEventFunc(grid.getRow(rowKey))
    })

    this.el.style.marginLeft = '10px'
    this.render(props)
  }

  getElement () {
    return this.el
  }

  render (props: any) {
    this.el.text = String(props.value)
  }
}

class NameRenderer {
  el = document.createElement('div')
  constructor (props: any) {
    const { grid, rowKey, columnInfo } = props

    this.render(props)
  }

  getElement () {
    return this.el
  }

  render (props: any) {
    this.el.innerHTML = String(props.value.name)
  }
}

@Component({
  components: {
    Grid, WriteContract, pagination, viewContract
  }
})
export default class Contracts extends Vue {
  dialog = false
  list: Contract[] = []
  pageable: Pageable = new Pageable()
  viewDialog = false
  selectedContract: Contract = (null as any)

  gridProps = {
    columns: [
      {
        header: '계약서명',
        name: 'title',
        renderer: {
          type: ViewRenderer,
          options: {
            clickEventFunc: this.openViewDialog
          }
        }
      },
      {
        header: '등록자',
        name: 'createdBy'
      },
      {
        header: '최근수정일',
        name: 'lastModifiedAt'
      },
      {
        header: '갑',
        name: 'gap',
        renderer: {
          type: NameRenderer
        }
      },
      {
        header: '을',
        name: 'eul',
        renderer: {
          type: NameRenderer
        }
      }
    ],
    options: {
      scrollX: false,
      scrollY: false
    },
    theme: gridThemeConfig
  }

  search (): void {
    contractService.getPage(this.pageable).then(({ data: list }) => {
      const grid = (this.$refs.tuiGrid as Grid)
      grid.invoke('resetData', list.content)
      this.pageable.setDataPage(list)
    })
  }

  openViewDialog (row: any): void {
    this.selectedContract = row
    this.viewDialog = true
  }

  closeDialog () {
    this.dialog = false
  }

  created () {
    this.search()
  }

  clickedRow (ev: any): void {
    const record = {
      start: [ev.rowKey, 0],
      end: [ev.rowKey, ev.instance.getColumns().length]
    }
    ev.instance.setSelectionRange(record)
  }

  closeViewDialog () {
    this.viewDialog = false
  }
}
</script>

<style>

</style>
