package javacollectionandstreamswithlambdas.easy;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class TestCaseEasy {

    private static Logger logger = LoggerFactory.getLogger(lambdacookbook.easy.TestCaseEasy.class);

    /**
     *  filter method removes all the elements for which the given condition (i.e. b.getTitle().startsWith("F"))
     *  returns false from the stream. These elements are not removed from the underlying list but only from the stream.
     *  Therefore, when you create a stream from the list again, it will have all the elements from the list.
     *  Since the setPrice operation changes the Book object contained in the list,
     *  the updated value is shown the second time when you go through the list.
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
        List<Book> books = Arrays.asList(
                new Book("Freedom at Midnight", 5.0),
                new Book("Gone with the wind", 5.0),
                new Book("Midnight Cowboy", 15.0) );

        books.stream().filter(b -> b.title.startsWith("F")).forEach(b -> b.setPrice(10.0));
        books.stream().forEach(b -> logger.info("Результат '{}', '{}'", b.price, b.title));
    }



}
