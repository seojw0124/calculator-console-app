class Calculator { // 계산기 클래스: 추상클래스를 사용하여 연산을 추상화 -> Calculator 클래스와 연산자 클래스들 간의 결합도를 낮춤 -> 각 연산 클래스의 변경이 Calculator 클래스에 영향을 주지 않음 -> 의존성 역전 원칙(DIP)
                   // 만약 추상클래스를 사용하지 않았다면 Calculator 클래스에서 연산을 직접 구현해야 했을 것 -> 연산 클래스의 변경이 Calculator 클래스에 영향을 줌
    private val operations = mapOf( // 연산자 코드와 연산자 객체를 매핑한 맵
        1 to AddOperation(),
        2 to SubtractOperation(),
        3 to MultiplyOperation(),
        4 to DivideOperation()
    )

    fun calculate(operatorCode: Int, num1: Double, num2: Double): Double { // 연산자 코드와 두 숫자를 받아서 연산을 수행하는 함수
        // 연산자 코드에 해당하는 연산자 객체를 가져옴, main 함수에서 when 문으로 연산자 코드를 검증했기 때문에 null이 나올 수 없음, 그래도 써보고 싶어서 requireNotNull() 사용
        val operation = requireNotNull(operations[operatorCode]) { "잘못된 연산자입니다." }
        return operation.calculate(num1, num2)
    }
}
