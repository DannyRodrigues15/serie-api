package com.devvision.series.app.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.Calendar.*;

public class DateUtil {
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String PT_BR_DATETIME_FORMAT = "dd/MM/yyyy HH:mm:ss";
    public static final String PT_BR_DATE_FORMAT = "dd/MM/yyyy";
    public static final String PT_BR_TIME_FORMAT = "HH:mm:ss";
    
    public static Date now() {
        return new Date();
    }
    
    
    
    
    
    
    

    public static String format(Date date) throws Exception {
    	try {
    		return date != null ? defaultDateFormat().format(date) : null;
    	} catch (Exception e) {
			throw new Exception(e);
		}
    }

    public static Date format(String date) throws Exception {
    	try {
    		return date != null ? defaultDateFormat().parse(date) : null;
    	} catch (ParseException e) {
    		throw new Exception(e);
		}
    }
    
    public static SimpleDateFormat defaultDateFormat() {
    	return new SimpleDateFormat(DEFAULT_DATE_FORMAT);    	
    }

    public static SimpleDateFormat ptBrDatetimeFormat() {
    	return new SimpleDateFormat(PT_BR_DATETIME_FORMAT);
    }

    public static SimpleDateFormat ptBrDateFormat() {
    	return new SimpleDateFormat(PT_BR_DATE_FORMAT);
    }

    public static SimpleDateFormat ptBrTimeFormat() {
    	return new SimpleDateFormat(PT_BR_TIME_FORMAT);
    }

    public static SimpleDateFormat dateFormat(String format) {
    	return new SimpleDateFormat(format);
    }
    
    public static TimeZone getTimeZoneByAlias(String alias) {    	
    	TimeZone timeZone = TimeZone.getTimeZone(alias);    	
    	return timeZone;
    }

    public static Date add(Date date, int calendarField, int amount) {

    	if (date == null)
    		return null;

        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        cal.add(calendarField, amount);
        return cal.getTime();
    }

    public static Date addYear(int year, Date reference) {
    	return add(reference, YEAR, year);
    }

    public static Date addMonth(int month, Date reference) {
    	return add(reference, MONTH, month);
    }

    public static Date addDayOfMonth(int day, Date reference) {
    	return add(reference, DAY_OF_MONTH, day);
    }

    public static Date addHour(int hour, Date reference) {
    	return add(reference, HOUR_OF_DAY, hour);
    }

    public static Date addMinute(int min, Date reference) {
    	return add(reference, MINUTE, min);
    }

    public static Date addSecond(int sec, Date reference) {
    	return add(reference, SECOND, sec);
    }

    public static Date addMile(int mile, Date reference) {
    	return add(reference, MILLISECOND, mile);
    }

    public static int get(Date date, int calendarField) {

    	Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        return cal.get(calendarField);
    }

    public static int getYear(Date reference) {
    	return get(reference, YEAR);
    }

    public static int getMonth(Date reference) {
    	return get(reference, MONTH);
    }

    public static int getDayOfMonth(Date reference) {
    	return get(reference, DAY_OF_MONTH);
    }

    public static int getHour(Date reference) {
    	return get(reference, HOUR_OF_DAY);
    }

    public static int getMinute(Date reference) {
        return get(reference, MINUTE);
    }

    public static Date set(Date date, int calendarField, int value) {

    	if (date == null)
    		return null;

    	Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        cal.set(calendarField, value);
        return cal.getTime();
    }

    public static Date setYear(int year, Date reference) {
    	return set(reference, YEAR, year);
    }

    public static Date setMonth(int month, Date reference) {
    	return set(reference, MONTH, month);
    }

    public static Date setDayOfMonth(int day, Date reference) {
    	return set(reference, DAY_OF_MONTH, day);
    }

    public static Date setHour(int hour, Date reference) {
    	return set(reference, HOUR_OF_DAY, hour);
    }

    public static Date setMinute(int min, Date reference) {
    	return set(reference, MINUTE, min);
    }

    public static Date setSecond(int sec, Date reference) {
    	return set(reference, SECOND, sec);
    }

    public static Date setMile(int mile, Date reference) {
    	return set(reference, MILLISECOND, mile);
    }

    public static Date setStartOfDay(Date reference) {

    	if (reference == null)
    		return null;

    	reference = set(reference, HOUR_OF_DAY, 0);
    	reference = set(reference, MINUTE, 0);
    	reference = set(reference, SECOND, 0);
    	reference = set(reference, MILLISECOND, 0);
    	return reference;
    }

    public static Date addWorkingDays(Date date, int workdays) {

    	if (date == null)
    		return null;

    	if (workdays < 1)
            return date;

    	LocalDate localdate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        int addedDays = 0;
        while (addedDays < workdays) {
        	localdate = localdate.plusDays(1);
            if (!(localdate.getDayOfWeek() == DayOfWeek.SATURDAY || localdate.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                ++addedDays;
            }
        }

        return Date.from(localdate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Date subWorkingDays(Date date, int workdays) {

    	if (date == null)
    		return null;

    	if (workdays < 1)
            return date;

    	LocalDate localdate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        int addedDays = 0;
        while (addedDays < workdays) {
        	localdate = localdate.minusDays(1);
            if (!(localdate.getDayOfWeek() == DayOfWeek.SATURDAY || localdate.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                ++addedDays;
            }
        }

        return Date.from(localdate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Date nextWorkingDate(Date date) {

    	if (date == null)
    		return null;

    	LocalDate localdate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        while (localdate.getDayOfWeek() == DayOfWeek.SATURDAY || localdate.getDayOfWeek() == DayOfWeek.SUNDAY) {
        	localdate = localdate.plusDays(1);
        }

        return Date.from(localdate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Date today() {
        LocalDateTime ldt = LocalDate.now().atTime(0,0,0,0);
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date startOfDay() {
        LocalDateTime ldt = LocalDate.now().atTime(0,0,0,0);
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date startOfDay(Date date) {

    	if (date == null)
    		return null;

        LocalDateTime ldt = toLocalDate(date).atTime(0,0,0,0);
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date endOfDay() {
        LocalDateTime ldt = LocalDate.now().atTime(23,59,59,999);
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date endOfDay(Date date) {

    	if (date == null)
    		return null;

        LocalDateTime ldt = toLocalDate(date).atTime(23,59,59,999);
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date middleOfDay(Date date) {

        if (date == null)
            return null;

        LocalDateTime ldt = toLocalDate(date).atTime(12,00,00,000);
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate parseDateToLocalDate(Date date) {

    	if (date == null)
    		return null;

    	LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    	return localDate;
    }

    public static LocalDate toLocalDate(Date date) {

    	if (date == null)
    		return null;

    	LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    	return localDate;
    }

    public static Date fromLocalDate(LocalDate localDate) {

    	if (localDate == null)
    		return null;

    	return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static boolean isExpired(Date ref) {
    	return now().after(ref);
    }

    public static boolean isBetween(Date ref, Date start, Date end) {
    	return ref.after(start) && ref.before(end);
    }

    public static Date createDate(int day, int month, int year, int hour, int minute, int second) {
        LocalDateTime ldt = LocalDate.of(year, month, day).atTime(hour,minute,second,0);
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }

	public static int daysBetween(LocalDate start, LocalDate end) {
		return (int) DAYS.between(start.atStartOfDay(), end.atStartOfDay());
	}

	public static int daysBetween(Date start, Date end) {
		return daysBetween(toLocalDate(start), toLocalDate(end));
	}

	public static Date nextDueDate(Date ref, int dueDay) {

		LocalDate refLocalDate = ref.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate dueLocalDate = nextDueDate(refLocalDate, dueDay);
		return Date.from(dueLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public static LocalDate nextDueDate(LocalDate ref, int dueDay) {

		LocalDate date = LocalDate
				.from(ref)
				.withDayOfMonth(dueDay);

		if (ref.atStartOfDay().isAfter(date.atStartOfDay())) {
			date = date.plus(Period.ofMonths(1));
		}

		return date;
	}
}
