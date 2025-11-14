let x, r;

const xHandler = document.querySelectorAll('input[data-group="x"]');
const yHandler = document.getElementById('y');
const rHandler = document.querySelectorAll('button[data-group="r"]');
const alertHandler = document.getElementById('alert');
const graphHandler = document.getElementById('graph');

xHandler.forEach(el => {
    el.addEventListener('change', function (e) {
        xHandler.forEach(i => i.checked = false);
        e.target.checked = true;
        x = Number(e.target.value);
    })
})

rHandler.forEach(el => {
    el.addEventListener('click', function (e) {
        rHandler.forEach(i => i.disabled = false);
        e.target.disabled = true;
        r = Number(e.target.value);
    })
})

function isValid(str) {
    if (str === '') return false;
    if (isNaN(Number(yHandler.value))) return false;
    if (Number(yHandler.value) < -3 || Number(yHandler.value) > 3) return false;
    return true;
}

document.getElementById('submit').addEventListener('click', function (e) {
    submit();
});

document.getElementById('graph').addEventListener('click', function (e) {
    if (r == undefined) {
        alertHandler.textContent = "Установите R";
        return;
    }

    const rect = graphHandler.getBoundingClientRect();
    const _x = e.clientX - rect.left - 200;
    const _y = -(e.clientY - rect.top - 200);

    const xPercent = (_x / 150);
    const yPercent = (_y / 150);

    x = (xPercent * r).toFixed();
    yHandler.value = (yPercent * r).toFixed(10);

    submit();
});

function submit() {
    if (x == undefined) {
        alertHandler.textContent = "Установите X";
    } else if (x > 5 || x < -3) {
        alertHandler.textContent = "Некорректное значение X";
        x = null;
    } else if (!isValid(yHandler.value)) {
        alertHandler.textContent = "Некорректное значение Y";
        yHandler.value = '';
    } else if (r == undefined) {
        alertHandler.textContent = "Установите R";
    } else {
        window.location.href = `/lab2/controller?x=${x}&y=${yHandler.value}&r=${r}`;
    }
}