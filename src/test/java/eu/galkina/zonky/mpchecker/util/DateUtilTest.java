package eu.galkina.zonky.mpchecker.util;

import java.time.LocalDateTime;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateUtilTest {

    @Test
    public void testToISO8601Conversion() {
        LocalDateTime date = LocalDateTime.of(2018,7,23,18,35, 43);
        String iso8601FormatDate = DateUtil.toISO8601(date);
        assertEquals("2018-07-23T18:35:43", iso8601FormatDate);
    }
}
