package lambdacookbook.easy;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestCase {

    private static Logger logger = LoggerFactory.getLogger(TestCase.class);

    /**
     * Given that Book is a valid class with appropriate constructor and getPrice and getTitle methods
     * that returns a double and a String respectively, consider the following code:
     * <p>
     * Работа со stream и flatmap
     */
    @Test
    public void testMatToDoubleAndSum() {
        List<List<Book>> books = Arrays.asList(
                Arrays.asList(new Book("Windmills of the Gods", 7.0), new Book("Tell me your dreams", 9.0)),
                Arrays.asList(new Book("There is a hippy on the highway", 5.0), new Book("Easy come easy go", 5.0)));

        double price = books.stream().flatMap(Collection::stream).mapToDouble(book -> book.price).sum();

        logger.info("Результат операции {}", price);
    }

    /**
     * Пример работы с методом find() - Возвращает Stream<Path>
     *
     * @throws IOException if a problem occurs.
     */
    @Test
    public void testStreamFilesFind() throws IOException {
        Stream<Path> files = Files.find(Paths.get("./"), 10, (path, attrs) -> {
            logger.info("Path name - '{}'", path.getFileName());
            return path.toString().endsWith(".xml") && attrs.isRegularFile();
        });
        logger.info("File counts '{}'", files.count());
    }

    /**
     * Работа с методом list() из класса Files
     *
     * @throws IOException if a problem occurs.
     */
    @Test
    public void testStreamList() throws IOException {
        Stream<Path> file = Files.list(Paths.get("./" + ""));
        logger.info("File list '{}'", file.count());
    }

    /**
     * Lazy read file, line by line.
     *
     * @throws IOException if a problem occurs.
     */
    @Test
    public void testStreamLine() throws IOException {
        Stream<String> fileLines = Files.lines(Paths.get("./pom.xml"), Charset.defaultCharset());
        String lines = fileLines.collect(Collectors.joining(""));
        logger.info("Concat string:\n {}", lines);
    }

    /**
     * What will the following code print when compiled and run?
     */
    @Test
    public void printStream() {
        Stream<List<String>> s1 = Stream.of(Arrays.asList("a", "b"), Arrays.asList("a", "c"));
        Stream<String> news = s1.filter(s -> s.contains("c")).flatMap(Collection::stream);
        news.forEach(str -> logger.info(str));
    }

    private class Book {
        private final String context;
        private final Double price;

        Book(String context, Double price) {
            this.context = context;
            this.price = price;
        }
    }
}
