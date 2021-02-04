import { ContractParagraph } from '@/model/ContractParagraph'
import { ContractTemplate } from '@/model/ContractTemplate'
import { Company } from '@/model/Company'

export class Contract {
  id?: number = 0
  templateId = 0
  title = ''
  subTitle = ''
  contractDate = ''
  contractName = ''
  contractAmount = ''
  contractCondition = ''
  contractStartDate = ''
  contractEndDate = ''
  bigParagraphs: ContractParagraph[] = []
  createdBy = ''
  lastModifiedAt = ''
  gap: Company = null as any
  eul: Company = null as any

  addParagraphs (paragraph: ContractParagraph) {
    this.bigParagraphs.push(paragraph)
  }

  static of (contractTemplate: ContractTemplate): Contract {
    const contract = new Contract()
    contract.templateId = contractTemplate.id
    contract.title = contractTemplate.title
    contract.subTitle = contractTemplate.subTitle
    for (const bp of contractTemplate.bigParagraphs) {
      contract.bigParagraphs.push(ContractParagraph.new(bp))
    }
    return contract
  }
}
