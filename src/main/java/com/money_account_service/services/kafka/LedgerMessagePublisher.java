package com.money_account_service.services.kafka;

import com.money_account_service.entities.LedgerAvroRecord;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
public class LedgerMessagePublisher {

    private final KafkaTemplate kafkaTemplate;

    @Value("${spring.kafka.topic.orders}")
    private String topicName;

    public void publish(LedgerAvroRecord ledgerAvroRecord) {
        ProducerRecord<String, LedgerAvroRecord> producerRecord = new ProducerRecord<>(topicName, ledgerAvroRecord);
        try {
            final var sendResult = kafkaTemplate.send(producerRecord);
            kafkaTemplate.flush();
            sendResult.get();
        } catch (final InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (final KafkaException | ExecutionException e) {
        }
    }
}
