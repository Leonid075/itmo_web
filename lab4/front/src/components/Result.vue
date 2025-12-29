<template>
  <div class="result-container">
    <h2>Результаты проверки точек</h2>
    
    <div v-if="loading" class="loading">
      Загрузка данных...
    </div>
    
    <div v-else-if="error" class="error">
      Ошибка загрузки: {{ error }}
    </div>
    
    <div v-else-if="points.length === 0" class="empty">
      Нет сохранённых точек
    </div>
    
    <table v-else class="points-table">
      <thead>
        <tr>
          <th>Время запроса</th>
          <th>X</th>
          <th>Y</th>
          <th>R</th>
          <th>Результат</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(point, index) in points" :key="index" :class="{ hit: point.hit, miss: !point.hit }">
          <td>{{ point.timestamp.toLocaleString('ru-RU') }}</td>
          <td>{{ point.x }}</td>
          <td>{{ point.y.toFixed(2) }}</td>
          <td>{{ point.r.toFixed(2) }}</td>
          <td>
            <span class="status-badge" :class="{ 'status-hit': point.hit, 'status-miss': !point.hit }">
              {{ point.hit ? 'Да' : 'Нет' }}
            </span>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { useResultStore } from '../stores/result';
import { storeToRefs } from 'pinia';

const { points, loading, error } = storeToRefs(useResultStore());
</script>

<style scoped>
.points-table {
    border-collapse: collapse;
}

.points-table th,
.points-table td {
    border: 1px solid black;
    padding: 8px 12px;
    text-align: center;
}
</style>