const yHandler = document.querySelectorAll('input[data-group="y"]')[0];
const rHandler = document.querySelectorAll('input[data-group="r"]')[0];

const circle = document.getElementById('circle');
const rect = document.getElementById('rect');
const triangle = document.getElementById('triangle');

const graphHandler = document.getElementById('graph');
const sendHandler = document.getElementById('sendButton');

function slider(e) {
    const newR = parseFloat(rHandler.value);
    if (!Number.isNaN(newR)) {
        circle.setAttribute('r', 37.5*newR);
        rect.setAttribute('width', 37.5*newR);
        rect.setAttribute('height', 37.5*newR);
        rect.setAttribute('y', 200-37.5*newR);
        triangle.setAttribute('points', `200,200 200,${37.5*newR+200} ${200-37.5*newR},200`);
    }
}

function setX(x) {
    const values = [-2, -1.5, -1, -0.5, 0, 0.5, 1];
    const closestValue = values.reduce((prev, curr) => 
        (Math.abs(curr - x) < Math.abs(prev - x) ? curr : prev)
    );

    const diff = Math.abs(closestValue - x);
    
    if (diff < 0.5) {
        const checkboxToSelect = Array.from(xHandler).find(checkbox => {
            const label = checkbox.nextElementSibling;
            return label && parseFloat(label.textContent.trim()) === closestValue;
        });

        if (checkboxToSelect) {
            xHandler.forEach(i => i.checked = false);
            checkboxToSelect.checked = true;
        }
    } else {
        alertHandler.textContent = 'Не удалось подобрать значение X';
        throw new Error('Не удалось подобрать значение X');
    }
}

graphHandler.addEventListener('click', function (e) {
    alertHandler.textContent = ''
    const rect = graphHandler.getBoundingClientRect();
    const _x = e.clientX - rect.left - 200;
    const _y = -(e.clientY - rect.top - 200);

    const xPercent = (_x / 37.5);
    const yPercent = (_y / 37.5);

    yHandler.value = yPercent.toFixed(10);
    setX(xPercent);

    sendHandler.click();
});

document.addEventListener('DOMContentLoaded', slider);
