package techcourse.fp.mission;

import java.util.List;
import java.util.function.Predicate;

public class Calculator {

    public static int sumAll(List<Integer> numbers) {
        int total = 0;
        for (int number : numbers) {
            total += number;
        }
        return total;
    }

    public static int sumAllEven(List<Integer> numbers) {
        int total = 0;
        for (int number : numbers) {
            if (number % 2 == 0) {
                total += number;
            }
        }
        return total;
    }

    public static int sumAllOverThree(List<Integer> numbers, Conditional condition) {
        //TODO: List에 담긴 값 중 3보다 큰 수만을 더해야 한다.
//        int total = 0;
//        for (int number : numbers) {
//            if (conditional.filter(number)) {
//                total += number;
//            }
//        }
//
//        return total;
        int total = 0;
        for (int number : numbers) {
            if (condition.filter(number)) {
                total += number;
            }
        }
        return total;
    }
}
