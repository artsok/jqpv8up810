package lambdacookbook.tough;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestCaseTough {

    private static Logger logger = LoggerFactory.getLogger(TestCaseTough.class);

    /**
     * A directory in the root of the file system named "a" contains a file named "a.java" and
     * a subdirectory named "b". The subdirectory "b" contains a file named "b.java".
     * What will be printed by the following line of code?
     * <p>
     * The maxDepth parameter specifies how deep should the walk method traverse down the directory structure.
     * A value of 0 implies that will not even go inside the starting directory. Therefore, it will just print the starting directory.
     * <p>
     * You need to remember the following points about Files.walk method:
     * 1. The first value that is returns is the starting directory itself. In this question, it will be "a".
     * 2. It walks the directory in a depth first manner, which means, it processes the children of a directory first before moving to the sibling of the directory. In this question, once it starts processing a/b, it will process a/b/b.java before moving to a/c (assuming there is a c inside a as well, because then c would be a sibling to b).
     * 3. The order of processing of siblings is not defined. For example, there is no guarantee that a/b will be processed before a/c.
     */
    @Test
    public void testWalk() throws IOException {
        Files.walk(Paths.get("./"), 0)
                .forEach(str -> logger.info("file : {}", str.getFileName()));
    }

    /**
     * Deep dive
     *
     * @throws IOException if a problem occurs.
     */
    @Test
    public void testDeepWalt() throws IOException {
        Stream<Path> files = Files.walk(Paths.get("./"), Integer.MAX_VALUE);
        files.close();
    }

    /**
     * Given that c:\temp\pathtest is a directory that contains several directories.
     * Each sub directory contains several files but there is exactly one regular file named test.txt within the whole directory structure.
     * Which of the given options can be inserted in the code below so that it will print complete path of test.txt?
     *
     * @throws IOException if a problem occurs.
     */
    @Test
    public void testFindInDeepWithMatchers() throws IOException {
        Stream<Path> pathStream =
                Files.find(
                        Paths.get("c:\\temp\\pathtest"),
                        Integer.MAX_VALUE,
                        (p, a) -> p.endsWith("test.txt") && a.isRegularFile());
        pathStream.close();
    }


    /**
     * Return a Stream that is lazily populated with Path by searching for files in a file tree rooted at a given starting file.
     *
     * Parameters:
     * start - the starting file
     * maxDepth - the maximum number of directory levels to search
     * matcher - the function used to decide whether a file should be included in the returned stream
     * options - options to configure the traversal
     *
     * Returns: the Stream of Path
     *
     * Throws:
     * IllegalArgumentException - if the maxDepth parameter is negative
     * SecurityException - If the security manager denies access to the starting file. In the case of the default provider, the checkRead method is invoked to check read access to the directory.
     * IOException - if an I/O error is thrown when accessing the starting file.
     * @throws IOException if a problem occurs.
     */
    @Test
    public void testFindWithFileVisitOption() throws IOException {
        Stream<Path> files = Files.find(Paths.get("./"), Integer.MAX_VALUE, (p, a) -> a.size() > 1000, FileVisitOption.FOLLOW_LINKS);
        files.close();
    }


    /**
     * IntStream's range method returns a sequential ordered IntStream from startInclusive (inclusive) to endExclusive (exclusive) by an incremental step of 1.
     * The rangeClosed method works similarly except that the second parameter is also included in the result.
     */
    @Test
    public void testIntStreamRange() {
        IntStream is = IntStream.range(1, 4);
        OptionalInt sum = is.reduce((a, b) -> a + b);
        int a = sum.orElse(0);
        logger.info("a = {}", a);
    }

    @Test
    public void testIntStreamRangeClosed() {
        IntStream is = IntStream.rangeClosed(1, 4);
        int value = is.reduce(0, (a, b) -> a + b);
    }

    /**
     * There are several things going on here:
     * 1. IntStream.range returns a sequential ordered IntStream from startInclusive (inclusive) to endExclusive (exclusive) by an incremental step of 1. Therefore, is1 contains 1, 2.
     * 2. IntStream.rangeClosed returns a sequential ordered IntStream from startInclusive (inclusive) to endInclusive (inclusive) by an incremental step of 1. Therefore, is2 contains 1, 2, 3.
     * 3. IntStream.concat returns a lazily concatenated stream whose elements are all the elements of the first stream followed by all
     * the elements of the second stream. Therefore, is3 contains 1, 2, 1, 2, 3.
     * 4. is3 is a stream of primitive ints. is3.boxed() returns a new Stream containing Integer objects instead of primitives.
     * This allows the use various flavors of collect method available in non-primitive streams. [IntStream does have one collect method but it does not take a Collector as argument.]
     * 5. Collectors.groupingBy(k->k) creates a Collector that groups the elements of the stream by a key returned by the function k->k, which is nothing but the value in the stream itself.
     * Therefore, it will group the elements into a Map<Integer, List<Integer>> containing: {1=[1, 1], 2=[2, 2], 3=[3]}
     * 6. Finally, get(3) will return [3].
     */
    @Test
    public void testIntStreamConcatBoxedCollect() {
        IntStream is1 = IntStream.range(1, 3);
        IntStream is2 = IntStream.rangeClosed(1, 3);
        IntStream is3 = IntStream.concat(is1, is2);
        Object val = is3.boxed().collect(Collectors.groupingBy(k->k)).get(3);
        logger.info("val = {}", val);
    }


    /**
     * This code illustrates the use of Collectors.partitioningBy method.
     * This method takes a Predicate and returns Collector that distributes the elements of the stream into two groups -
     * one containing elements for which the Predicate returns true, and another containing elements for which the Predicate returns false.
     * The return type is a Map containing two keys - true and false and the values are Lists of the elements.
     *
     * IntStream.rangeClosed(10, 15) creates an IntStream of int primitives containing elements 10, 11, 12, 13, 14, and 15
     * (Observe that 15 is included).
     *
     * IntStream does not support the various collect methods supported by a regular Stream of objects.
     * But it does support a boxed() method that returns a Stream<Integer> containing Integer objects.
     */
    @Test
    public void testPartitioningBy() {
        Stream<Integer> values = IntStream.rangeClosed(10, 15).boxed();
        Object obj = values.collect(Collectors.partitioningBy(x -> x%2==0));
        logger.info("Результат {}", obj);
    }

    /**
     * Read user and password from file
     * @throws IOException
     */
    @Test
    public void readPasswordFromFile() throws IOException {
        Map<String, String> userPasswords = Files.lines(Paths.get("./"))
                .map(str -> str.split(":")).collect(Collectors.toMap(arr -> arr[0], arr -> arr[1]));

    }






}
