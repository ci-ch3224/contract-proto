import { ContractParagraph } from '@/model/ContractParagraph'

export class ContractTemplate {
  templateName: string
  id = 0
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
