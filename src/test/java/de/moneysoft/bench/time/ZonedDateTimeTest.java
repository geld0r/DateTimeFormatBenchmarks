package de.moneysoft.bench.time;

import org.apache.commons.lang3.time.FastDateFormat;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.junit.Test;

import java.text.ParseException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author gelder
 */
public class ZonedDateTimeTest
{
	private static final String TIMESTAMP = "2016-04-14T22:37:55.315+0200";
	private static final String PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(PATTERN);
	private static final FastDateFormat FAST_DATE_FORMAT = FastDateFormat.getInstance(PATTERN);
	private static final org.joda.time.format.DateTimeFormatter JODA_DATE_TIME_FORMATTER = DateTimeFormat.forPattern(PATTERN);

	@Test
	public void zonedDateTimeParse()
	{
		ZonedDateTime time = ZonedDateTime.parse(TIMESTAMP, DATE_TIME_FORMATTER);
		assertThat(DATE_TIME_FORMATTER.format(time)).isEqualTo(TIMESTAMP);
	}

	@Test
	public void fastDateFormat() throws ParseException
	{
		ZonedDateTime time = ZonedDateTime.from(FAST_DATE_FORMAT.parse(TIMESTAMP).toInstant().atZone(ZoneId.systemDefault()));
		assertThat(DATE_TIME_FORMATTER.format(time)).isEqualTo(TIMESTAMP);
	}

	@Test
	public void jodaDateTimeFormatter() throws ParseException
	{
		DateTime dateTime = JODA_DATE_TIME_FORMATTER.parseDateTime(TIMESTAMP);
		ZonedDateTime time = ZonedDateTime.from(Instant.ofEpochMilli(dateTime.getMillis()).atZone(ZoneId.systemDefault()));
		assertThat(DATE_TIME_FORMATTER.format(time)).isEqualTo(TIMESTAMP);
	}
}
