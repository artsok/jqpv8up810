package builtinlambdatypes.easy;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;
import java.util.function.IntFunction;

public class TestCaseEasy {

    private static Logger logger = LoggerFactory.getLogger(lambdacookbook.easy.TestCaseEasy.class);

    /**
     * Обращаем внимание, что у интерфейса Consumer метод !!accept!!, чтобы выполнить операцию над параметром
     */
    @Test
    public void testConsumer() {
        Consumer<Book> c = b -> logger.info(b.id + " " + b.title);
        c.accept(new Book(1, "Java"));
    }

    /**
     * Обращаю внимание, что у интерфейса IntFunction, входной параметр int примитив а выходной параметр, что что
     * определим в <>. Для того чтобы выполнить операцию необходимо воспользоваться методом apply.
     *
     * IntFunction - standard functional interfaces is most suitable to process a large collection of int primitives
     * and return processed data for each of them.
     */
    @Test
    public void testIntFunction() {
        IntFunction<String> intToString = String::valueOf;
        logger.info("testIntFunction -  {}",  intToString.apply(4));
    }

    private class Book {
        private int id;
        private String title;

        Book(int id, String title) {
            this.id = id;
            this.title = title;
        }
    }

}
