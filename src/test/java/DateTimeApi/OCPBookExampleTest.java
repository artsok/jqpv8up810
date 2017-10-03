package DateTimeApi;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.*;
import java.time.temporal.ChronoUnit;
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

    @Test
    public void testZoneDateTime() {
        LocalDateTime ldt = LocalDateTime.of(2017, Month.DECEMBER, 1, 12, 00);
        ZoneId id = ZoneId.of("US/Eastern");
        ZonedDateTime zonedDateTime = ZonedDateTime.of(ldt, id);
        Instant instant = zonedDateTime.toInstant();
        log.info("В формате zoneDateTime '{}'",zonedDateTime);
        log.info("В формате instant '{}'", instant);
    }

    @Test
    public void testUseCaseJavaTime() {
        LocalDate d1 = LocalDate.of(2015, Month.JANUARY, 31);
        LocalDateTime d2 = LocalDateTime.of(2015, Month.JANUARY, 31, 10, 56);
        LocalDateTime d3 = LocalDateTime.parse("2015-01-02T17:13:50"); //Note that this will throw a  format.DateTimeParseException если в строке ввода отсутствует компонент времени T17:13:50
        LocalDate d4 = LocalDate.parse("2015-01-02"); //Note that this will throw a  format.DateTimeParseException if the input string contains the time component
        LocalTime d5 = LocalTime.parse("02:13:59.985"); //Note that this will throw a  format.DateTimeParseException if the input string contains the Date component
    }


}
