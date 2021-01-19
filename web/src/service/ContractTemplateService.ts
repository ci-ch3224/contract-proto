import axios from '@/plugins/axios'
import { ContractTemplate } from '@/model/ContractTemplate'

class ContractTemplateService {
  getAll () {
    return axios.get('/contractTemplates')
  }

  getOne (id: number) {
    return axios.get(`/contractTemplates/${id}`)
  }

  save (data: ContractTemplate) {
    return axios.post('/contractTemplates', data)
  }
}
export const contractTemplateService = new ContractTemplateService()
