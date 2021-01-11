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
      <v-toolbar-title>템플릿 등록</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-toolbar-items>
        <v-btn
          dark
          text
          @click="$emit('close-dialog')"
        >
          Save
        </v-btn>
      </v-toolbar-items>
    </v-toolbar>
    <v-card-text class="pa-0">
      <v-stepper class="pa-2" v-model="e1">
        <v-stepper-header>
          <v-stepper-step
            :complete="e1 > 1"
            step="1"
          >
            본문작성
          </v-stepper-step>

          <v-divider></v-divider>

          <v-stepper-step
            :complete="e1 > 2"
            step="2"
          >
            날인 위치 설정 - 1
          </v-stepper-step>

          <v-divider></v-divider>

          <v-stepper-step step="3">
            날인 위치 설정 - 2
          </v-stepper-step>
        </v-stepper-header>

        <v-stepper-items>
          <v-stepper-content step="1" class="py-2 px-0">
            <editor :initialValue="editorText"
                    initialEditType="wysiwyg"
                    height="500px"
                    ref="toastuiEditor"
            />
            <div class="mt-2">
              <v-btn
                color="primary"
                @click="step1()"
              >
                Continue
              </v-btn>

              <v-btn text>
                Cancel
              </v-btn>
            </div>
          </v-stepper-content>

          <v-stepper-content step="2">
            <viewer :initialValue="editorText"
                    height="500px"
            />

            <div class="mt-2">
              <v-btn
                color="primary"
                @click="e1 = 3"
              >
                Continue
              </v-btn>

              <v-btn text>
                Cancel
              </v-btn>
            </div>
          </v-stepper-content>

          <v-stepper-content step="3">
            <v-card
              class="mb-12"
              color="grey lighten-1"
              height="200px"
            ></v-card>

            <v-btn
              color="primary"
              @click="e1 = 1"
            >
              Continue
            </v-btn>

            <v-btn text>
              Cancel
            </v-btn>
          </v-stepper-content>
        </v-stepper-items>
      </v-stepper>
    </v-card-text>
  </v-card>
</template>

<script lang="ts">
import 'codemirror/lib/codemirror.css'
import '@toast-ui/editor/dist/toastui-editor.css'
import '@toast-ui/editor/dist/toastui-editor-viewer.css'

import { Component, Vue } from 'vue-property-decorator'
import { Editor, Viewer } from '@toast-ui/vue-editor'

@Component({
  components: {
    Editor, Viewer
  }
})
export default class WriteTemplate extends Vue {
  editorText = '<p>hello~</p>'
  e1 = 1

  step1 () {
    this.e1 = 2
    this.editorText = this.getHtml()
    console.log(this.editorText)
  }

  getHtml () {
    const html = (this.$refs.toastuiEditor as HTMLFormElement).invoke('getHtml')
    return html
  }
}
</script>
