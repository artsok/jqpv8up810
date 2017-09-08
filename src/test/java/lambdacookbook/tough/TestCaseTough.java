package lambdacookbook.tough;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class TestCaseTough {

    private static Logger logger = LoggerFactory.getLogger(TestCaseTough.class);

    /**
     * A directory in the root of the file system named "a" contains a file named "a.java" and
     * a subdirectory named "b". The subdirectory "b" contains a file named "b.java".
     * What will be printed by the following line of code?
     *
     * The maxDepth parameter specifies how deep should the walk method traverse down the directory structure.
     * A value of 0 implies that will not even go inside the starting directory. Therefore, it will just print the starting directory.
     *
     *  You need to remember the following points about Files.walk method:
     1. The first value that is returns is the starting directory itself. In this question, it will be "a".
     2. It walks the directory in a depth first manner, which means, it processes the children of a directory first before moving to the sibling of the directory. In this question, once it starts processing a/b, it will process a/b/b.java before moving to a/c (assuming there is a c inside a as well, because then c would be a sibling to b).
     3. The order of processing of siblings is not defined. For example, there is no guarantee that a/b will be processed before a/c.
     */
    @Test
    public void testWalk() throws IOException {
        Files.walk(Paths.get("./"), 0)
                .forEach(str -> logger.info("file : {}", str.getFileName()));
    }

    /**
     * Deep dive
     * @throws IOException if a problem occurs.
     */
    @Test
    public void testDeepWalt() throws IOException {
        Stream<Path> files = Files.walk(Paths.get("./"), Integer.MAX_VALUE);
        files.close();
    }

    /**
     * 	Given that c:\temp\pathtest is a directory that contains several directories.
     * 	Each sub directory contains several files but there is exactly one regular file named test.txt within the whole directory structure.
     * 	Which of the given options can be inserted in the code below so that it will print complete path of test.txt?
     *
     * @throws IOException if a problem occurs.
     */
    @Test
    public void testFindInDeepWithMatchers() throws IOException {
        Stream<Path> pathStream =
                Files.find(
                        Paths.get("c:\\temp\\pathtest"),
                        Integer.MAX_VALUE,
                        (p, a)-> p.endsWith("test.txt")&& a.isRegularFile());
        pathStream.close();
    }

}
