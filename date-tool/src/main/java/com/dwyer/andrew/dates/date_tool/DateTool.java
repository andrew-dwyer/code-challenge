package com.dwyer.andrew.dates.date_tool;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.Days;
import org.joda.time.Duration;
import org.joda.time.Weeks;

/**
 * The DateTool class compares two supplied DateTime instances.
 *
 * Assumptions: 1 - The method calcWeekdaysDifference treats input start and end
 * DateTimes as whole days. Part days are ignored. 2 - When returning durations
 * in different units, I have directly converted days into the appropriate
 * units, without taking into account the length of the year.
 *
 * @author dwyera
 */
public class DateTool {

   public enum ResultUnit {
      SECONDS, MINUTES, HOURS, DAYS, WEEKS, YEARS, DEFAULT
   }

   /** The start date. */
   private DateTime startDate;

   /** The end date. */
   private DateTime endDate;

   /** The units of return results */
   private ResultUnit resultUnit = ResultUnit.DEFAULT;

   /**
    * Instantiates a new date tool with a default result return value of DAYS
    *
    * @see DateTool(startDate, endDate, resultUnit)
    */
   public DateTool(DateTime startDate, DateTime endDate) {

      this.startDate = startDate;
      this.endDate = endDate;
   }

   /**
    * Instantiates a new date tool. Note that dates supplied in reverse order
    * (endDate < startDate) will always result in negative results. E.g.
    * 22/1/2003 - 21/1/2003 = -1
    *
    * @param startDate
    *           the start date
    * @param endDate
    *           the end date
    * @param resultUnit
    *           the unit that the result of any calculations should be returned
    *           in
    */
   public DateTool(DateTime startDate, DateTime endDate, ResultUnit resultUnit) {

      this.startDate = startDate;
      this.endDate = endDate;
      this.resultUnit = resultUnit;
   }

   /**
    * Calculate the difference in days between the two dates.
    *
    * @return the number of days difference
    */
   public long calcDaysDifference() {

      Days days = Days.daysBetween(startDate, endDate);

      if (resultUnit == ResultUnit.DEFAULT) {
         return days.getDays();
      } else {
         return periodToSetUnits(days.toStandardDuration());
      }
   }

   /**
    * Calculate the difference in weekdays between the two dates. Days are
    * treated as complete days regardless of the time in hours or minutes. TODO
    * confirm if this is the desired behavior.
    *
    * @return the number of weekdays difference
    */
   public long calcWeekdaysDifference() {

      int count = 0;
      boolean datesReversed = fixDateOrder();

      DateTime currentDay = startDate;

      // iterate through all days until endDate
      while (currentDay.isBefore(endDate)) {
         // if day is on weekend, skip ahead to Monday.
         if (currentDay.getDayOfWeek() == DateTimeConstants.SATURDAY
               || currentDay.getDayOfWeek() == DateTimeConstants.SUNDAY) {
            currentDay = currentDay.plusWeeks(1).withDayOfWeek(
                  DateTimeConstants.MONDAY);
         } else {
            currentDay = currentDay.plusDays(1);
            count++;
         }
      }

      /*
       * returns difference in reverse order if that was the way start and end
       * date were supplied
       */
      if (datesReversed) {
         count = -count;
      }

      Days days = Days.days(count);

      if (resultUnit == ResultUnit.DEFAULT) {
         return days.getDays();
      } else {
         return periodToSetUnits(days.toStandardDuration());
      }
   }

   /**
    * Calculate the difference in complete weeks between the start and end date.
    *
    * @return the difference in complete weeks between the start and end date.
    */
   public long calcCompleteWeeksDifference() {

      Weeks weeks = Weeks.weeksBetween(startDate, endDate);

      if (resultUnit == ResultUnit.DEFAULT) {
         return weeks.getWeeks();
      } else {
         return periodToSetUnits(weeks.toStandardDuration());
      }

   }

   /**
    * Converts a duration to the specified resultUnit
    *
    * @param duration
    * @return duration in specified unit @see resultUnit
    */
   private long periodToSetUnits(Duration duration) {
      switch (resultUnit) {
      case YEARS:
         return duration.toStandardDays().getDays() / 365;
      case WEEKS:
         return duration.toStandardDays().getDays() / 7;
      case DAYS:
         return duration.getStandardDays();
      case HOURS:
         return duration.getStandardHours();
      case MINUTES:
         return duration.getStandardMinutes();
      case SECONDS:
         return duration.getStandardSeconds();
      default:
         return duration.getStandardDays();
      }
   }

   private boolean fixDateOrder() {
      if (startDate.compareTo(endDate) > 0) {
         DateTime tempDate = startDate;
         startDate = endDate;
         endDate = tempDate;
         return true;
      }
      return false;
   }

   public DateTime getStartDate() {
      return startDate;
   }

   public void setStartDate(DateTime startDate) {
      this.startDate = startDate;
   }

   public DateTime getEndDate() {
      return endDate;
   }

   public void setEndDate(DateTime endDate) {
      this.endDate = endDate;
   }

   public ResultUnit getResultUnit() {
      return resultUnit;
   }

   public void setResultUnit(ResultUnit resultUnit) {
      this.resultUnit = resultUnit;
   }

}
