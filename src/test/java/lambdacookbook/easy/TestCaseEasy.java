package lambdacookbook.easy;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestCaseEasy {

    private static Logger logger = LoggerFactory.getLogger(TestCaseEasy.class);

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
     * The maxDepth parameter specifies how deep should the walk method traverse down the directory structure.
     * A value of 0 implies that will not even go inside the starting directory. Therefore, it will just print the starting directory.
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
     * The stream is lazily populated, which means, it doesn't read the whole file upfront.
     * It reads the file as you consume the elements of the stream. This helps if you have a large file.
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
    public void testPrintStream() {
        Stream<List<String>> s1 = Stream.of(Arrays.asList("a", "b"), Arrays.asList("a", "c"));
        Stream<String> news = s1.filter(s -> s.contains("c")).flatMap(Collection::stream);
        news.forEach(str -> logger.info(str));
    }

    /**
     * Метод readAllLines, читает весь файл целиком и могут быть проблемы при чтение больших файлов. Возвращает List<String>
     * Both the methods - readAllLines and lines - throw an exception if you try to pass a path to a directory instead of a regular file.
     *
     * @throws IOException if a problem occurs.
     */
    @Test
    public void testReadAllLines() throws IOException {
        Files.readAllLines(Paths.get(""), Charset.defaultCharset()).forEach(logger::info);
    }


    /**
     * The objective of the given code is to collect multiple values for a given key in a map.
     * When a value for a new key is to be inserted, it needs to put a List in the map first before adding the key to the List.

     computeIfAbsent is perfect for this. This methods checks if the key exists in the map.
     If it does, the method just returns the value associated with that key.
     If it doesn't, the method executes the Function, associates the value returned by that Function in the map with that key, and returns that value.
     */
    @Test
    public void testComputeIfAbsent() {
        Map<String, List<Double>> groupedValues = new HashMap<>();
        groupedValues.computeIfAbsent("key", (a) -> new ArrayList<>()).add(2.0);
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
