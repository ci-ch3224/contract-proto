<template>
  <div>base2</div>
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
export default class Base2 extends Vue {
  editorText = '<p>hello~</p>'
}
</script>
