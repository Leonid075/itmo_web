<template>
    <div style="width: 400px; height: 400px; cursor: crosshair;">
        <svg viewBox="0 0 400 400" xmlns="http://www.w3.org/2000/svg" @click="handleClick">
            <circle cx="200" cy="200" :r="store.r*25" class="light-blue" />
            <rect x="50" y="50" width="150" height="300" style="fill:white" />
            <rect x="200" y="50" width="150" height="150" style="fill:white" />
            <rect x="200" :y="200-store.r*25" :width="store.r*50" :height="store.r*25" class="light-blue" />
            <polygon :points="`200,200 200,${200-store.r*25} ${200-store.r*50},200`" class="light-blue" />
            <line x1="200" y1="20" x2="200" y2="380" stroke="black" />
            <line x1="20" y1="200" x2="380" y2="200" stroke="black" />
            <line x1="370" y1="204" x2="380" y2="200" stroke="black" />
            <line x1="370" y1="196" x2="380" y2="200" stroke="black" />
            <line x1="204" y1="30" x2="200" y2="20" stroke="black" />
            <line x1="196" y1="30" x2="200" y2="20" stroke="black" />
            <line x1="197" y1="275" x2="203" y2="275" stroke="black" />
            <line x1="197" y1="350" x2="203" y2="350" stroke="black" />
            <line x1="197" y1="125" x2="203" y2="125" stroke="black" />
            <line x1="197" y1="50" x2="203" y2="50" stroke="black" />
            <line x1="50" y1="197" x2="50" y2="203" stroke="black" />
            <line x1="125" y1="197" x2="125" y2="203" stroke="black" />
            <line x1="275" y1="197" x2="275" y2="203" stroke="black" />
            <line x1="350" y1="197" x2="350" y2="203" stroke="black" />
            <text x="210" y="30" class="small">y</text>
            <text x="370" y="190" class="small">x</text>
            <text x="46" y="195" class="small">R</text>
            <text x="346" y="195" class="small">R</text>
            <text x="205" y="54" class="small">R</text>
            <text x="205" y="354" class="small">R</text>
            <text x="115" y="195" class="small">R/2</text>
            <text x="265" y="195" class="small">R/2</text>
            <text x="205" y="129" class="small">R/2</text>
            <text x="205" y="279" class="small">R/2</text>
            
            <circle 
                v-for="point in points" 
                :cx="200 + (point.x * 50)"
                :cy="200 - (point.y * 50)"
                :r="3"
                :class="point.hit ? 'hit-point' : 'miss-point'"
            />
        </svg>
    </div>
</template>

<script setup>
import { useResultStore } from '../stores/result';
import { useInputStore } from '../stores/input';
import { storeToRefs } from 'pinia';
import Point from '../api/point';

const store = useInputStore();
const { points } = storeToRefs(useResultStore());

const handleClick = async (event) => {
    if (store.isSubmitDisabled) return;
    const svg = event.currentTarget;
    const pt = svg.createSVGPoint();
    pt.x = event.clientX;
    pt.y = event.clientY;
    const cursorPoint = pt.matrixTransform(svg.getScreenCTM().inverse());

    const graphX = (cursorPoint.x - 200) / 50;
    const graphY = (200 - cursorPoint.y) / 50;

    store.setX(Math.round(graphX));
    store.setY(graphY.toFixed(5));

    await Point.create({x: store.x, y: store.y, r: store.r});
    useResultStore().fetchPoints();
};
</script>

<style>
.light-blue {
    fill: rgba(0, 0, 255, 0.418);
}

.small {
    font: 12px sans-serif;
}

.hit-point {
    fill: rgb(0, 175, 0);
    stroke-width: 1px;
    stroke: black;
}

.miss-point {
    fill: red;
    stroke-width: 1px;
    stroke: black;
}
</style>
