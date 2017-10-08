package javacollectionandstreamswithlambdas.easy;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class TestCaseEasy {

    private static Logger logger = LoggerFactory.getLogger(lambdacookbook.easy.TestCaseEasy.class);

    /**
     * filter method removes all the elements for which the given condition (i.e. b.getTitle().startsWith("F"))
     * returns false from the stream. These elements are not removed from the underlying list but only from the stream.
     * Therefore, when you create a stream from the list again, it will have all the elements from the list.
     * Since the setPrice operation changes the Book object contained in the list,
     * the updated value is shown the second time when you go through the list.
     */
    @Test
    public void testStreamForEach() {
        class Book {
            private Double price;
            private String title;

            private void setPrice(Double price) {
                this.price = price;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            private Book(String title, Double price) {
                this.price = price;
                this.title = title;
            }
        }
        List<Book> books = asList(
                new Book("Freedom at Midnight", 5.0),
                new Book("Gone with the wind", 5.0),
                new Book("Midnight Cowboy", 15.0));

        books.stream().filter(b -> b.title.startsWith("F")).forEach(b -> b.setPrice(10.0));
        books.stream().forEach(b -> logger.info("Результат '{}', '{}'", b.price, b.title));
    }

    /**
     * Collector.counting returns a Collector that returns a long.
     * You cannot assign it to an int without a cast.
     * If you make it long, it will print 2 because there are two elements with length greater than 4.
     */
    @Test
    public void testCollectorsCounting() {
        List<String> names = asList("charles", "chuk", "cynthia", "cho", "cici");
        long cont = names.stream().filter(name -> name.length() > 4).collect(Collectors.counting());
    }


    /**
     * If it were a Stream of mutable objects such as StringBuilders and if you append something to the elements in the
     * first forEach, that would change the original StringBuilder objects contained in the list.
     * In that case, the second forEach will actually print the updated values.
     * For example, the following code will indeed print ab and aab.
     */
    @Test
    public void testMutableForEachStream() {
        List<StringBuilder> a = asList(new StringBuilder("a"), new StringBuilder("b"));
        a.forEach(sb -> sb.append("123"));
        a.stream().forEach(sb -> logger.info("Результат {}", sb));
    }

    @Test
    public void testFunctionOperation() {
        List<String> list = Arrays.asList("a", "aa", "aaa");
        Function<String, Integer> func = String::length;
        Consumer<Integer> consumer = x -> System.out.println("Len: " + x + "");
        list.stream().map(func).forEach(consumer);
    }

    @Test
    public void testMethodReference() {

        class Student {
            private Student(String name, int marks) {
                this.name = name;
                this.marks = marks;
            }

            private String name;
            private int marks;

            private void addMarks(int m) {
                this.marks += m;
            }

            private void debug() {
                System.out.println(name + ":" + marks);
            }
        }

        List<Student> slist = Arrays.asList(
                new Student("S1", 40),
                new Student("S2", 35),
                new Student("S3", 30));
        Consumer<Student> increaseMarks = s -> s.addMarks(10);
        slist.forEach(increaseMarks);
        slist.stream().forEach(Student::debug);

    }

    @Test
    public void testFilterWithComparator() {
        List<String> al = Arrays.asList("aa", "aaa", "b", "cc", "ccc", "ddd", "a");
        long count = al.stream().filter(element -> element.compareTo("c") > 0).count();
        logger.info("Результат testFilterWithComparator() '{}'", count);
    }

}
