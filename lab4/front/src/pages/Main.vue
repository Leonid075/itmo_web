<template>
    <Header></Header>
    <Container>
        <Graph></Graph>
        <Input></Input>
        <Result></Result>
        <div style="padding-top: 20px;">
          <button @click="logout">Выйти</button>
        </div>
    </Container>
</template>

<script setup>
import Header from "../components/Header.vue"
import Graph from "../components/Graph.vue"
import Container from "../components/Container.vue"
import Input from "../components/Input.vue"
import Result from "../components/Result.vue"
import { onMounted, onUnmounted } from 'vue';
import { useResultStore } from '../stores/result';
import router from "../router"

const logout = async () => {
  localStorage.removeItem('auth');
  router.push('/');
}

const store = useResultStore();

onMounted(() => {
  store.startPolling();
});

onUnmounted(() => {
  store.stopPolling();
});
</script>

<style>
</style>
