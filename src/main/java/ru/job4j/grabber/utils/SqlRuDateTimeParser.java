package ru.job4j.grabber.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class SqlRuDateTimeParser implements DateTimeParser {
    Map<String, Integer> months = new HashMap<>();
    {
        months.put("янв", 1);
        months.put("фев", 2);
        months.put("мар", 3);
        months.put("апр", 4);
        months.put("май", 5);
        months.put("июн", 6);
        months.put("июл", 7);
        months.put("авг", 8);
        months.put("сен", 9);
        months.put("окт", 10);
        months.put("ноя", 11);
        months.put("дек", 12);
    }

    public LocalDateTime parse(String parse) {
        LocalDate localdate;
        String time = parse.substring(parse.length() - 5);
        if (parse.contains("вчера")) {
            localdate = LocalDate.now().minusDays(1);
        } else if (parse.contains("сегодня")) {
            localdate = LocalDate.now();
        } else {
            int year = Integer.parseInt("20" + parse.substring(parse.length() - 9, parse.length() - 7));
            int month = months.get(parse.split(" ")[1].split(" ")[0]);
            localdate = LocalDate.of(year, month, Integer.parseInt(parse.substring(0, 1)));
        }
        return LocalDateTime.of(localdate, LocalTime.parse(time));
    }
}