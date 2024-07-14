package academy.kata.mis.kafkatest.service;

import org.springframework.kafka.annotation.KafkaListener;

import java.util.LinkedHashMap;

public interface KafkaTopicListener {
    @KafkaListener(
            topics = "${spring.kafka.consumer.topic.test-incoming}",
            groupId = "${spring.kafka.consumer.report_group}",
            containerFactory = "singleFactory")
    void listen(LinkedHashMap<String, Object> message);
}
