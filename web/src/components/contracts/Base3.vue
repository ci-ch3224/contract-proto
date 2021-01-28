<template>
  <v-form>
    <v-card outlined elevation="2" class="mt-2">
      <v-card-title>표지</v-card-title>
      <v-card-text>
        <v-text-field
          v-model="contract.title"
          label="제목"
        ></v-text-field>
        <v-text-field
          label="소제목"
          v-model="contract.subTitle"
        ></v-text-field>
        <v-menu
          v-model="contractDateMenu"
          :close-on-content-click="false"
          :nudge-right="40"
          transition="scale-transition"
          offset-y
          min-width="auto"
        >
          <template v-slot:activator="{ on, attrs }">
            <v-text-field
              v-model="contract.contractDate"
              label="계약일"
              prepend-icon="mdi-calendar"
              readonly
              v-bind="attrs"
              v-on="on"
            ></v-text-field>
          </template>
          <v-date-picker
            no-title
            locale="ko-kr"
            v-model="contract.contractDate"
            @input="contractDateMenu = false"
          ></v-date-picker>
        </v-menu>
      </v-card-text>
    </v-card>
    <v-card outlined elevation="2" class="mt-2" v-if="contract.bigParagraphs[0]">
      <v-card-title>갑지</v-card-title>
      <v-card-text>
        <v-row no-gutters>
          <v-col>
            <v-text-field
              v-model="contract.bigParagraphs[0].title"
              label="제목"
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row no-gutters>
          <v-col>
            <v-text-field
              v-model="contract.contractName"
              label="계약명"
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row no-gutters>
          <v-col>
            <v-text-field
              v-model="contract.contractAmount"
              label="금액"
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row no-gutters>
          <v-col>
            <v-textarea
              v-model="contract.contractCondition"
              label="조건"
              rows="3"
            ></v-textarea>
          </v-col>
        </v-row>
        <v-row no-gutters>
          <v-col md="1">
            <v-text-field disabled label="계약기간" />
          </v-col>
          <v-col md="5.5">
            <v-menu
              v-model="contractStartDateMenu"
              :close-on-content-click="false"
              :nudge-right="40"
              transition="scale-transition"
              offset-y
              min-width="auto"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-text-field
                  :value="contract.contractStartDate"
                  label="시작일"
                  prepend-icon="mdi-calendar"
                  readonly
                  v-bind="attrs"
                  v-on="on"
                ></v-text-field>
              </template>
              <v-date-picker
                no-title
                locale="ko-kr"
                v-model="contract.contractStartDate"
                @input="contractStartDateMenu = false"
              ></v-date-picker>
            </v-menu>
          </v-col>
          <v-col md="5.5">
            <v-menu
              v-model="contractEndDateMenu"
              :close-on-content-click="false"
              :nudge-right="40"
              transition="scale-transition"
              offset-y
              min-width="auto"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-text-field
                  :value="contract.contractEndDate"
                  label="종료일"
                  prepend-icon="mdi-calendar"
                  readonly
                  v-bind="attrs"
                  v-on="on"
                ></v-text-field>
              </template>
              <v-date-picker
                no-title
                locale="ko-kr"
                v-model="contract.contractEndDate"
                @input="contractEndDateMenu = false"
              ></v-date-picker>
            </v-menu>
          </v-col>
        </v-row>
        <v-row no-gutters>
          <v-col>
            <editor initialEditType="wysiwyg"
                    height="500px"
                    ref="contractContents"
                    :initialValue="contract.bigParagraphs[0].contents"
            />
          </v-col>
        </v-row>
      </v-card-text>
    </v-card>
    <v-card outlined elevation="2" class="mt-2" v-if="contract.bigParagraphs[1]">
      <v-card-title>조항</v-card-title>
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
                :disabled="item.necessary === 'Y'"
              ></v-text-field>
              <v-spacer></v-spacer>
              <v-btn
                v-if="item.necessary !== 'Y'"
                icon
                @click="deleteArticle(i)"
              >
                <v-icon>mdi-close</v-icon>
              </v-btn>
            </v-app-bar>
            <v-card-text>
              <editor v-if="item.necessary !== 'Y'"
                      initialEditType="wysiwyg"
                      height="150px"
                      :initialValue="item.contents"
                      :ref="'sp' + i"
              />
              <viewer v-else
                      height="150px"
                      :initialValue="item.contents"
              />
              <div v-if="item.necessary === 'Y'">필수 조항입니다.</div>
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
import { Editor, Viewer } from '@toast-ui/vue-editor'
import { ContractParagraph } from '@/model/ContractParagraph'
import { Contract } from '@/model/Contract'

@Component({
  components: {
    Editor, Viewer
  }
})
export default class Base3 extends Vue {
  contractDateMenu = false // 계약일 datepicker open 여부
  contractStartDateMenu = false
  contractEndDateMenu = false
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
