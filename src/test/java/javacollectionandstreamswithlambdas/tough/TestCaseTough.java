package javacollectionandstreamswithlambdas.tough;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestCaseTough {

    private static Logger logger = LoggerFactory.getLogger(javacollectionandstreamswithlambdas.tough.TestCaseTough.class);

    /**
     * Examples with Collectors.summarizingInt. All result will be 6
     */
    @Test
    public void testCollectorsSummarizingInt() {
        List<Integer> names = Arrays.asList(1, 2, 3);
        long a = names.stream().collect(Collectors.mapping(x -> x, Collectors.summarizingInt(x-> x))).getSum();
        long b = names.stream().collect(Collectors.summarizingInt(x -> x)).getSum();
        logger.info("Результаты операций {}, {}", a, b);
    }


    /**
     * Since the first stream is made parallel, it may be partitioned into multiple pieces and each piece may be processed by a different thread. findAny may, therefore, return a value from any of those partitions. Hence, any number from 13 to 15 may be printed.
     * The second stream is sequential and therefore, ideally, findAny should return the first element. However, findAny is deliberately designed to be non-deterministic. Its API specifically says that it may return any element from the stream. If you want to select the first element, you should use findFirst.
     * Further findAny returns Optional object. Therefore, the output will be Optional[13] instead of just 13 (or any other number).
     */
    @Test
    public void testParallelStream() {
        Optional<Integer> a = IntStream.rangeClosed(10, 15).boxed().parallel().filter(x -> x > 12).findAny();
        Optional<Integer> b = IntStream.rangeClosed(10, 15).boxed().sequential().filter( x -> x > 12).findAny();
        logger.info("a = '{}', b = '{}'", a, b);
    }

}
