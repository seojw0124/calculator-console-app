fun main() {
    val calculator = Calculator()
    var result: Double = Double.NaN
    var num1 = 0.0
    var isFirst = true // 처음 입력인지 여부를 나타내는 변수

    while (true) {
        if (isFirst) {
            println("첫번째 숫자 입력(종료하려면 -1 입력)")
            num1 = readDouble()
            if (num1 == -1.0) break
            else if (num1.isNaN()) {
                println("숫자를 입력해주세요.")
                continue
            }
        }

        showOperators(isFirst, result)

        if (!result.isNaN()) { // 이전 결과를 num1에 저장
            num1 = result
        }

        when (val operatorCode = readInt()) {
            in 1..4 -> {
                println("숫자 입력")
                var num2 = readDouble()
                while (num2.isNaN()) { // num2가 입력될 때까지 반복
                    println("숫자를 입력해주세요.")
                    num2 = readDouble()
                }
                result = calculator.calculate(operatorCode, num1, num2)
                isFirst = false
                println("$num1 ${operatorCodeToString(operatorCode)} $num2 = $result")
            }
            -1 -> break
            else -> println("잘못된 연산자입니다.")
        }
    }
}

fun readDouble(): Double {
    return readln().toDoubleOrNull() ?: Double.NaN
}

fun readInt(): Int {
    return readln().toIntOrNull() ?: -1
}

fun operatorCodeToString(operatorCode: Int): String { // 연산자 코드를 연산자 문자열로 변환하는 함수
    return when (operatorCode) {
        1 -> "+"
        2 -> "-"
        3 -> "*"
        4 -> "/"
        else -> "잘못된 연산자"
    }
}

fun showOperators(isFirst: Boolean, result: Double) { // 연산자를 출력하는 함수
    if (isFirst){
        println("""
        ===============계산기===============
        어떤 연산을 하시겠습니까?(종료하려면 -1 입력)
        1. +    2. -    3. *    4. /
        ===================================
    """.trimIndent())
    } else {
        println("""
        ===============계산기===============
        어떤 연산을 하시겠습니까?(종료하려면 -1 입력)
        1. +    2. -    3. *    4. /
        이전 결과: $result
        ===================================
    """.trimIndent())
    }
}