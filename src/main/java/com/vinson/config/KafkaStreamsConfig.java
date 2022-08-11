package com.vinson.config;

import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;

import java.util.Arrays;
import java.util.Locale;

@Configuration
@EnableKafkaStreams
public class KafkaStreamsConfig {

    @Bean
    public KStream<Object, Object> kStream(StreamsBuilder streamsBuilder){
        KStream<Object, Object> stream = streamsBuilder.stream("test1");

        stream.flatMapValues(value -> Arrays.asList(value.toString().toLowerCase(Locale.getDefault()).split(" ")))
                .foreach((key,value) -> System.out.println(key + ": "+ value));

        return stream;
    }
}

