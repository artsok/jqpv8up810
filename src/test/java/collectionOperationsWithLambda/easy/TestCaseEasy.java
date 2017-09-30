package collectionOperationsWithLambda.easy;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TestCaseEasy {

    private static Logger logger = LoggerFactory.getLogger(collectionOperationsWithLambda.easy.TestCaseEasy.class);


    /**
     * 1. Collectors.groupingBy(Function<? super T,? extends K> classifier) returns a Collector that groups elements of a Stream into multiple groups. Elements are grouped by the value returned by applying a classifier function on an element.
     * <p>
     * 2. It is important to understand that the return type of the collect method depends on the Collector that is passed as an argument. In this case, the return type would be Map<K, List<T> because that is the type specified in the Collector returned by the groupingBy method.
     * <p>
     * 3. Java 8 has added a default forEach method in Map interface. This method takes a BiConsumer function object and applies this function to each key-value pair of the Map. In this case, m is the key and n is the value.
     * <p>
     * 4. The given code provides a trivial lambda expression for BiConsumer that just prints the second parameter, which happens to be the value part of of the key-value pair of the Map.
     * <p>
     * 5. The value is actually an object of type List<Course>, which is printed in the output. Since there are two groups, two lists are printed. First list has only one Course element and the second list has three.
     */
    @Test
    public void testCollectorsGroupingBy() {
        List<Course> list = Arrays.asList(
                new Course("OCAJP", "Java"),
                new Course("OCPJP", "Java"),
                new Course("C#", "C#"),
                new Course("OCEJPA", "Java"));

        list.stream().collect(Collectors.groupingBy(Course::getCategory)).forEach((m, n) -> logger.info("testCollectorsGroupingBy - {}, {}", m, n));
    }

    @Test
    public void testPredicateAllMatch() {
        List<String> values = Arrays.asList("Alpha A", "Alpha B", "Alpha C");
        boolean flag = values.stream().allMatch(str -> str.equals("Alha"));
        logger.info("Результат testPredicateAllMatch '{}'", flag);
    }

    @Test
    public void testFindFirst() {
        List<String> values = Arrays.asList("Alpha A", "Alpha B", "Alpha C");
        Optional<String> opt = values.stream().findFirst();
        logger.info("Результат testFindFirst '{}'", opt);
    }

    @Test
    public void testFindAny() {
        List<String> values = Arrays.asList("Alpha A", "Alpha B", "Alpha C");
        Optional<String> opt = values.stream().findAny();
        logger.info("Результа testFindAny '{}'", opt);
    }

    @Test
    public void testOptional() {
        Optional<String> gradle = Optional.empty();
        gradle.of("Pass");
        logger.info("testOptional '{}'", gradle);
    }

    /**
     * The average() method actually returns an OptionalDouble (and not Double).
     * If the filter condition changes to something such that no element satisfies the condition, the stream will
     * contain nothing and the average() method will return an OptionalDouble containing OptionalDouble.empty.
     * In that case, the getAsDouble method will throw a java.util.NoSuchElementException.  
     * To avoid this problem, instead of getAsDouble, you should use orElse(0.0).
     * That way, if the OptionalDouble is empty, it will return 0.0.
     */
    @Test
    public void testAverage() {
        List<Book> books = Arrays.asList(new Book("Thinking in Java", 30.0),
                new Book("Java in 24 hrs", 20.0),
                new Book("Java Recipies", 10.0));

        double averagePrice = books.stream()
                .filter(b -> b.getPrice() > 10)
                .mapToDouble(b -> b.getPrice())
                .average()
                .getAsDouble();
        logger.info("testAverage '{}'",averagePrice);
    }

}

@Data
@AllArgsConstructor
class Book {
    private String title;
    private double price;
}

@Data
@AllArgsConstructor
class Course {
    private String id;
    private String category;
}
