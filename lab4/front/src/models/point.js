export default class Point {
  constructor({timestamp, x, y, r, hit }) {
    this.timestamp = new Date(timestamp);
    this.x = Number.isFinite(parseInt(x, 10)) ? parseInt(x, 10) : 0
    this.y = Number.isFinite(parseFloat(y)) ? parseFloat(y) : 0.0
    this.r = Number.isFinite(parseFloat(r)) ? parseFloat(r) : 0.0
    this.hit = !!hit
  }
}
