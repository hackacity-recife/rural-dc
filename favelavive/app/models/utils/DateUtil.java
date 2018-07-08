package models.utils;

import com.google.common.base.Strings;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Classe utilitária para ações relacionadas a entidades de data.
 */
public final class DateUtil {
	public static final String TIMEZONE = "America/Sao_Paulo";
	
	/**
     * Formato padrão de armazenamento de data no banco de dados.
     */
    public static final String DEFAULT_FULL_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * Formato padrão de armazenamento de data no banco de dados.
     */
    public static final String DEFAULT_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * Formato padrão de armazenamento de data no banco de dados.
     */
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

    /**
     * Formato padrão de exibição e recuperação de data nas views.
     */
    public static final String DEFAULT_VIEW_DATE_PATTERN = "dd/MM/yyyy";

    /**
     * Formato padrão de exibição para utilizasa no JavaScript
     */
    public static final String DEFAULT_VIEW_DATE_JS_PATTERN = "dd/mm/yyyy";

    /**
     * Padrão de data brasileira.
     */
    public static final String DATE_PATTERN_BR = "dd/MM/yyyy";

    /**
     * Padrão de data brasileira.
     */
    public static final String DATE_PATTERN_BR_2 = "dd/MM/yy";

    /**
     * Padrão customizado da data brasileira.
     */
    public static final String PATTERN_EEE_DD_MM = "EEE - dd/MM";

    /**
     * Padrão de hora brasileira.
     */
    public static final String HOUR_PATTERN_BR = "HH:mm:ss";
    public static final String MINIFIED_HOUR_PATTERN_BR = "HH";

    /**
     * Padrão de data/hora brasileira.
     */
    public static final String DATE_HOUR_PATTERN_BR = "dd/MM/yyyy HH:mm:ss";

    /**
     * Padrão de data/hora brasileira, sem os segundos.
     */
    public static final String DATE_HOUR_WITHOUT_SECONDS_PATTERN_BR = "dd/MM/yyyy HH:mm";
    public static final String PATTERN_EEEEE = "EEEEE";
    public static final String DEFAULT_TIME_PATTERN = "HH:mm";

    /**
     * Responsável por transformar uma string em padrão para uma entidade Date.
     * @param dateHour
     * @return
     */
    public static DateTime parse(String dateHour, String pattern) {
        if (dateHour == null || dateHour.isEmpty()) {
            return null;
        }

        if (pattern == null || pattern.isEmpty()) {
            pattern = DEFAULT_DATE_TIME_PATTERN;
        }

        return DateTime.parse(dateHour, DateTimeFormat.forPattern(pattern));
    }
    
    public static DateTime parse(String dateHour) {
    	String pattern;
    	if(dateHour.length() == 10) { // yyyy-mm-dd
        	pattern = DEFAULT_DATE_PATTERN;
        }else if(dateHour.length() > 19) { // with .SSS
        	pattern = DEFAULT_FULL_DATE_TIME_PATTERN;
        }else {
        	pattern = DEFAULT_DATE_TIME_PATTERN;
        }
		return parse(dateHour, pattern);
    }

    public static LocalDate parseDate(String date, String pattern) {
        if (date == null || date.isEmpty()) {
            return null;
        }

        if (pattern == null || pattern.isEmpty()) {
            pattern = DEFAULT_DATE_PATTERN;
        }

        return LocalDate.parse(date, DateTimeFormat.forPattern(pattern));
    }

    /**
     * Método responsável por ler uma classe Date e transforma-la em String no formato passado.
     * @param dateHour
     * @return formattedDate
     */
    public static String format(DateTime dateHour) {
        return format(dateHour, DATE_HOUR_WITHOUT_SECONDS_PATTERN_BR);
    }
    
    public static String format(Date dateHour) {
        return format(new DateTime(dateHour), DATE_HOUR_WITHOUT_SECONDS_PATTERN_BR);
    }

    /**
     * Método responsável por ler uma classe Date e transforma-la em String no formato passado.
     * @param dateHour
     * @param pattern
     * @return formattedDate
     */
    public static String format(DateTime dateHour, String pattern) {
        if (dateHour == null) {
            return "";
        }

        if (pattern == null || pattern.isEmpty()) {
            pattern = DEFAULT_DATE_TIME_PATTERN;
        }

        return dateHour.toString(pattern);
    }
    
    public static String format(Date dateHour, String pattern) {
        return format(new DateTime(dateHour), pattern);
    }

    public static String formatTime(LocalTime time, String pattern) {
        if (time == null) {
            return "";
        }

        if (pattern == null || pattern.isEmpty()) {
            pattern = DEFAULT_DATE_TIME_PATTERN;
        }

        return time.toString(pattern);
    }

    /**
     * Responsável por transformar uma string em padrão para uma entidade Date.
     * @param dateHour
     * @return
     * @throws ParseException
     */
    public static LocalDate parseLocalDate(String dateHour, String pattern) throws ParseException {
        if (dateHour == null || dateHour.isEmpty()) {
            return null;
        }

        if (pattern == null || pattern.isEmpty()) {
            pattern = DEFAULT_DATE_TIME_PATTERN;
        }

        return LocalDate.parse(dateHour, DateTimeFormat.forPattern(pattern));
    }

    /**
     * Método responsável por ler uma classe Date e transforma-la em String no formato passado.
     * @param dateHour
     * @param pattern
     * @return formattedDate
     */
    public static String format(LocalDate dateHour, String pattern) {
        if (dateHour == null) {
            return "";
        }

        if (pattern == null || pattern.isEmpty()) {
            pattern = DEFAULT_DATE_TIME_PATTERN;
        }

        return dateHour.toString(pattern);
    }

    public static String format(LocalTime time, String pattern) {
        if (time == null) {
            return "";
        }

        if (pattern == null || pattern.isEmpty()) {
            pattern = HOUR_PATTERN_BR;
        }

        return time.toString(pattern);
    }

    public static LocalTime parseLocalTime(String time, String pattern) {
        if (Strings.isNullOrEmpty(time)) {
            return null;
        }

        if (pattern == null || pattern.isEmpty()) {
            pattern = DEFAULT_TIME_PATTERN;
        }

        return LocalTime.parse(time, DateTimeFormat.forPattern(pattern));
    }
    
    public static boolean equals(Date a, Date b){
    	if(a == null || b == null) return false;
    	Calendar c1 = Calendar.getInstance();
    	Calendar c2 = Calendar.getInstance();
    	c1.setTime(a);
    	c2.setTime(b);
    	return c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH)
    		&& c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
    		&& c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR);
    }
    
    public static Date now(){
    	TimeZone GMT = TimeZone.getTimeZone(TIMEZONE);
    	Calendar c = Calendar.getInstance(GMT);
		return c.getTime();
    }
    
    public static Date today(){
    	TimeZone GMT = TimeZone.getTimeZone(TIMEZONE);
    	Calendar c = Calendar.getInstance(GMT);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
    }
    
    public static Date nextUtilDay(int margin){
    	TimeZone GMT = TimeZone.getTimeZone(TIMEZONE);
    	Calendar c = Calendar.getInstance(GMT);
    	c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
    	c.add(Calendar.DAY_OF_MONTH, margin);
    	
    	while (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
    		c.add(Calendar.DAY_OF_MONTH, 1);
    	}		
		return c.getTime();
    }
    
    public static int minutesTo(int horaInicio, int minutoInicio) {
    	TimeZone GMT = TimeZone.getTimeZone(TIMEZONE);
    	Calendar c = Calendar.getInstance(GMT);
		c.set(Calendar.HOUR_OF_DAY, horaInicio);
		c.set(Calendar.MINUTE, minutoInicio);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		
		Calendar c2 = Calendar.getInstance(GMT);    	
		if (c2.after(c)){
			c.add(Calendar.DAY_OF_MONTH, 1);
		}
    	
    	Date novo = c.getTime();
    	Date agora = c2.getTime();
    	return (int)((novo.getTime() - agora.getTime())/1000/60); //delay em minutos
	}
    
    public static int nextDay(int calendarDay) {
    	TimeZone GMT = TimeZone.getTimeZone(TIMEZONE);
    	Calendar c = Calendar.getInstance(GMT);
    	int days = 0;
    	while (c.get(Calendar.DAY_OF_WEEK) != calendarDay){
    		c.add(Calendar.DAY_OF_MONTH, 1);
    		days++;
    	}
    	return days;
	}
    
    public static boolean isContainsDate(Date init, Date fini, Date date) {
    	return init.compareTo(date) * fini.compareTo(date) <= 0;
	}

}