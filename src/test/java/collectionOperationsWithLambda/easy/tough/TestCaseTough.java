package collectionOperationsWithLambda.easy.tough;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class TestCaseTough {

    private static Logger logger = LoggerFactory.getLogger(TestCaseTough.class);

    @Test
    public void testCollectorsAveragingDouble() {
        List<Item> items = Arrays.asList(
                new Item("Pen", "Stationery", 3.0),
                new Item("Pencil", "Stationery", 2.0),
                new Item("Eraser", "Stationery", 1.0),
                new Item("Milk", "Food", 2.0),
                new Item("Eggs", "Food", 3.0));
        ToDoubleFunction<Item> price = Item::getPrice;

        items.stream().collect(Collectors.groupingBy(Item::getCategory))
                .forEach((a, b) -> {
                            double av = b.stream().collect(Collectors.averagingDouble(price));
                            logger.info("testCollectorsAveragingDouble a='{}', av = '{}'", a, av);
                        }
                );
    }

    @Data
    @AllArgsConstructor
    class Item {
        private String name;
        private String category;
        private double price;
    }

}

