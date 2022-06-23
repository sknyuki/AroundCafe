// 60분 이내 Timer
class TimerBeforeHour {
  timeToString(time) {
    const minutes = this.checkLength(parseInt(time / 60).toString())
    const seconds = this.checkLength(parseInt(time % 60).toString())
    return `${minutes}:${seconds}`
  }

  checkLength(time) {
    if (time.length === 2) {
      return time
    } else if (time.length === 1 && time !== "0") {
      return `0${time}`
    } else if (time === "0") {
      return `00`
    }
  }
}
export default new TimerBeforeHour()
