const tableHandler = document.querySelectorAll('table[data-group="table"]')[0];
const svg = document.getElementById('svg');

tableHandler.querySelectorAll('tr').forEach((e) => {
    const cells = e.querySelectorAll('td');
    if (cells.length === 0) return;
    const circle = document.createElementNS("http://www.w3.org/2000/svg", "circle");
    circle.setAttribute("data-group", "p");
    circle.setAttribute("cx", 200 + Number(cells[1].textContent)*37.5);
    circle.setAttribute("cy", 200 - Number(cells[2].textContent)*37.5);
    if (cells[4].textContent === "true") {
        circle.setAttribute("fill", "green");
    } else {
        circle.setAttribute("fill", "red");
    }
    circle.setAttribute("r", 3);
    svg.appendChild(circle);
});