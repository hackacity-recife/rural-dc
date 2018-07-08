package models.injects;

import models.utils.DateUtil;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import play.data.format.Formatters;
import play.data.format.Formatters.SimpleFormatter;
import play.i18n.MessagesApi;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import java.text.ParseException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Singleton
public class DateFormattersProvider implements Provider<Formatters> {

    private final Formatters formatters;

    @Inject
    public DateFormattersProvider(MessagesApi messagesApi) {
        this.formatters = new Formatters(messagesApi);
        configure();
    }

    @Override
    public Formatters get() {
        return this.formatters;
    }

    private void configure() {
        this.formatters.register(LocalDate.class, new SimpleFormatter<LocalDate>() {

            private Pattern timePattern = Pattern.compile(
                    "(\\d{2})/(\\d{2})/(\\d{4})?"
            );

            @Override
            public LocalDate parse(String input, Locale l) throws ParseException {
                Matcher m = timePattern.matcher(input);
                if (!m.find()) throw new ParseException("Invalid Date", 0);
                int day = Integer.valueOf(m.group(1));
                int month = Integer.valueOf(m.group(2));
                int year = Integer.valueOf(m.group(3));
                return new LocalDate(year, month, day);
            }

            @Override
            public String print(LocalDate localTime, Locale l) {
                return localTime.toString("dd/MM/yyyy");
            }

        });
        this.formatters.register(LocalTime.class, new SimpleFormatter<LocalTime>() {

            private Pattern timePattern = Pattern.compile(
                    "(\\d{2}):(\\d{2}):(\\d{2})?"
            );

            @Override
            public LocalTime parse(String input, Locale l) throws ParseException {
                Matcher m = timePattern.matcher(input);
                if (!m.find()) throw new ParseException("Invalid Time", 0);
                int hour = Integer.valueOf(m.group(1));
                int minute = Integer.valueOf(m.group(2));
                int second = Integer.valueOf(m.group(3));
                return new LocalTime(hour, minute, second);
            }

            @Override
            public String print(LocalTime localTime, Locale l) {
                return localTime.toString(DateUtil.HOUR_PATTERN_BR);
            }

        });
    }

}