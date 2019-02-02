package collectionOperationsWithLambda.easy;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Операцию reduce стоит использовать, когда имеется коллекция значений,
 * а нужно получить единственное значение в качестве результата.
 *
 * лямбда-выражение известное как аккумулятор (Accumulator), которое служит для сворачивания данных в одну "кучу".
 */
public class ReduceExample {

    /**
     * В данном примере, инициализации будет значением 0 и {@link java.util.function.IntBinaryOperator},
     * функцией, которая принимает два Int и возвращает Int. Когда используется значение инициализации,
     * НЕ используется Optional.
     * Arrays.stream(int[]), преобразует в IntStream
     *
     * reduce(T identity, BinaryOperator<T> accumulator), так как IntStream используется IntBinaryOperator
     */
    @Test
    public void simpleIntReduce() {
        int[] arr = {1, 2, 3, 4};
        int result = Arrays.stream(arr).reduce(0, (a, b) -> a + b);
        System.out.println("result = " + result);
    }

    /**
     * Так как нет значения identity получаем Optional и по хорошему требуется, что-то вернуть, если нет исключения.
     */
    @Test
    public void simpleIntReduceWithoutIdentity() {
        int[] arr = {1, 2, 3, 4};
        int result = Arrays.stream(arr).reduce((a, b) -> a + b).orElseGet(() -> 0);
        System.out.println("result = " + result);
    }


    /**
     * Разбираем работу reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner), в этом
     * случае добавляется аргумент combiner.
     *
     * В данном случае метод reduce принимает три параметра - identity, accumulator, combiner.
     * Где accumulator умножает каждое значение из Stream-a на начальное значение (identity),
     * а combiner собирает результат работы accumulator.
     *
     * Combiner работает только в параллельном Stream, иначе объединять нечего!!!
     *
     */
    @Test
    public void simpleIntReduceWithIdentityAndCombiner() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);

        //Только с parallelStream()! будет - (0 + 1) * (0 + 2) * (0 + 3) * (0 + 4)
        int result = list.parallelStream().reduce(0, (identity, val) -> identity + val, (c, d) -> c * d);
        System.out.println("result = " + result);

        //Обратите внимание используется stream(), третий параметр НЕ задействован
        result = list.stream().reduce(1, (identity, val) -> identity + val, (c, d) -> c * d);
        System.out.println("result = " + result);
    }
}
