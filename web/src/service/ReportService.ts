import axios from '@/plugins/axios'

class ReportService {
  getHtml (name: string) {
    return axios.get(`/report/${name}?output=html&templateId=`)
  }
}
export const reportService = new ReportService()
