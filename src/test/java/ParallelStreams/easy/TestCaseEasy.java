package ParallelStreams.easy;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;

public class TestCaseEasy {

    private static Logger logger = LoggerFactory.getLogger(ParallelStreams.easy.TestCaseEasy.class);

    /**
     * This code illustrates how you can take advantage of multiple cores and still preserve the order of the output.
     * Note that forEachOrdered forces the iteration of the elements of the stream in an ordered fashion.
     * However, any operation that is chained before forEachOrdered will still happen in parallel because the stream
     * is a parallel stream. Therefore, the map function will be executed by mutliple
     * threads on different elements of the stream in parallel taking advantage of multiple CPU cores.
     */
    @Test
    public void testForEachOrdered() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4);
        integers.stream().parallel().forEachOrdered(a -> logger.info("Число в массиве '{}'", a));
    }

    @Test
    public void testIntStreamParallel() {
        int[] array = new int[]{1, 2, 3, 4, 5, 6};
        OptionalDouble average = Arrays.stream(array).parallel().average();
        average.ifPresent(p -> logger.info("testIntStreamParallel '{}'", p));
    }
}
