package com.zhqn.demo4.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Copyright (C), 2015-2021, 易车
 * FileName: ConverterConfiguration
 * Author:   zhouquan3
 * Date:     2021/4/28 19:44
 * Description: 转换类配置
 *
 * @author zhouquan3
 */
@Configuration
public class DateTimeConfiguration {

    final static String NORMAL_DATE_PATTERN = "yyyy-MM-dd";

    final static String NORMAL_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * string to date
     * @return date
     */
    @Bean
    public DateConverter dateConverter () {
        return new DateConverter();
    }

    /**
     * string to localDate
     * @return localDate
     */
    @Bean
    public LocalDateConverter localDateConverter () {
        return new LocalDateConverter();
    }

    /**
     * string to LocalDateTime
     * @return LocalDateTime
     */
    @Bean
    public LocalDateTimeConverter localDateTimeConverter () {
        return new LocalDateTimeConverter();
    }

    @Bean
    public ObjectMapper initObjectMapper(){
        ObjectMapper objectMapper=new ObjectMapper();

        JavaTimeModule javaTimeModule=new JavaTimeModule();
        javaTimeModule.addDeserializer(LocalDateTime.class,new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(NORMAL_DATE_TIME_PATTERN)));
        javaTimeModule.addSerializer(LocalDateTime.class,new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(NORMAL_DATE_TIME_PATTERN)));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(NORMAL_DATE_PATTERN)));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(NORMAL_DATE_PATTERN)));
        javaTimeModule.addSerializer(Date.class, new DateSerializer(false, new SimpleDateFormat(NORMAL_DATE_TIME_PATTERN)));
        javaTimeModule.addDeserializer(Date.class, new DateDeserializers.DateDeserializer());
        objectMapper.registerModule(javaTimeModule);

        return objectMapper;
    }



    static class DateConverter implements Converter<String, Date> {

        @Override
        public Date convert(String source) {
            String pattern ;
            if (source.length() <= NORMAL_DATE_PATTERN.length()) {
                pattern = NORMAL_DATE_PATTERN;
            }else {
                pattern = NORMAL_DATE_TIME_PATTERN;
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            try {
                return dateFormat.parse(source);
            } catch (ParseException e) {
                throw new RuntimeException(source + " to date error!");
            }
        }
    }

    static class LocalDateConverter implements Converter<String, LocalDate> {

        @Override
        public LocalDate convert(String source) {
            return LocalDate.parse(source, DateTimeFormatter.ofPattern(NORMAL_DATE_PATTERN));
        }
    }

    static class LocalDateTimeConverter implements Converter<String, LocalDateTime> {

        @Override
        public LocalDateTime convert(String source) {
            return LocalDateTime.parse(source, DateTimeFormatter.ofPattern(NORMAL_DATE_TIME_PATTERN));
        }
    }
}



