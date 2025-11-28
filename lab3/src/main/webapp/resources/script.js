const xHandler = document.querySelectorAll('input[data-group="x"]');
const alertHandler = document.getElementById('x-alert');

xHandler.forEach(el => {
    el.addEventListener('change', function (e) {
        xHandler.forEach(i => i.checked = false);
        e.target.checked = true;
        alertHandler.textContent = '';
    })
})