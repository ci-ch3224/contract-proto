<template>
  <div>
    <div><input placeholder="title"></div>
    <div>
      <editor :initialValue="editorText"
              initialEditType="wysiwyg"
              height="500px"
              ref="toastuiEditor"
      />
    </div>
  </div>
</template>

<script lang="ts">
import 'codemirror/lib/codemirror.css'
import '@toast-ui/editor/dist/toastui-editor.css'
import '@toast-ui/editor/dist/toastui-editor-viewer.css'

import { Component, Vue } from 'vue-property-decorator'
import { Editor } from '@toast-ui/vue-editor'

@Component({
  components: {
    Editor
  }
})
export default class Base1 extends Vue {
  editorText = '<p>hello~</p>'
}
</script>
