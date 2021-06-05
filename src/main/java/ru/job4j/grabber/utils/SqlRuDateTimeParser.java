package ru.job4j.grabber.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SqlRuDateTimeParser implements DateTimeParser {
    @Override
    public LocalDateTime parse(String parse) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yy, HH:mm");
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd MMM yy,");
        String time = parse.substring(parse.length() - 6);
        if (parse.length() == 15) {
            parse = "0" + parse;
        }
        if (parse.contains("вчера")) {
            parse = df.format(LocalDateTime.now().minusDays(1)) + time;
        }
        if (parse.contains("сегодня")) {
            parse = df.format(LocalDateTime.now()) + time;
        }
        if (parse.contains("май")) {
            parse = parse.substring(0, 3) + "мая" + parse.substring(6);
        } else if (!parse.contains(".")) {
            parse = parse.substring(0, 6) + ". " + parse.substring(7);
        }
        return LocalDateTime.parse(parse, dtf);
    }
}