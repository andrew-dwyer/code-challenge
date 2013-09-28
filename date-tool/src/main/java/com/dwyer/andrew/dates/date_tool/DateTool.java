/**
 * 
 */
package com.dwyer.andrew.dates.date_tool;

import org.joda.time.DateTime;


// TODO: Auto-generated Javadoc
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
	 * Instantiates a new date tool.
	 * 
	 * @param startDate
	 *            the start date
	 * @param endDate
	 *            the end date
	 */
	public DateTool(DateTime startDate, DateTime endDate) {

		this.startDate = startDate;
		this.endDate = endDate;
	}

	/**
	 * Calculate the difference in days between the two dates.
	 * 
	 * @return the date time
	 */
	public int calcDaysDifference() {
		throw new UnsupportedOperationException();
	}

}
