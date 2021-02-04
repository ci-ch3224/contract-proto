import axios from '@/plugins/axios'
import { Company } from '@/model/Company'

class CompanyService {
  getAll () {
    return axios.get('/companies')
  }
}
export const companyService = new CompanyService()
