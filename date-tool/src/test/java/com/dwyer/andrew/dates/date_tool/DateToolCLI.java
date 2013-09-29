package com.dwyer.andrew.dates.date_tool;

import java.io.IOException;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

public class DateToolCLI {

   private final static String dateTimeDescription = "Argument must be an ISO date time string with optional time E.g. 1997-07-16T19:20:30+01:00";
   private static DateTool dateTool;
   private static HelpFormatter formatter;
   private static Options options;

   @SuppressWarnings("static-access")
   public static void main(String[] args) throws IOException {
      // create the command line parser
      CommandLineParser parser = new BasicParser();

      // create the Options
      options = new Options();

      options.addOption(OptionBuilder
            .withDescription(
                  "The start date to search from. " + dateTimeDescription)
            .isRequired().withLongOpt("startDate").hasArg()
            .withArgName("startDate").create("x"));

      options.addOption(OptionBuilder
            .withDescription(
                  "The end date to search to. " + dateTimeDescription)
            .isRequired().withLongOpt("endDate").hasArg()
            .withArgName("endDate").create("y"));

      options.addOption("h", "help", false, "Prints this Help");
      options.addOption("a", "daysBetween", false,
            "Calculates the number of days between the two dates");

      options.addOption("b", "weekdaysBetween", false,
            "Calculates the number of weekdays between the two dates");

      options.addOption("c", "weeksBetween", false,
            "Calculates the number of complete weeks between the two dates");

      formatter = new HelpFormatter();

      try {

         // parse the command line arguments
         CommandLine line = parser.parse(options, args);

         if (line.hasOption("help")) {
            printHelp();
            return;
         }

         DateTool dateTool = buildDateTool(line);

         if (line.hasOption("daysBetween")) {
            System.out.println("The number of days between the two dates is "
                  + dateTool.calcDaysDifference());
         }

         if (line.hasOption("weekdaysBetween")) {
            System.out
                  .println("The number of weekdays between the two dates is "
                        + dateTool.calcWeekdaysDifference());
         }

         if (line.hasOption("weeksBetween")) {
            System.out
                  .println("The number of complete weeks between the two dates is "
                        + dateTool.calcCompleteWeeksDifference());
         }

      } catch (ParseException exp) {
         System.out.println(exp.getMessage());
         printHelp();
      } catch (IllegalArgumentException iae) {
         System.out
               .println("Your dates are formatted incorrectly.  Please use ISO date format");
         printDateFormat();
      }

   }

   private static DateTime parseDate(String dateString)
         throws java.lang.IllegalArgumentException {

      return ISODateTimeFormat.dateOptionalTimeParser().parseDateTime(
            dateString);
   }

   private static void printHelp() {
      formatter.printHelp("DateTool", options, true);
   }

   private static void printDateFormat() {
      String formatHelp = " date-opt-time     = date-element ['T' [time-element] [offset]]\r\n"
            + " date-element      = std-date-element | ord-date-element | week-date-element\r\n"
            + " std-date-element  = yyyy ['-' MM ['-' dd]]\r\n"
            + " ord-date-element  = yyyy ['-' DDD]\r\n"
            + " week-date-element = xxxx '-W' ww ['-' e]\r\n"
            + " time-element      = HH [minute-element] | [fraction]\r\n"
            + " minute-element    = ':' mm [second-element] | [fraction]\r\n"
            + " second-element    = ':' ss [fraction]\r\n"
            + " fraction          = ('.' | ',') digit+\r\n";
      System.out.println(formatHelp);

   }

   private static DateTool buildDateTool(CommandLine line) {
      DateTime startDate = parseDate(line.getOptionValue("startDate"));
      DateTime endDate = parseDate(line.getOptionValue("endDate"));

      return new DateTool(startDate, endDate);

   }

}
