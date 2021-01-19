export class ContractParagraph {
  title = ''
  contents = ''
  seq = 0
  smallParagraphs: ContractParagraph[] = []

  addParagraphs (paragraph: ContractParagraph) {
    this.smallParagraphs.push(paragraph)
  }

  removeParagraph (index: number) {
    this.smallParagraphs.splice(index, 1)
  }
}
