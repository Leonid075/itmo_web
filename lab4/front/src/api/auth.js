import apiClient from './client'

export default {
  register(userData) {
    return apiClient.post('/auth/register', userData)
  },
  
  login() {
    return apiClient.get('/auth/check')
  },
  
  logout() {
    localStorage.removeItem('auth')
  }
}
