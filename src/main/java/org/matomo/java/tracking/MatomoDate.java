/*
 * Matomo Java Tracker
 *
 * @link https://github.com/matomo/matomo-java-tracker
 * @license https://github.com/matomo/matomo-java-tracker/blob/master/LICENSE BSD-3 Clause
 */
package org.matomo.java.tracking;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * A datetime object that will return the datetime in the format {@code yyyy-MM-dd hh:mm:ss}.
 *
 * @author brettcsorba
 */
public class MatomoDate {
  private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

  private ZonedDateTime zonedDateTime;

  /**
   * Allocates a Date object and initializes it so that it represents the time
   * at which it was allocated, measured to the nearest millisecond.
   */
  public MatomoDate() {
    zonedDateTime = ZonedDateTime.now(ZoneOffset.UTC);
  }

  /**
   * Allocates a Date object and initializes it to represent the specified number
   * of milliseconds since the standard base time known as "the epoch", namely
   * January 1, 1970, 00:00:00 GMT.
   *
   * @param epochMilli the milliseconds since January 1, 1970, 00:00:00 GMT.
   */
  public MatomoDate(long epochMilli) {
    zonedDateTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(epochMilli), ZoneOffset.UTC);
  }

  /**
   * Sets the time zone of the String that will be returned by {@link #toString()}.
   * Defaults to UTC.
   *
   * @param zone the TimeZone to set
   */
  public void setTimeZone(ZoneId zone) {
    zonedDateTime = zonedDateTime.withZoneSameInstant(zone);
  }

  /**
   * Converts this MatomoDate object to a String of the form:<br>
   * <br>
   * {@code yyyy-MM-dd hh:mm:ss}.
   *
   * @return a string representation of this MatomoDate
   */
  @Override
  public String toString() {
    return DATE_TIME_FORMATTER.format(zonedDateTime);
  }

  /**
   * Converts this datetime to the number of milliseconds from the epoch
   * of 1970-01-01T00:00:00Z.
   *
   * @return the number of milliseconds since the epoch of 1970-01-01T00:00:00Z
   * @throws ArithmeticException if numeric overflow occurs
   */
  public long getTime() {
    return zonedDateTime.toInstant().toEpochMilli();
  }
}
