declare module 'vue-signature-pad'{
  import VueSignaturePad from 'vue-signature-pad'
  export default VueSignaturePad = {
    methods: {
      undo() {
        this.$refs.signaturePad.undoSignature();
      },
      save() {
        const { isEmpty, data } = this.$refs.signaturePad.saveSignature();
      }
    }
  }
}