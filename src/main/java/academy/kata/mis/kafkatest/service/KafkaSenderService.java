package academy.kata.mis.kafkatest.service;

public interface KafkaSenderService {
    void sendToKafkaAsync(String topic, Object message);
}
