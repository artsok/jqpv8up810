package DateTimeApi;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

@Slf4j
public class OCPBookExampleTest {

    @Test
    public void testDefaultZone() {
        log.info("Текущая зона '{}'", ZoneId.systemDefault());
    }

    @Test
    public void testChronoUnit() {
        LocalTime lc1 = LocalTime.of(20,00);
        LocalTime lc2 = LocalTime.of(21, 10);
        log.info("Сколько времени между lc1 и lc2 '{}' в минутах", ChronoUnit.MINUTES.between(lc1, lc2));
        log.info("Сколько времени между lc1 и lc2 '{}' в часах", ChronoUnit.HOURS.between(lc1, lc2));
    }

}
