package collectionOperationsWithLambda.easy;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestCaseEasy {

    private static Logger logger = LoggerFactory.getLogger(collectionOperationsWithLambda.easy.TestCaseEasy.class);


    /**
     *
     1. Collectors.groupingBy(Function<? super T,? extends K> classifier) returns a Collector that groups elements of a Stream into multiple groups. Elements are grouped by the value returned by applying a classifier function on an element.

     2. It is important to understand that the return type of the collect method depends on the Collector that is passed as an argument. In this case, the return type would be Map<K, List<T> because that is the type specified in the Collector returned by the groupingBy method.

     3. Java 8 has added a default forEach method in Map interface. This method takes a BiConsumer function object and applies this function to each key-value pair of the Map. In this case, m is the key and n is the value.

     4. The given code provides a trivial lambda expression for BiConsumer that just prints the second parameter, which happens to be the value part of of the key-value pair of the Map.

     5. The value is actually an object of type List<Course>, which is printed in the output. Since there are two groups, two lists are printed. First list has only one Course element and the second list has three.
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
}


class Course {
    private String id;
    private String category;

    public Course(String id, String category) {
        this.id = id;
        this.category = category;
    }

    public String toString() {
        return id + " " + category;
    }

    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
