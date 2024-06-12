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
            throw ArithmeticException() // 0으로 나눌 때 예외 발생
        }
        return num1 / num2
    }
}