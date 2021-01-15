<template>
  <v-form>
    <v-card outlined elevation="2" class="mt-2">
      <v-card-title>표지</v-card-title>
      <v-card-text>
        <v-text-field
          :value="contractTemplate.title"
          label="계약서명"
        ></v-text-field>
        <v-text-field
          label="소제목"
          :value="contractTemplate.subTitle"
        ></v-text-field>
      </v-card-text>
    </v-card>
    <v-card outlined elevation="2" class="mt-2">
      <v-card-title>계약서 본문</v-card-title>
      <v-card-text>
        <editor initialEditType="wysiwyg"
                height="500px"
                ref="toastuiEditor"
        />
      </v-card-text>
    </v-card>
    <v-card outlined elevation="2" class="mt-2">
      <v-card-title>약관</v-card-title>
      <v-card-text>
        <v-text-field
          label="약관명"
          :value="contractTemplate.bigParagraphs[1].title"
        ></v-text-field>
        <v-card-actions>
          <v-btn
            color="primary"
            outlined
            rounded
            @click="addArticle()"
          >
            추가
          </v-btn>
          <v-btn
            outlined
            rounded
            @click="test()"
          >
            test
          </v-btn>
        </v-card-actions>
        <v-card-text>
          <v-card
            v-for="(item, i) in contractTemplate.bigParagraphs[1].smallParagraphs"
            :key="i"
            class="mt-2"
          >
            <v-app-bar
              flat
              color="rgba(0, 0, 0, 0)"
            >
              <v-text-field
                label="조항명"
                :prefix="'제 ' + (i+1) + ' 조'"
                :value="item.title"
              ></v-text-field>
              <v-spacer></v-spacer>
              <v-btn
                icon
                @click="deleteArticle(i)"
              >
                <v-icon>mdi-close</v-icon>
              </v-btn>
            </v-app-bar>
            <v-card-text>
              <editor initialEditType="wysiwyg"
                      height="150px"
                      :ref="'sp' + i"
              />
            </v-card-text>
          </v-card>
        </v-card-text>
      </v-card-text>
    </v-card>
  </v-form>
</template>

<script lang="ts">
import 'codemirror/lib/codemirror.css'
import '@toast-ui/editor/dist/toastui-editor.css'
import '@toast-ui/editor/dist/toastui-editor-viewer.css'

import { Component, Vue } from 'vue-property-decorator'
import { Editor } from '@toast-ui/vue-editor'

class ContractParagraph {
  title = ''
  contents = ''
  seq = 0
  smallParagraphs: ContractParagraph[] = []

  getParagraphCount () {
    return this.smallParagraphs.length
  }

  addParagraphs (paragraph: ContractParagraph) {
    this.smallParagraphs.push(paragraph)
  }

  removeParagraph (index: number) {
    this.smallParagraphs.splice(index, 1)
  }
}

class ContractTemplate {
  title = ''
  subTitle = ''
  bigParagraphs: ContractParagraph[] = []

  addParagraphs (paragraph: ContractParagraph) {
    this.bigParagraphs.push(paragraph)
  }
}

@Component({
  components: {
    Editor
  }
})
export default class Base3 extends Vue {
  contractTemplate: ContractTemplate = new ContractTemplate()

  constructor () {
    super()
    this.contractTemplate.addParagraphs(new ContractParagraph()) // 계약본문
    this.contractTemplate.addParagraphs(new ContractParagraph()) // 약관
  }

  addArticle () {
    this.contractTemplate.bigParagraphs[1].addParagraphs(new ContractParagraph())
  }

  deleteArticle (index: number) {
    this.contractTemplate.bigParagraphs[1].removeParagraph(index)
  }

  test () {
    const spCount = this.contractTemplate.bigParagraphs[1].getParagraphCount()
    for (let i = 0; i < spCount; i++) {
      const editor: Editor = (this.$refs[`sp${i}`] as Vue[])[0] as Editor
      const sp = this.contractTemplate.bigParagraphs[1].smallParagraphs[i]
      sp.contents = editor.invoke('getHtml')
      sp.seq = i
    }

    console.log(this.contractTemplate)
  }
}
</script>
