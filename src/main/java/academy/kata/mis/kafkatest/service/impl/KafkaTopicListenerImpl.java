package academy.kata.mis.kafkatest.service.impl;

import academy.kata.mis.kafkatest.model.DocumentInfoDTO;
import academy.kata.mis.kafkatest.service.KafkaTopicListener;
import academy.kata.mis.kafkatest.service.ObjectKeeper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KafkaTopicListenerImpl implements KafkaTopicListener {
    private final ObjectKeeper objectKeeper;

    @Override
    @KafkaListener(
            topics = "${spring.kafka.consumer.topic.test-incoming}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "singleFactory")
    public void listen(LinkedHashMap<String, Object> message) {
        DocumentInfoDTO dto = DocumentInfoDTO.builder()
                .operationId(UUID.fromString((String) message.get("operationId")))
                .documentId(UUID.fromString((String) message.get("documentId")))
                .creationDate(LocalDateTime.parse((String) message.get("creationDate")))
                .expirationDate(LocalDateTime.parse((String) message.get("expirationDate")))
                .build();
        objectKeeper.setDocumentInfo(dto);
        System.out.println("-------------------------------------------------");
        System.out.println("getting DTO\n" + dto);
        System.out.println("-------------------------------------------------");

    }
}
