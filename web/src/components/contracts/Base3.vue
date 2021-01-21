<template>
  <v-form>
    <v-card outlined elevation="2" class="mt-2">
      <v-card-title>표지</v-card-title>
      <v-card-text>
        <v-text-field
          v-model="contract.title"
          label="계약서명"
        ></v-text-field>
        <v-text-field
          label="소제목"
          v-model="contract.subTitle"
        ></v-text-field>
      </v-card-text>
    </v-card>
    <v-card outlined elevation="2" class="mt-2">
      <v-card-title>계약서 본문</v-card-title>
      <v-card-text>
        <editor initialEditType="wysiwyg"
                height="500px"
                ref="contractContents"
                :initialValue="contract.bigParagraphs[0].contents"
        />
      </v-card-text>
    </v-card>
    <v-card outlined elevation="2" class="mt-2">
      <v-card-title>약관</v-card-title>
      <v-card-text>
        <v-text-field
          label="약관명"
          v-model="contract.bigParagraphs[1].title"
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
            v-for="(item, i) in contract.bigParagraphs[1].smallParagraphs"
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
                      :initialValue="item.contents"
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

import { Component, Vue, Emit, Prop } from 'vue-property-decorator'
import { Editor } from '@toast-ui/vue-editor'
import { ContractParagraph } from '@/model/ContractParagraph'
import { Contract } from '@/model/Contract'

@Component({
  components: {
    Editor
  }
})
export default class Base3 extends Vue {
  contract: Contract = new Contract()

  addArticle () {
    this.contract.bigParagraphs[1].addParagraphs(new ContractParagraph())
  }

  deleteArticle (index: number) {
    this.contract.bigParagraphs[1].removeParagraph(index)
  }

  @Emit('getContents')
  getContents (): Contract {
    this.contract.bigParagraphs[0].seq = 0
    this.contract.bigParagraphs[1].seq = 1
    this.contract.bigParagraphs[1].smallParagraphs.forEach((sp, i) => {
      const editor: Editor = (this.$refs[`sp${i}`] as Vue[])[0] as Editor
      sp.contents = editor.invoke('getHtml')
      sp.seq = i
    })

    this.contract.bigParagraphs[0].contents = (this.$refs.contractContents as Editor).invoke('getHtml')
    return this.contract
  }

  setContents (contract: Contract): void {
    this.contract = contract
  }
}
</script>
