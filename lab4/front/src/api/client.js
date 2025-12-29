import axios from 'axios'

// Create an axios instance with default configuration
const apiClient = axios.create({
  baseURL: '/api',
  timeout: 10000
})

// Add a request interceptor to include auth header if available
apiClient.interceptors.request.use(
  (config) => {
    const auth = localStorage.getItem('auth')
    if (auth && config.url !== '/auth/register') {
      config.headers.Authorization = `Basic ${auth}`
    }
    return config
  },
  (error) => {
    return Promise.reject(error)
  }
)

// Add a response interceptor to handle auth errors
apiClient.interceptors.response.use(
  (response) => {
    return response
  },
  (error) => {
    if (error.response && error.response.status === 401) {
      localStorage.removeItem('auth')
      if (window.location.pathname !== '/') {
        window.location.href = '/'
      }
    }
    return Promise.reject(error)
  }
)

export default apiClient
