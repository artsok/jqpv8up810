package builtinlambdatypes.easy;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.*;

public class TestCaseEasy {

    private static Logger logger = LoggerFactory.getLogger(lambdacookbook.easy.TestCaseEasy.class);

    /**
     * Обращаем внимание, что у интерфейса Consumer метод !!accept!!, чтобы выполнить операцию над параметром
     * Только принимает параметры, но ничего не производит
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

    /**
     * Обратить внимание, что для того чтобы получить результат необходимо вызвать get
     * Производит данные, но ничего не потребляет.
     */
    @Test
    public void testSupplier() {
        String name = "bob"; String val = null;
        Supplier<String> a = name::toUpperCase;
        val = a.get();
        logger.info("testSupplier -  {}", val);
    }

    @Test
    public void testForFun() {
        String val1 = "hello";
        StringBuilder val2 = new StringBuilder("world");
        UnaryOperator<String> uo1 = s1->s1.concat(val1);
        UnaryOperator<String> uo2 = String::toUpperCase;
        //System.out.println(uo1.apply(uo2.apply(val2)));
        //Remember that StringBuilder and StringBuffer do not extend String. The UnaryOperaty uo2 has been typed to String and therefore, you cannot apply it to a StringBuilder object.
    }

    /**
     * Supplier - return a value. To get a value, we must use getAsDouble();
     *
     java.util.function.DoubleSupplier (and other similar Suppliers such as IntSupplier and LongSupplier) is a
     functional interface with the functional method named getAsDouble.
     The return type of this method is a primitive double (not Double).
     Therefore, if your lambda expression for this function returns a Double, it will automatically be converted into a
     double because of auto-unboxing. However, if your expression returns a null, a NullPointerException will be thrown.
     */
    @Test(expected = NullPointerException.class)
    public void testDoubleSupplier() {
        class Book {
            private Double db;
            private String title;

            private Book(Double db, String title) {
                this.db = db;
                this.title = title;
            }

            private Double getDb() {
                return db;
            }
        }

        Book book = new Book(null, "JQPV 8up810 in 24hr");
        DoubleSupplier ds = book::getDb;
        logger.info("testDoubleSupplier {}", ds.getAsDouble());
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
