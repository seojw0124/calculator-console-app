abstract class AbstractOperation { // 추상 클래스
    abstract fun calculate(num1: Double, num2: Double): Double // 추상 클래스는 abstract 키워드를 사용하여 함수 선언 (구현부가 없을 때)
}

class AddOperation : AbstractOperation() { // Operation 추상 클래스를 상속받은 AddOperation(더하기) 클래스
    override fun calculate(num1: Double, num2: Double) = num1 + num2
}

class SubtractOperation : AbstractOperation() { // Operation 추상 클래스를 상속받은 SubtractOperation(빼기) 클래스
    override fun calculate(num1: Double, num2: Double) = num1 - num2
}

class MultiplyOperation : AbstractOperation() { // Operation 추상 클래스를 상속받은 MultiplyOperation(곱하기) 클래스
    override fun calculate(num1: Double, num2: Double) = num1 * num2
}

class DivideOperation : AbstractOperation() { // Operation 추상 클래스를 상속받은 DivideOperation(나누기) 클래스
    override fun calculate(num1: Double, num2: Double): Double {
        if (num2 == 0.0) {
            println("0으로 나눌 수 없습니다.")
            throw ArithmeticException() // 0으로 나눌 때 예외 발생
        }
        return num1 / num2
    }
}

class Calculator { // 계산기 클래스: 추상클래스를 사용하여 연산을 추상화 -> Calculator 클래스와 연산자 클래스들 간의 결합도를 낮춤 -> 각 연산 클래스의 변경이 Calculator 클래스에 영향을 주지 않음 -> 의존성 역전 원칙(DIP)
                   // 만약 추상클래스를 사용하지 않았다면 Calculator 클래스에서 연산을 직접 구현해야 했을 것 -> 연산 클래스의 변경이 Calculator 클래스에 영향을 줌
    private val operations = mapOf( // 연산자 코드와 연산자 객체를 매핑한 맵
        1 to AddOperation(),
        2 to SubtractOperation(),
        3 to MultiplyOperation(),
        4 to DivideOperation()
    )

    fun calculate(operatorCode: Int, num1: Double, num2: Double): Double { // 연산자 코드와 두 숫자를 받아서 연산을 수행하는 함수
        // 연산자 코드에 해당하는 연산자 객체를 가져옴, main 함수에서 when 문으로 연산자 코드를 검증했기 때문에 null이 나올 수 없음, 그래도 써보고 싶어서 requireNotNull 사용
        val operation = requireNotNull(operations[operatorCode]) { "잘못된 연산자입니다." }
        return operation.calculate(num1, num2)
    }
}

fun readDouble(): Double {
    return readln().toDoubleOrNull() ?: Double.NaN
}

fun readInt(): Int {
    return readln().toIntOrNull() ?: -1
}

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
