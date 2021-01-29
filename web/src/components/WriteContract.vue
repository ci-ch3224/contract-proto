<template>
  <v-card>
    <v-toolbar
      dark
      color="primary"
    >
      <v-btn
        icon
        dark
        @click="$emit('close-dialog')"
      >
        <v-icon>mdi-close</v-icon>
      </v-btn>
      <v-toolbar-title>계약서 작성</v-toolbar-title>
    </v-toolbar>
    <v-card-text class="pa-0">
      <v-stepper class="pa-2" v-model="e1">
        <v-stepper-header>
          <v-stepper-step
            :complete="e1 > 1"
            step="1"
          >
            템플릿 선택
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
            <v-container fluid :style="{ height: containerHeight + 'px' }">
              <v-row class="grey lighten-4">
                <v-col md="3">
                  <v-card>
                    <v-card-text>
                      <v-text-field
                        outlined
                        append-icon="mdi-magnify"
                        @click:append="searchTemplate()"
                        clearable
                        label="템플릿 제목 검색"
                        type="text"
                      ></v-text-field>
                      <v-divider></v-divider>
                      <v-list class="pa-0 overflow-auto mt-4" :style="{ height: containerHeight - 148 + 'px' }">
                        <v-list-item-group
                          color="primary"
                        >
                          <v-list-item
                            v-for="item in templateList"
                            :key="item.id"
                            @click="selectTemplate(item)"
                          >
                            <v-list-item-content class="pa-2 grey lighten-4">
                              <v-list-item-title>{{ item.title }}</v-list-item-title>
                              <pdf :src="item.src"></pdf>
                            </v-list-item-content>
                          </v-list-item>
                        </v-list-item-group>
                      </v-list>
                    </v-card-text>
                  </v-card>
                </v-col>
                <v-col md="9">
                  <div v-if="selectedTemplate" style="overflow-y: auto;" :style="{ height: containerHeight - 12 + 'px' }">
                    <pdf
                      v-for="i in selectedTemplate.numPages"
                      :key="i"
                      :src="selectedTemplate.src"
                      :page="i"
                      class="mb-1"
                    ></pdf>
                  </div>
                </v-col>
              </v-row>
            </v-container>
            <div class="mt-5">
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
          </v-stepper-content>

          <!-- 2. 내용 입력 -->
          <v-stepper-content step="2">
            <component ref="contents" v-bind:is="selectedComponent">
            </component>
            <div class="mt-2">
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
            </div>
          </v-stepper-content>

          <!-- 3. 미리보기 및 완료 -->
          <v-stepper-content step="3">
            <v-sheet
              color="grey lighten-4"
              height="100%"
              class="d-flex justify-center"
            >
              <div style="width:70%;background-color: white;">
                <pdf
                  v-for="i in numPages"
                  :key="i"
                  :src="pdfSrc"
                  :page="i"
                ></pdf>
              </div>
            </v-sheet>
            <div class="mt-2">
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
            </div>
          </v-stepper-content>
        </v-stepper-items>
      </v-stepper>
    </v-card-text>
  </v-card>
</template>

<script lang="ts">
import 'codemirror/lib/codemirror.css'
import '@toast-ui/editor/dist/toastui-editor.css'

import { Component, Vue } from 'vue-property-decorator'
import { Editor } from '@toast-ui/vue-editor'
import pdf from 'vue-pdf'
import { ContractTemplate } from '@/model/ContractTemplate'
import { Contract } from '@/model/Contract'
import { contractTemplateService } from '@/service/ContractTemplateService'
import { contractService } from '@/service/ContractService'
import Base1 from '@/components/contracts/Base1.vue'
import Base2 from '@/components/contracts/Base2.vue'
import Base3 from '@/components/contracts/Base3.vue'
import SignContractor from '@/components/contracts/SignContractor.vue'

interface Template {
  id: number;
  title: string;
  templateName: string;
  src: any;
  numPages: number;
}

@Component({
  components: {
    Editor, pdf, Base1, Base2, Base3
  }
})
export default class WriteContract extends Vue {
  e1 = 1
  selectedComponent = ''
  templateList: Template[] = []
  selectedTemplate: Template = (null as any) as Template
  pdfHref = ''
  pdfSrc?: any = ''
  numPages = 3
  containerHeight = window.innerHeight - 224

  handleResize () {
    this.containerHeight = window.innerHeight - 224
  }

  mounted () {
    window.addEventListener('resize', this.handleResize)
  }

  beforeDestroy () {
    window.removeEventListener('resize', this.handleResize)
  }

  cancel () {
    if (this.e1 > 1) {
      this.e1--
    }
  }

  step1 () {
    this.e1 = 2
    this.selectedComponent = this.selectedTemplate.templateName
    // component에 template -> contract로 변환하여 전달
    contractTemplateService.getOne(this.selectedTemplate.id).then(res => {
      (this.$refs.contents as any).setContents(Contract.of(res.data))
    })
  }

  selectTemplate (template: Template) {
    template.src.promise.then((res: any) => {
      template.numPages = res.numPages
      this.selectedTemplate = template
    })
  }

  searchTemplate () {
    contractTemplateService.getAll().then(({ data: page }) => {
      this.templateList = []
      page.content.forEach((item: ContractTemplate) => {
        this.templateList.push({
          id: item.id || 0,
          title: item.title || '',
          templateName: item.templateName,
          src: pdf.createLoadingTask(`http://localhost:8080/report/${item.templateName}?output=pdf&templateId=${item.id}`),
          numPages: 1
        })
      })
    })
  }

  saveContents () {
    const result = (this.$refs.contents as any).getContents()
    contractService.save(result)
      .then(({ data: savedContract }) => {
        (this.$refs.contents as any).setContents(savedContract)
        this.e1 = 3
        this.pdfHref = `http://localhost:8080/report/${this.selectedTemplate.templateName}?output=pdf&contractId=${savedContract.id}`
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
