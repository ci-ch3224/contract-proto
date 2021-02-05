import axios from '@/plugins/axios'
import { Sign } from '@/model/Sign'

class SignService {
  getAll () {
    return axios.get('/signs')
  }

  save (data: Sign) {
    return axios.post('/signs', data)
  }
}
export const signService = new SignService()
