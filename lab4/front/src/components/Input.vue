<template>
    <div>
        <fieldset class="input-element">
            <legend>Set X:</legend>
            <span>
                <button @click="setX(-5)" value="-5" :disabled="store.x === -5" class="input-button">-5</button>
            </span>
            <span>
                <button @click="setX(-4)" value="-4" :disabled="store.x === -4" class="input-button">-4</button>
            </span>
            <span>
                <button @click="setX(-3)" value="-3" :disabled="store.x === -3" class="input-button">-3</button>
            </span>
            <span>
                <button @click="setX(-2)" value="-2" :disabled="store.x === -2" class="input-button">-2</button>
            </span>
            <span>
                <button @click="setX(-1)" value="-1" :disabled="store.x === -1" class="input-button">-1</button>
            </span>
            <span>
                <button @click="setX(0)" value="0" :disabled="store.x === 0" class="input-button">0</button>
            </span>
            <span>
                <button @click="setX(1)" value="1" :disabled="store.x === 1" class="input-button">1</button>
            </span>
            <span>
                <button @click="setX(2)" value="2" :disabled="store.x === 2" class="input-button">2</button>
            </span>
            <span>
                <button @click="setX(3)" value="3" :disabled="store.x === 3" class="input-button">3</button>
            </span>
            <br/>
            <span class="input-hint" v-bind:hidden="!store.xError">Неверное значение X</span>
        </fieldset>

        <fieldset class="input-element">
            <legend>Type Y:</legend>
            <input type="text" size="10" maxlength="17" id="y" placeholder="-3 ≤ Y ≤ 5" required
                class="input-string" :value="store.y" @input="setY($event.target.value)"/>
            <br/>
            <span class="input-hint" v-bind:hidden="!store.yError">Неверное значение Y</span>
        </fieldset>

        <fieldset class="input-element">
            <legend>Set R:</legend>

            <span>
                <button @click="setR(-5)" value="-5" :disabled="store.r === -5" class="input-button">-5</button>
            </span>
            <span>
                <button @click="setR(-4)" value="-4" :disabled="store.r === -4" class="input-button">-4</button>
            </span>
            <span>
                <button @click="setR(-3)" value="-3" :disabled="store.r === -3" class="input-button">-3</button>
            </span>
            <span>
                <button @click="setR(-2)" value="-2" :disabled="store.r === -2" class="input-button">-2</button>
            </span>
            <span>
                <button @click="setR(-1)" value="-1" :disabled="store.r === -1" class="input-button">-1</button>
            </span>
            <span>
                <button @click="setR(0)" value="0" :disabled="store.r === 0" class="input-button">0</button>
            </span>
            <span>
                <button @click="setR(1)" value="1" :disabled="store.r === 1" class="input-button">1</button>
            </span>
            <span>
                <button @click="setR(2)" value="2" :disabled="store.r === 2" class="input-button">2</button>
            </span>
            <span>
                <button @click="setR(3)" value="3" :disabled="store.r === 3" class="input-button">3</button>
            </span>
            <br/>
            <span class="input-hint" v-bind:hidden="!store.rError">Неверное значение R</span>
        </fieldset>

        <div>
            <button @click="createPoint" :disabled="store.isSubmitDisabled" class="input-button">Создать</button>
        </div>
    </div>
</template>

<script setup>
import { useInputStore } from '../stores/input';
import { useResultStore } from '../stores/result';
import Point from '../api/point.js';

const store = useInputStore();
const resStore = useResultStore();

function setX(value) {
    store.setX(value);
}

function setY(value) {
    store.setY(value);
}

function setR(value) {
    store.setR(value);
}

async function createPoint() {
    await Point.create({x: store.x, y: store.y, r: store.r});
    resStore.fetchPoints();
}
</script>

<style scoped>
.input-button {
    margin: 2px;
    width: max(30px, content);
}

.input-string {
    width: 200px;
}
</style>