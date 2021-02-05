import axios from '@/plugins/axios'
import { Contract } from '@/model/Contract'
import { Pageable } from '@/model/Pageable'

class ContractService {
  getAll () {
    return axios.get('/contracts')
  }

  getPage (pageable: Pageable) {
    return axios.get('/contracts', {
      params: pageable,
      paramsSerializer: (params) => {
        return this.getQueryString(params)
      }
    })
  }

  getOne (id: number) {
    return axios.get(`/contracts/${id}`)
  }

  save (data: Contract) {
    return axios.post('/contracts', data)
  }

  private getQueryString (obj: any): string {
    let qs = ''
    Object.entries(obj).forEach(
      ([key, value]) => {
        qs += key + '=' + value + '&'
      }
    )
    return qs
  }
}
export const contractService = new ContractService()
