//$Id$
package com.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateDiff {
	public static void main(String[] args) {
		
		Date d1 = new Date(1998,06,22);
		Date d2 = new Date(2000,06,22);
		
		Period diff = Period.between(d1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
				d2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		System.out.println(diff.toTotalMonths());
		DateTimeFormatter obj = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		System.out.println(LocalDate.parse("22-06-1998",obj));
		System.out.println((LocalDate.parse("22-06-1998",obj)).getClass().getSimpleName());
		
	}
}
