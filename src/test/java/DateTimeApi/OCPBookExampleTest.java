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
        LocalTime lc1 = LocalTime.of(20, 00);
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
        log.info("В формате zoneDateTime '{}'", zonedDateTime);
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

    /**
     * There are two equivalent ways of using this method.
     * The first is to invoke this method. The second is to use TemporalUnit.between(Temporal, Temporal):    
     *
     * // these two lines are equivalent    
     * amount = start.until(end, ChronoUnit.MINUTES);    
     * amount = ChronoUnit.MINUTES.between(start, end);
     */
    @Test
    public void testIsAfterExample() {
        LocalTime now = LocalTime.of(9, 30);
        LocalTime gameStart = LocalTime.of(10, 15);
        long timeConsumed = 0;
        long timeToStart = 0;
        if (now.isAfter(gameStart)) {
            timeConsumed = gameStart.until(now, ChronoUnit.HOURS);
        } else {
            timeToStart = now.until(gameStart, ChronoUnit.HOURS);
        }
        log.info("timeToStart = '{}', timeConsumed = '{}'", timeToStart, timeConsumed);
    }

    @Test
    public void testDayLightSaving() {
        LocalDate date = LocalDate.of(2016, Month.MARCH, 13);
        LocalTime time = LocalTime.of(1, 30);
        ZoneId zone = ZoneId.of("US/Eastern");
        ZonedDateTime dateTime = ZonedDateTime.of(date, time, zone);
        log.info("dateTime '{}'", dateTime);
        dateTime = dateTime.plusHours(1);
        log.info("dateTime '{}'", dateTime);
    }


}
