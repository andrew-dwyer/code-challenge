package com.dwyer.andrew.dates.date_tool;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

/**
 * DateTool Test class.
 */
public class DateToolTest {

   /** The default test start date. */
   DateTime startDate;

   /** The default test end date. */
   DateTime endDate;

   /**
    * Setup normal start dates.
    */
   @Before
   public void setupNormalStartDates() {
      startDate = new DateTime(2000, 1, 1, 0, 0);
      endDate = new DateTime(2000, 1, 5, 0, 0);
   }

   /**
    * Test the method "calcDaysDifference". These tests do not need to be
    * exhaustive as the entire operation is implemented by Joda Time.
    */
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
    * Test calcWeekdaysDifference for dates within a single working week.
    */
   @Test
   public void testBetweenWeekdaysInsideWeek() {
      startDate = new DateTime(2000, 1, 3, 0, 0);
      endDate = new DateTime(2000, 1, 7, 0, 0);
      DateTool dateTool = new DateTool(startDate, endDate);
      assertEquals(4, dateTool.calcWeekdaysDifference());
   }

   /**
    * Test calcWeekdaysDifference for dates across weeks, starting and ending on a
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
    * Test calcWeekdaysDifference for dates across weeks, starting and/or ending on
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
    * Test calcWeekdaysDifference for dates exceeding months and years.
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
    * Test calcWeekdaysDifference for dates spanning leap years.
    */
   @Test
   public void testBetweenWeekdaysLeapYears() {
      startDate = new DateTime(2000, 1, 1, 0, 0);
      endDate = new DateTime(2001, 1, 1, 0, 0);
      DateTool dateTool = new DateTool(startDate, endDate);
      assertEquals(260, dateTool.calcWeekdaysDifference());
   }

   /**
    * Test calcWeekdaysDifference for dates in reverse order.
    */
   @Test
   public void testBetweenWeekdaysReverse() {
      startDate = new DateTime(2000, 1, 3, 0, 0);
      endDate = new DateTime(2000, 1, 10, 0, 0);
      DateTool dateTool = new DateTool(endDate, startDate);
      assertEquals(-5, dateTool.calcWeekdaysDifference());
   }

   /**
    * Test calcWeekdaysDifference for dates in reverse order.
    */
   @Test
   public void testBetweenWeekdaysSameDay() {
      DateTool dateTool = new DateTool(startDate, startDate);
      assertEquals(0, dateTool.calcWeekdaysDifference());
   }

   /**
    * Test edge cases of calcWeekdaysDifference. Hours and minutes should be ignored
    */
   @Test
   public void testBetweenWeekdaysEdgeCases() {
      startDate = new DateTime(2000, 1, 3, 23, 0);
      endDate = new DateTime(2000, 1, 10, 0, 0);
      DateTool dateTool = new DateTool(startDate, endDate);
      assertEquals(5, dateTool.calcWeekdaysDifference());
   }

   /**
    * Test the method "calcCompleteWeeksDifference".
    */
   @Test
   public void testCalcCompleteWeeksDiff() {

      // 6 day week
      startDate = new DateTime(2000, 1, 3, 0, 0);
      endDate = new DateTime(2000, 1, 9, 0, 0);
      DateTool dateTool = new DateTool(startDate, endDate);
      assertEquals(0, dateTool.calcCompleteWeeksDifference());

      // 7 day week
      endDate = new DateTime(2000, 1, 10, 0, 0);
      dateTool = new DateTool(startDate, endDate);
      assertEquals(1, dateTool.calcCompleteWeeksDifference());

      // 7 day week
      endDate = new DateTime(2000, 1, 26, 0, 0);
      dateTool = new DateTool(startDate, endDate);
      assertEquals(3, dateTool.calcCompleteWeeksDifference());
   }

   /**
    * Test the method "calcCompleteWeeksDifference" with reversed dates.
    */
   @Test
   public void testCalcCompleteWeeksDiffReverseOrder() {
      startDate = new DateTime(2000, 1, 3, 0, 0);
      endDate = new DateTime(2000, 1, 10, 0, 0);
      DateTool dateTool = new DateTool(endDate, startDate);
      assertEquals(-1, dateTool.calcCompleteWeeksDifference());
   }

   /**
    * Test the method "calcCompleteWeeksDifference" with the same dates.
    */
   @Test
   public void testCalcCompleteWeeksDiffSameDates() {
      DateTool dateTool = new DateTool(endDate, startDate);
      assertEquals(0, dateTool.calcCompleteWeeksDifference());
   }

   /**
    * Test the method "calcCompleteWeeksDifference" with the same dates.
    */
   @Test
   public void testCalcCompleteWeeksDiffEdgeCase() {
      // 1 minute less than a complete week
      startDate = new DateTime(2000, 1, 3, 0, 1);
      endDate = new DateTime(2000, 1, 10, 0, 0);
      DateTool dateTool = new DateTool(startDate, endDate);
      assertEquals(0, dateTool.calcCompleteWeeksDifference());

      // 1 minute more than a complete week
      startDate = new DateTime(2000, 1, 3, 0, 0);
      endDate = new DateTime(2000, 1, 10, 0, 1);
      dateTool = new DateTool(startDate, endDate);
      assertEquals(1, dateTool.calcCompleteWeeksDifference());
   }

}
