import { ContractParagraph } from '@/model/ContractParagraph'

export class ContractTemplate {
  private templateName: string
  title = ''
  subTitle = ''
  bigParagraphs: ContractParagraph[] = []
  createdBy = ''
  lastModifiedAt = ''

  constructor (templateName: string) {
    this.templateName = templateName
  }

  addParagraphs (paragraph: ContractParagraph) {
    this.bigParagraphs.push(paragraph)
  }
}
