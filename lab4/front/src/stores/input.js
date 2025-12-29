import { defineStore } from 'pinia';

export const useInputStore = defineStore('input', {
  state: () => ({
    x: null,
    y: '',
    r: 1,
    xError: false,
    yError: false,
    rError: false,
  }),
  getters: {
    isSubmitDisabled() {
      return this.xError || this.yError || this.rError || this.x === null || String(this.y).trim() === '';
    }
  },
  actions: {
    setX(value) {
      if (Number.isInteger(value) && value >= -5 && value <= 3) {
        this.x = value;
        this.xError = false;
      } else {
        this.xError = true;
      }
    },
    setY(value) {
      this.y = value;
      const yFloat = parseFloat(String(value).replace(',', '.'));
      if (!isNaN(yFloat) && yFloat >= -3 && yFloat <= 5) {
        this.yError = false;
      } else {
        if (String(value).trim() === '') {
            this.yError = false;
        } else {
            this.yError = true;
        }
      }
    },
    setR(value) {
      if (Number.isInteger(value) && value >= 0 && value <= 3) {
        this.r = value;
        this.rError = false;
      } else {
        this.r = -0.00001;
        this.rError = true;
      }
    }
  }
});