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
        <grid ref="tuiGrid" :data="gridProps.data" :columns="gridProps.columns" :options="gridProps.options" />
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
import WriteContract from '@/components/WriteContract.vue'

@Component({
  components: {
    Grid, WriteContract
  }
})
export default class Contracts extends Vue {
  dialog = false
  page = 1

  gridProps = {
    data: [
      {
        title: 'hello',
        createdBy: '김을동',
        lastModifiedAt: '2020-01-01',
        gap: '가나시스템',
        eul: '다라소프트'

      }
    ],
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
        name: 'lastModifiedAt'
      },
      {
        header: '갑',
        name: 'gap'
      },
      {
        header: '을',
        name: 'eul'
      }
    ],
    options: {
    }
  }

  closeDialog () {
    this.dialog = false
  }
}
</script>

<style>

</style>
