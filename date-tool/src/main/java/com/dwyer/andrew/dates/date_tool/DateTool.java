/**
 *
 */
package com.dwyer.andrew.dates.date_tool;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.Days;
import org.joda.time.Weeks;

/**
 * The DateTool class compares two supplied DateTime instances.
 *
 * @author dwyera
 */
public class DateTool {

   /** The start date. */
   private DateTime startDate;

   /** The end date. */
   private DateTime endDate;

   /**
    * Instantiates a new date tool. Note that dates supplied in reverse order
    * (endDate < startDate) will always result in negative results. E.g.
    * 22/1/2003 - 21/1/2003 = -1
    *
    * @param startDate
    *           the start date
    * @param endDate
    *           the end date
    */
   public DateTool(DateTime startDate, DateTime endDate) {

      this.startDate = startDate;
      this.endDate = endDate;
   }

   /**
    * Calculate the difference in days between the two dates.
    *
    * @return the number of days difference
    */
   public int calcDaysDifference() {

      return Days.daysBetween(startDate, endDate).getDays();

   }

   /**
    * Calculate the difference in weekdays between the two dates. Days are
    * treated as complete days regardless of the time in hours or minutes. TODO
    * confirm if this is the desired behavior.
    *
    * @return the number of weekdays difference
    */
   public int calcWeekdaysDifference() {

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
         return -count;
      }
      return count;
   }

   /**
    * Calculate the difference in complete weeks between the start and end date.
    *
    * @return the difference in complete weeks between the start and end date.
    */
   public int calcCompleteWeeksDifference() {
      return Weeks.weeksBetween(startDate, endDate).getWeeks();
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

}
