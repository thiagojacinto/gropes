package br.unit.pe.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConverterData {

	public static String paraString(String formato,Date date) {
		
		 // Converts the string
        // format to date object
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
  
        // Convert the date into a
        // string using format() method
        String dateToString = df.format(date);
  
        // Return the result
        return (dateToString);
	}
}
