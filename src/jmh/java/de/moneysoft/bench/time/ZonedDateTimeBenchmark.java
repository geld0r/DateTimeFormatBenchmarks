package de.moneysoft.bench.time;

import org.apache.commons.lang3.time.FastDateFormat;
import org.joda.time.format.DateTimeFormat;
import org.openjdk.jmh.annotations.Benchmark;

import java.text.ParseException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A benchmark to evaluate the fastest way to get from a timestamp (with zone) to a ZonedDateTime (single threaded case)
 *
 * @author gelder
 */
public class ZonedDateTimeBenchmark
{
	private static final String TIMESTAMP = "2016-04-14T22:37:55.315+0200";
	private static final String PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(PATTERN);
	private static final FastDateFormat FAST_DATE_FORMAT = FastDateFormat.getInstance(PATTERN);
	private static final org.joda.time.format.DateTimeFormatter JODA_DATE_TIME_FORMATTER = DateTimeFormat.forPattern(PATTERN);

	@Benchmark
	public ZonedDateTime zonedDateTimeParse()
	{
		return ZonedDateTime.parse(TIMESTAMP, DATE_TIME_FORMATTER);
	}

	@Benchmark
	public ZonedDateTime fastDateFormat() throws ParseException
	{
		return ZonedDateTime.from(FAST_DATE_FORMAT.parse(TIMESTAMP).toInstant().atZone(ZoneId.systemDefault()));
	}

	@Benchmark
	public ZonedDateTime jodaDateTimeFormatter() throws ParseException
	{
		return ZonedDateTime.from(Instant.ofEpochMilli(JODA_DATE_TIME_FORMATTER.parseDateTime(TIMESTAMP).getMillis()).atZone(ZoneId.systemDefault()));
	}
}
