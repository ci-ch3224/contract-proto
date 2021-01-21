export class ContractParagraph {
  id?: number = 0
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

  static new (old: ContractParagraph): ContractParagraph {
    const contractParagraph = new ContractParagraph()
    contractParagraph.title = old.title
    contractParagraph.contents = old.contents
    contractParagraph.seq = old.seq
    for (const sp of old.smallParagraphs) {
      contractParagraph.addParagraphs(ContractParagraph.new(sp))
    }
    return contractParagraph
  }
}
