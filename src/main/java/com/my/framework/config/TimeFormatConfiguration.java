package com.my.framework.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Configuration
public class TimeFormatConfiguration {

    private static final String dateFormal = "yyyy-MM-dd";
    private static final String timeFormal = "HH:mm:ss";
    private static final String dateTimeFormal = "yyyy-MM-dd HH:mm:ss";
    private static final String instantFormal = "yyyy-MM-dd HH:mm:ss";

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {

        return builder -> {
            builder.simpleDateFormat(dateTimeFormal);
            //返回时间数据序列化
            builder.serializerByType(Instant.class, new JsonSerializer<Instant>() {
                @Override
                public void serialize(Instant value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                    gen.writeString(
                        DateTimeFormatter
                            .ofPattern(instantFormal)
                            .withZone(ZoneId.systemDefault())
                            .format(value));
                }
            });
            builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(dateFormal)));
            builder.serializers(new LocalTimeSerializer(DateTimeFormatter.ofPattern(timeFormal)));
            builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormal)));
            //接收时间数据反序列化
            builder.deserializers(InstantDeserializer.OFFSET_DATE_TIME);
            builder.deserializers(new LocalDateDeserializer(DateTimeFormatter.ofPattern(dateFormal)));
            builder.deserializers(new LocalTimeDeserializer(DateTimeFormatter.ofPattern(timeFormal)));
            builder.deserializers(new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(dateTimeFormal)));
        };
    }
}
