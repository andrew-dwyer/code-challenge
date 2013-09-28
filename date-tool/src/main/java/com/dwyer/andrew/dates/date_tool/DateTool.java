/**
 *
 */
package com.dwyer.andrew.dates.date_tool;

import org.joda.time.DateTime;
import org.joda.time.Days;

/**
 * The DateTool class compares two supplied DateTime instances.
 *
 * @author dwyera
 */
public class DateTool {

   /** The start date. */
   private final DateTime startDate;

   /** The end date. */
   private final DateTime endDate;

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
    * Calculate the difference in weekdays between the two dates.
    *
    * @return the number of weekdays difference
    */
   public int calcWeekdaysDifference() {

      throw new UnsupportedOperationException();
   }

}
