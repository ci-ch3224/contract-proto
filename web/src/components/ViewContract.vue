<template>
  <div>
    <v-toolbar class="fixed-bar-title">
      {{selectedContract.title}}
      <v-spacer></v-spacer>
      <v-btn
        icon
        @click="closeFunc"
      >
        <v-icon>mdi-close</v-icon>
      </v-btn>
    </v-toolbar>
    <v-sheet
      ref="pdfSheet"
      color="grey lighten-4"
      class="d-flex justify-center mb-10 a"
      style="overflow-y: scroll; max-height: 90%;"
    >
      <div style="width: 70%; background-color: white;">
        <pdf v-for="i in numPages" :key="i" :src="pdfSrc" :page="i"></pdf>
      </div>
    </v-sheet>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue, Watch } from 'vue-property-decorator'
import { Contract } from '@/model/Contract'
import { contractTemplateService } from '@/service/ContractTemplateService'
import pdf from 'vue-pdf'

@Component({
  components: {
    pdf
  }
})
export default class ViewContract extends Vue {
  @Prop() selectedContract!: Contract
  @Prop() closeFunc!: Function
  pdfSrc = null as any
  numPages = 0

  mounted () {
    this.setPdf()
  }

  @Watch('selectedContract')
  selectedContractChanged () {
    this.setPdf()
  }

  setPdf () {
    (this.$refs.pdfSheet as Vue).$el.scrollTop = 0
    contractTemplateService.getOne(this.selectedContract.templateId).then(({ data: template }) => {
      this.pdfSrc = pdf.createLoadingTask('http://localhost:8080/report/' + template.templateName + '?output=pdf&contractId=' + this.selectedContract.id)
      this.pdfSrc.promise.then((pdf: any) => {
        this.numPages = pdf.numPages
      })
    })
  }
}
</script>
<style scoped>
.fixed-bar-title {
  position: sticky;
  position: -webkit-sticky; /* for Safari */
  top: 0;
  z-index: 2;
}
</style>
