class Number2String {
  do(number, digit) {
    let num2str = ""
    let count = parseInt((String(number).length - 1) / digit)
    if (String(number).length >= digit) {
      for (let i = count; i >= 1; i--) {
        let quotient = parseInt(number / 10 ** (digit * i))
        num2str += `${quotient},`
        number -= quotient * 10 ** (digit * i)
      }
      switch (String(number).length) {
        case 1:
          num2str += `00${number}`
          break
        case 2:
          num2str += `0${number}`
          break
        case 3:
          num2str += `${number}`
          break
      }
      return num2str
    } else {
      num2str += `${number}`
      return num2str
    }
  }
}
export default new Number2String()
