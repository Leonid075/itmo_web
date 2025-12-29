import { defineStore } from 'pinia';
import Point from '../api/point.js';

export const useResultStore = defineStore('result', {
  state: () => ({
    points: [],
    loading: false,
    error: null,
    timerId: null
  }),
  actions: {
    async fetchPoints() {
      this.loading = true;
      this.error = null;
      try {
        this.points = await Point.fetchAll();
      } catch (err) {
        this.error = err.message || 'Не удалось загрузить данные';
        console.error('Ошибка загрузки точек:', err);
      } finally {
        this.loading = false;
      }
    },
    startPolling() {
      if (this.timerId) {
        clearInterval(this.timerId);
      }
      this.fetchPoints();
      this.timerId = setInterval(() => {
        this.fetchPoints();
      }, 100000);
    },
    stopPolling() {
      if (this.timerId) {
        clearInterval(this.timerId);
        this.timerId = null;
      }
    }
  }
});