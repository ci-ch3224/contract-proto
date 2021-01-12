import axios from '@/plugins/axios'

class ReportService {
  async getHtml (name: string) {
    return await axios.get(`/report/${name}?output=html`)
  }
}
export const reportService = new ReportService()
