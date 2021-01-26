<template>
  <v-form>
    <v-card outlined elevation="2" class="mt-2">
      <v-card-title>표지</v-card-title>
      <v-card-text>
        <v-text-field
          v-model="contractTemplate.title"
          label="제목"
        ></v-text-field>
        <v-text-field
          label="소제목"
          v-model="contractTemplate.subTitle"
        ></v-text-field>
      </v-card-text>
    </v-card>
    <v-card outlined elevation="2" class="mt-2">
      <v-card-title>갑지</v-card-title>
      <v-card-text>
        <v-text-field
          v-model="contractTemplate.bigParagraphs[0].title"
          label="제목"
        ></v-text-field>
        <editor initialEditType="wysiwyg"
                height="400px"
                ref="contractContents"
        />
      </v-card-text>
    </v-card>
    <v-card outlined elevation="2" class="mt-2">
      <v-card-title>조항</v-card-title>
      <v-card-text>
        <v-text-field
          label="약관명"
          v-model="contractTemplate.bigParagraphs[1].title"
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
                v-model="item.title"
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
              <v-checkbox v-model="item.necessary" value="Y">
                <template v-slot:label>
                  <div>
                    필수조항으로 설정합니다.
                  </div>
                </template>
              </v-checkbox>
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

import { Component, Vue, Emit } from 'vue-property-decorator'
import { Editor } from '@toast-ui/vue-editor'
import { ContractParagraph } from '@/model/ContractParagraph'
import { ContractTemplate } from '@/model/ContractTemplate'

@Component({
  components: {
    Editor
  }
})
export default class Base3 extends Vue {
  contractTemplate: ContractTemplate = new ContractTemplate('base3')

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

  @Emit('getContents')
  getContents (): ContractTemplate {
    this.contractTemplate.bigParagraphs[0].seq = 0
    this.contractTemplate.bigParagraphs[1].seq = 1
    this.contractTemplate.bigParagraphs[1].smallParagraphs.forEach((sp, i) => {
      const editor: Editor = (this.$refs[`sp${i}`] as Vue[])[0] as Editor
      sp.contents = editor.invoke('getHtml')
      sp.seq = i
    })

    this.contractTemplate.bigParagraphs[0].contents = (this.$refs.contractContents as Editor).invoke('getHtml')
    return this.contractTemplate
  }

  setContents (savedTemplate: ContractTemplate): void {
    this.contractTemplate = savedTemplate
  }
}
</script>
