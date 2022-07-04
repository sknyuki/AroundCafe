// 필터 관련 함수가 존재하는 파일

export function pricePoint(pricePoint) {
  return pricePoint.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")
}

export function ratingPoint(ratingPoint) {
  return ratingPoint.toString().replace(/\B(?=(\d{1})+(?!\d))/g, ".")
}

export function yyyyMMdd(value) {
  if (value == "") return ""

  var js_date = new Date(value)

  var year = js_date.getFullYear()
  var month = js_date.getMonth() + 1
  var day = js_date.getDate()

  if (month < 10) {
    month = "0" + month
  }

  if (day < 10) {
    day = "0" + day
  }

  return year + "-" + month + "-" + day
}

export function HHmm(value2) {
  if (value2 == "") return ""

  var js_time = new Date(value2)

  var hour = js_time.getHours()
  var min = js_time.getMinutes()

  if (hour < 10) {
    hour = "0" + hour
  }

  if (min < 10) {
    min = "0" + min
  }

  return hour + ":" + min
}
