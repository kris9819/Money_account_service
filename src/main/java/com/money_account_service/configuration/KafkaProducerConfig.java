package com.money_account_service.configuration;

//import com.money_account_service.entities.LedgerAvroRecord;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    private static final String SCHEMA_REGISTRY_URL = "schema.registry.url";

    @Value("${spring.kafka.producer.bootstrap-servers}")
    String bootstrapServers;

    @Value("${spring.kafka.producer.schema.registry.url}")
    String schemaRegistryUrl;

//    @Bean
//    public KafkaTemplate<String, LedgerAvroRecord> kafkaTemplate(ProducerFactory<String, LedgerAvroRecord> producerFactory) {
//        return new KafkaTemplate<>(producerFactory);
//    }
//
//    @Bean
//    public ProducerFactory<String, LedgerAvroRecord> producerFactory() {
//        Map<String, Object> producerConfig = getProducerConfig();
//        return new DefaultKafkaProducerFactory<>(producerConfig);
//    }

    private Map<String, Object> getProducerConfig() {
        Map<String, Object> properties = new HashMap<>();

        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        properties.put(ProducerConfig.RETRIES_CONFIG, 1);
        properties.put(ProducerConfig.RECONNECT_BACKOFF_MS_CONFIG, "5000");
        properties.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, "5000");
        properties.put(SCHEMA_REGISTRY_URL, schemaRegistryUrl);

        return properties;
    }
}
