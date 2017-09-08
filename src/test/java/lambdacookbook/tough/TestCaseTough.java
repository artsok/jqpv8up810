package lambdacookbook.tough;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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

}
