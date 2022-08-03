package com.yzb.gateway;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * ClassName: CreateGatewayDateTime
 * Description:
 * date: 2021/12/23 14:26
 *
 * @author ZhenBang-Yi
 * @version 1.0
 * @since JDK 1.8
 */
public class CreateGatewayDateTime {
    public static void main(String[] args) {
        //DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //ZoneId zoneId = ZoneId.systemDefault();
        //LocalDateTime parse = LocalDateTime.parse("2088-01-18 23:59:59", dateTimeFormatter);
        //ZonedDateTime of = ZonedDateTime.of(parse, zoneId);
        //System.out.println(of);

        ArrayList<String> strings = new ArrayList<>();
        strings.add("A");
        strings.add("B");
        strings.add("C");
        strings.add("D");


        ArrayList<String> strings2 = new ArrayList<>();
        strings2.add("A");
        strings2.add("D");

        System.out.println("执行");
        List<String> collect = strings.stream().filter(e -> strings2.stream().noneMatch(o -> Objects.equals(o, e))).collect(Collectors.toList());

        collect.forEach(System.out::println);
    }
}
