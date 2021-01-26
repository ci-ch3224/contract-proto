<template>
  <v-card>
    <v-stepper class="pa-2" v-model="e1">
      <v-app-bar fixed dark color="primary">
        <v-btn
          icon
          dark
          @click="$emit('close-dialog')"
        >
          <v-icon>mdi-close</v-icon>
        </v-btn>
        <v-app-bar-title>템플릿 등록</v-app-bar-title>
        <v-spacer></v-spacer>
          <v-btn
            dark
            text
            @click="$emit('close-dialog')"
          >
            Save
          </v-btn>
      </v-app-bar>
        <v-stepper-header class="mt-16">
          <v-stepper-step
            :complete="e1 > 1"
            step="1"
          >
            포멧 선택
          </v-stepper-step>
          <v-divider></v-divider>
          <v-stepper-step
            :complete="e1 > 2"
            step="2"
          >
            내용 입력
          </v-stepper-step>
          <v-divider></v-divider>
          <v-stepper-step step="3">
            미리보기 및 완료
          </v-stepper-step>
        </v-stepper-header>
        <v-stepper-items>
          <!-- 1. 포멧 선택 -->
          <v-stepper-content step="1" class="py-2 px-0">
            <v-carousel :height=carouselHeight v-model="selectedReportIndex" light hide-delimiter-background>
              <v-carousel-item
                v-for="(d, i) in reports"
                :key="i"
              >
                <v-sheet
                  color="grey lighten-5"
                  height="100%"
                  tile
                  class="d-flex justify-center"
                >
                  <div style="width:70%;background-color: white;">
                    <pdf
                      class="mb-2"
                      v-for="i in d.numPages"
                      :key="i"
                      :src="d.src"
                      :page="i"
                    ></pdf>
                  </div>
                </v-sheet>
              </v-carousel-item>
            </v-carousel>
            <v-footer
              elevation="3"
              fixed
              >
            <div>
              <v-btn
                color="primary"
                @click="step1()"
              >
                선택
              </v-btn>
              <v-btn text
                @click="cancel()"
              >
                취소
              </v-btn>
            </div>
            </v-footer>
          </v-stepper-content>

          <!-- 2. 내용 입력 -->
          <v-stepper-content step="2">
            <component class="mb-10" ref="contents" v-bind:is="selectedComponent">
            </component>
            <v-footer
            fixed
            elevation="3"
            >
              <v-btn
                color="primary"
                @click="saveContents()"
              >
                본문저장
              </v-btn>

              <v-btn text
                @click="cancel()"
              >
                취소
              </v-btn>
            </v-footer>
          </v-stepper-content>

          <!-- 3. 미리보기 및 완료 -->
          <v-stepper-content step="3">
            <v-sheet
              color="grey lighten-4"
              height="100%"
              class="d-flex justify-center mb-10"
            >
              <div style="width:70%;background-color: white;">
                <pdf
                  class="mb-2"
                  v-for="i in numPages"
                  :key="i"
                  :src="pdfSrc"
                  :page="i"
                ></pdf>
              </div>
            </v-sheet>
            <v-footer
              fixed
              elevation="3"
              >
              <v-btn
                color="primary"
                @click="e1 = 1"
              >
                완료
              </v-btn>

              <v-btn text @click="cancel()">
                취소
              </v-btn>
              <v-btn text color="lime" :href="pdfHref" target="_blank" download>
                Download
              </v-btn>
            </v-footer>
          </v-stepper-content>
        </v-stepper-items>
      </v-stepper>
  </v-card>
</template>

<script lang="ts">
import 'codemirror/lib/codemirror.css'
import '@toast-ui/editor/dist/toastui-editor.css'
import '@toast-ui/editor/dist/toastui-editor-viewer.css'

import { Component, Vue } from 'vue-property-decorator'
import { Editor, Viewer } from '@toast-ui/vue-editor'
import pdf from 'vue-pdf'
import { contractTemplateService } from '@/service/ContractTemplateService'
import Base1 from '@/components/templates/Base1.vue'
import Base2 from '@/components/templates/Base2.vue'
import Base3 from '@/components/templates/Base3.vue'

interface Report {
  name: string;
  numPages: number;
  src: any;
}

@Component({
  components: {
    Editor, Viewer, Base1, Base2, Base3, pdf
  }
})
export default class WriteTemplate extends Vue {
  e1 = 1
  reports: Report[] = []
  selectedComponent = 'base1'
  selectedReportIndex = 0
  pdfHref = ''
  pdfSrc = pdf.createLoadingTask('http://localhost:8080/report/base3?output=pdf&templateId=3')
  numPages = 3
  carouselHeight = window.innerHeight - 203

  step1 () {
    this.e1 = 2
    this.selectedComponent = this.reports[this.selectedReportIndex].name
  }

  cancel () {
    if (this.e1 > 1) {
      this.e1--
    }
  }

  mounted () {
    // console.log("ready...");
    window.addEventListener('resize', this.handleResize)
  }

  beforeDestroy () {
    // console.log("beforeDestroy...");
    window.removeEventListener('resize', this.handleResize)
  }

  handleResize () {
    this.carouselHeight = window.innerHeight - 203
  }

  created () {
    this.initReports()
  }

  initReports () {
    ['base1', 'base2', 'base3'].reduce((previous, current) => {
      return previous.then(async () => {
        const src = pdf.createLoadingTask(`http://localhost:8080/report/${current}?output=pdf`)
        const res = await src.promise
        this.reports.push({
          name: current,
          src: src,
          numPages: res.numPages
        })
      })
    }, Promise.resolve())
  }

  saveContents () {
    const result = (this.$refs.contents as any).getContents()
    contractTemplateService.save(result)
      .then(({ data: savedTemplate }) => {
        (this.$refs.contents as any).setContents(savedTemplate)
        this.e1 = 3
        this.pdfHref = `http://localhost:8080/report/${this.selectedComponent}?output=pdf&templateId=${savedTemplate.id}`
        this.pdfSrc = pdf.createLoadingTask(this.pdfHref)
        this.pdfSrc.promise.then((a: any) => {
          this.numPages = a.numPages
        })
      })
      .catch(() => {
        console.log('error')
      })
  }
}
</script>

<style scoped>
.v-sheet {overflow-y: auto;}
</style>
