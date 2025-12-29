<template>
  <Container>
    <div style="width: 400px;">
      <h2>Авторизация</h2>
      <form>
        <fieldset class="input-element">
            <legend>Имя пользователя:</legend>
            <input
              class="input-string"
              type="text" 
              id="username" 
              v-model="authForm.username" 
              required
            />
        </fieldset>
        <fieldset class="input-element">
            <legend>Пароль:</legend>
            <input 
              class="input-string"
              type="password" 
              id="password" 
              v-model="authForm.password" 
              required
            />
        </fieldset>
        <button type="submit" class="input-button" @click="authorize" :disabled="loading">Войти</button>
        <button type="button" class="input-button" @click="register" :disabled="loading">Зарегистрироваться</button>
        <div v-if="authError" class="error">{{ authError }}</div>
      </form>
    </div>
  </Container>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import Container from "../components/Container.vue"
import authAPI from '../api/auth.js'

const router = useRouter()

const authForm = ref({
  username: '',
  password: ''
})

const loading = ref(false)
const authError = ref('')

const authorize = async () => {
  loading.value = true
  authError.value = ''
  
  try {
    const credentials = btoa(`${authForm.value.username}:${authForm.value.password}`)
    localStorage.setItem('auth', credentials)
    
    const response = await authAPI.login()
    
    if (response.status === 200) {
      router.push('/main')
    } else {
      localStorage.removeItem('auth')
      authError.value = 'Неверное имя пользователя или пароль'
    }
  } catch (error) {
    localStorage.removeItem('auth')
    if (error.response && error.response.status === 401) {
      authError.value = 'Неверное имя пользователя или пароль'
    } else {
      authError.value = 'Ошибка авторизации'
    }
  } finally {
    loading.value = false
  }
}

const register = async () => {
  loading.value = true
  authError.value = ''
  
  try {
    const response = await authAPI.register({
      username: authForm.value.username,
      password: authForm.value.password
    })
    
    if (response.status === 201) {
      const credentials = btoa(`${authForm.value.username}:${authForm.value.password}`)
      localStorage.setItem('auth', credentials)
      router.push('/main')
    }
  } catch (error) {
    if (error.response && error.response.status === 409) {
      authError.value = 'Пользователь с таким именем уже существует'
    } else {
      authError.value = 'Ошибка регистрации'
    }
  } finally {
    loading.value = false
  }
}
</script>

<style scoped>
.input-button {
    margin-top: 20px;
    margin: 4px;
    width: max(30px, content);
}

.input-string {
    width: 200px;
}

.auth-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f5f5f5;
}

.auth-form {
  background: white;
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 400px;
}

.form-group {
  margin-bottom: 1rem;
}
</style>
