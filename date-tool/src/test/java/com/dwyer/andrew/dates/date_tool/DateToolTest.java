package com.dwyer.andrew.dates.date_tool;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.junit.Test;

public class DateToolTest {

	@Test
	public void testBetweenDays() {

		DateTime startDate = new DateTime(2000, 1, 1, 0, 0);
		DateTime endDate = new DateTime(2000, 1, 5, 0, 0);

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
		assertEquals(0, dateTool.calcDaysDifference());

	}

}
