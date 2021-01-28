<template>
  <div>
    <v-toolbar class="fixed-bar-title">
      {{selectedTemplate.title}}
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
import { ContractTemplate } from '@/model/ContractTemplate'
import pdf from 'vue-pdf'

@Component({
  components: {
    pdf
  }
})
export default class ViewTemplate extends Vue {
  @Prop() selectedTemplate!: ContractTemplate
  @Prop() closeFunc!: Function
  pdfSrc = null as any
  numPages = 0

  mounted () {
    this.setPdf()
  }

  @Watch('selectedTemplate')
  selectedTemplateIdChanged () {
    this.setPdf()
  }

  setPdf () {
    (this.$refs.pdfSheet as Vue).$el.scrollTop = 0
    this.pdfSrc = pdf.createLoadingTask('http://localhost:8080/report/' + this.selectedTemplate.templateName + '?output=pdf&templateId=' + this.selectedTemplate.id)
    this.pdfSrc.promise.then((pdf: any) => {
      this.numPages = pdf.numPages
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
