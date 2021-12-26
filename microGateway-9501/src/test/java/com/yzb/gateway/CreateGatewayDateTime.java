package com.yzb.gateway;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

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
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime parse = LocalDateTime.parse("2088-01-18 23:59:59", dateTimeFormatter);
        ZonedDateTime of = ZonedDateTime.of(parse, zoneId);
        System.out.println(of);
    }
}
