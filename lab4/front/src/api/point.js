import apiClient from './client'
import Point from '../models/point'

export default {
  async fetchAll() {
    const resp = await apiClient.get('/point')
    if (!Array.isArray(resp.data)) {
      console.log("123" + resp.data);
      return [];
    }
    return resp.data.map(item => new Point(item))
  },
  
  async create(pointData) {
    await apiClient.post('/point', pointData)
  }
}
