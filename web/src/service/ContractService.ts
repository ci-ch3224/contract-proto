import axios from '@/plugins/axios'
import { Contract } from '@/model/Contract'

class ContractService {
  getAll () {
    return axios.get('/contracts')
  }

  getOne (id: number) {
    return axios.get(`/contracts/${id}`)
  }

  save (data: Contract) {
    return axios.post('/contracts', data)
  }
}
export const contractService = new ContractService()
