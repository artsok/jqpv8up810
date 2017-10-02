package DateTimeApi;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public class OCPBookExampleTest {


    @Test
    public void testDefaultZone() {
        log.info("Текущая зона '{}'", ZoneId.systemDefault());
    }

    @Test
    public void testChronoUni() {
        LocalTime lc1 = LocalTime.of(20,00);
        LocalTime lc2 = LocalTime.of(21, 10);
        log.info("Сколько времени между lc1 и lc2 '{}' в минутах", ChronoUnit.MINUTES.between(lc1, lc2));
        log.info("Сколько времени между lc1 и lc2 '{}' в часах", ChronoUnit.HOURS.between(lc1, lc2));
    }

    @Test
    public void testInstant() throws InterruptedException {
        Instant is = Instant.now();
        TimeUnit.SECONDS.sleep(2L);
        Instant is2 = Instant.now();
        log.info("Сколько времени между is и is2 '{}' в секундах", Duration.between(is, is2));
        log.info("Сколько времени между is и is2 '{}' в миллисекундах", Duration.between(is, is2).toMillis());
    }

}