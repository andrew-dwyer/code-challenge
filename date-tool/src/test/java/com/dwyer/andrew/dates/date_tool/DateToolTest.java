package com.dwyer.andrew.dates.date_tool;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

public class DateToolTest {
   DateTime startDate;
   DateTime endDate;

   @Before
   public void setupNormalStartDates() {
      startDate = new DateTime(2000, 1, 1, 0, 0);
      endDate = new DateTime(2000, 1, 5, 0, 0);
   }

   @Test
   public void testBetweenDays() {

      DateTool dateTool = new DateTool(startDate, endDate);

      // testing normal operation
      assertEquals(4, dateTool.calcDaysDifference());

      // testing negative dates
      dateTool = new DateTool(endDate, startDate);
      assertEquals(-4, dateTool.calcDaysDifference());

      // testing compare same date
      dateTool = new DateTool(startDate, startDate);
      assertEquals(0, dateTool.calcDaysDifference());

      // Testing edge cases
      endDate = new DateTime(2000, 1, 5, 23, 59);
      dateTool = new DateTool(startDate, endDate);
      assertEquals(4, dateTool.calcDaysDifference());

   }

   /**
    * Test testBetweenWeekdays for dates within a single working week.
    */
   @Test
   public void testBetweenWeekdaysInsideWeek() {
      startDate = new DateTime(2000, 1, 3, 0, 0);
      endDate = new DateTime(2000, 1, 7, 0, 0);
      DateTool dateTool = new DateTool(startDate, endDate);
      assertEquals(4, dateTool.calcWeekdaysDifference());
   }

   /**
    * Test testBetweenWeekdays for dates across weeks, starting and ending on a
    * weekday.
    */
   @Test
   public void testBetweenWeekdaysAcrossWeeksToFromWeekdays() {
      startDate = new DateTime(2000, 1, 3, 0, 0);
      endDate = new DateTime(2000, 1, 10, 0, 0);
      DateTool dateTool = new DateTool(startDate, endDate);
      assertEquals(5, dateTool.calcWeekdaysDifference());

      endDate = new DateTime(2000, 1, 17, 0, 0);
      dateTool = new DateTool(startDate, endDate);
      assertEquals(10, dateTool.calcWeekdaysDifference());
   }

   /**
    * Test testBetweenWeekdays for dates across weeks, starting and/or ending on
    * a weekend.
    */
   @Test
   public void testBetweenWeekdaysAcrossWeeksToFromWeekends() {
      // date ending on weekend across 1 week
      startDate = new DateTime(2000, 1, 3, 0, 0);
      endDate = new DateTime(2000, 1, 9, 0, 0);
      DateTool dateTool = new DateTool(startDate, endDate);
      assertEquals(5, dateTool.calcWeekdaysDifference());

      // date ending on weekend across 2 weeks
      endDate = new DateTime(2000, 1, 15, 0, 0);
      dateTool = new DateTool(startDate, endDate);
      assertEquals(10, dateTool.calcWeekdaysDifference());

      // date starting on weekend across 1 week
      startDate = new DateTime(2000, 1, 1, 0, 0);
      endDate = new DateTime(2000, 1, 5, 0, 0);
      dateTool = new DateTool(startDate, endDate);
      assertEquals(2, dateTool.calcWeekdaysDifference());

      // date starting on weekend across 3 weeks
      startDate = new DateTime(2000, 1, 1, 0, 0);
      endDate = new DateTime(2000, 1, 20, 0, 0);
      dateTool = new DateTool(startDate, endDate);
      assertEquals(13, dateTool.calcWeekdaysDifference());
   }

   /**
    * Test testBetweenWeekdays for dates exceeding months and years.
    */
   @Test
   public void testBetweenWeekdaysMonthsYears() {
      // test across months
      startDate = new DateTime(2000, 1, 3, 0, 0);
      endDate = new DateTime(2000, 5, 9, 0, 0);
      DateTool dateTool = new DateTool(startDate, endDate);
      assertEquals(91, dateTool.calcWeekdaysDifference());

      // test across years
      startDate = new DateTime(2001, 1, 1, 0, 0);
      endDate = new DateTime(2002, 1, 1, 0, 0);
      dateTool = new DateTool(startDate, endDate);
      assertEquals(261, dateTool.calcWeekdaysDifference());
   }

   /**
    * Test testBetweenWeekdays for dates spanning leap years.
    */
   @Test
   public void testBetweenWeekdaysLeapYears() {
      startDate = new DateTime(2000, 1, 1, 0, 0);
      endDate = new DateTime(2001, 1, 1, 0, 0);
      DateTool dateTool = new DateTool(startDate, endDate);
      assertEquals(260, dateTool.calcWeekdaysDifference());
   }

   /**
    * Test testBetweenWeekdays for dates in reverse order.
    */
   @Test
   public void testBetweenWeekdaysReverse() {
      startDate = new DateTime(2000, 1, 3, 0, 0);
      endDate = new DateTime(2000, 1, 10, 0, 0);
      DateTool dateTool = new DateTool(endDate, startDate);
      assertEquals(-5, dateTool.calcWeekdaysDifference());
   }

   /**
    * Test testBetweenWeekdays for dates in reverse order.
    */
   @Test
   public void testBetweenWeekdaysSameDay() {
      DateTool dateTool = new DateTool(startDate, startDate);
      assertEquals(0, dateTool.calcWeekdaysDifference());
   }

}
