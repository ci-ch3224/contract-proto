<template>
  <v-row>
    <v-col cols="1">
      <v-select
        v-model="pageable.size"
        :items="pageable.pageSizes"
        label="Items per Page"
        @change="changePageSize"
      ></v-select>
    </v-col>
    <v-col cols="11">
      <v-pagination
        v-model="pageable.currentPage"
        :length="pageable.totalPages"
        total-visible="10"
        @input="execute"
      ></v-pagination>
    </v-col>
  </v-row>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'
import { Pageable } from '@/model/Pageable'

@Component
export default class Pagination extends Vue {
  @Prop() pageable!: Pageable
  @Prop() executeFunction!: Function

  execute (): void {
    this.executeFunction()
  }

  changePageSize (): void {
    this.pageable.resetPage()
    this.execute()
  }
}
</script>
