import axios from '@/plugins/axios'
import { ContractTemplate } from '@/model/ContractTemplate'
import { Pageable } from '@/model/Pageable'

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

  getPage (pageable: Pageable) {
    return axios.get('/contractTemplates', {
      params: pageable,
      paramsSerializer: (params) => {
        return this.getQueryString(params)
      }
    })
  }

  private getQueryString (obj: any): string {
    let qs = ''
    Object.entries(obj).forEach(
      ([key, value]) => {
        qs += key + '=' + value
      }
    )
    return qs
  }
}
export const contractTemplateService = new ContractTemplateService()
