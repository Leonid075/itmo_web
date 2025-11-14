const tableHandler = document.getElementById('table');
const svg = document.getElementById('svg');

let points = [];

tableHandler.querySelectorAll('tr').forEach((e) => {
    const cells = e.querySelectorAll('td');
    points.push({ "x": Number(cells[1].textContent), "y": Number(cells[2].textContent) })
});

rHandler.forEach(el => {
    el.addEventListener('click', function (e) {
        r = Number(e.target.value);
        svg.querySelectorAll('circle[data-group="p"]').forEach((e) => {
            e.remove();
        });
        for (const point of points) {
            const circle = document.createElementNS("http://www.w3.org/2000/svg", "circle");
            circle.setAttribute("data-group", "p");
            circle.setAttribute("cx", 200 + point.x / r * 150);
            circle.setAttribute("cy", 200 - point.y / r * 150);
            circle.setAttribute("r", 3);
            svg.appendChild(circle);
        }
        console.log(r);
    })
})