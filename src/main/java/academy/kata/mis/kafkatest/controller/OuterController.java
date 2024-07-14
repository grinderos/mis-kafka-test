package academy.kata.mis.kafkatest.controller;

import academy.kata.mis.kafkatest.model.DocumentInfoDTO;
import academy.kata.mis.kafkatest.model.FileTransferDTO;
import academy.kata.mis.kafkatest.model.enums.DocumentDestination;
import academy.kata.mis.kafkatest.model.enums.DocumentRole;
import academy.kata.mis.kafkatest.model.enums.DocumentType;
import academy.kata.mis.kafkatest.service.KafkaSenderService;
import academy.kata.mis.kafkatest.service.ObjectKeeper;
import academy.kata.mis.kafkatest.service.PdfGenerationService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/kafka-test")
public class OuterController {

    private final KafkaSenderService kafkaSenderService;
    private final ObjectKeeper objectKeeper;
    private final PdfGenerationService pdfGenerationService;


    @Value("${spring.kafka.producer.topic.test-outgoing}")
    private String sendTopic;

    @SneakyThrows
    @GetMapping("/send-pdf")
    public ResponseEntity<byte[]> sendPdfFileInKafka() {

        UUID operationId = UUID.randomUUID();
//        UUID userId = UUID.randomUUID();
        UUID userId = UUID.fromString("31c2cd49-939a-4227-ae8e-c95b0a4456b6");
        System.out.println("operationId = " + operationId + "\n" + "userId = " + userId);
        DocumentType docType = DocumentType.PDF;
        DocumentRole docRole = DocumentRole.REPORT;
        DocumentDestination docDest = DocumentDestination.PERSONAL;

        byte[] pdf = pdfGenerationService.generatePdf(pdfGenerationService.generateInfo(operationId,
                userId, docType, docDest, docRole));

        FileTransferDTO newDoc = FileTransferDTO.builder()
                .operationId(operationId)
                .userId(userId)
                .documentType(docType)
                .destination(docDest)
                .role(docRole)
                .body(pdf)
                .build();


        System.out.println("newDoc ------------------------");
        System.out.println(newDoc);
        kafkaSenderService.sendToKafkaAsync(sendTopic, newDoc);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(pdf);
    }


    @SneakyThrows
    @GetMapping("/send-image")
    public ResponseEntity<byte[]> sendImageFileInKafka() {

        UUID operationId = UUID.randomUUID();
//        UUID userId = UUID.randomUUID();
        UUID userId = UUID.fromString("31c2cd49-939a-4227-ae8e-c95b0a4456b6");
        System.out.println("operationId = " + operationId + "\n" + "userId = " + userId);
        DocumentType docType = DocumentType.JPEG;
        DocumentRole docRole = DocumentRole.IDENTIFICATION_DOCUMENT;
        DocumentDestination docDest = DocumentDestination.PERSONAL;

        Path path = Paths.get("/image.jpeg");
        byte[] bytes = Files.readAllBytes(path);

        FileTransferDTO newDoc = FileTransferDTO.builder()
                .operationId(operationId)
                .userId(userId)
                .documentType(docType)
                .destination(docDest)
                .role(docRole)
                .body(bytes)
                .build();



        System.out.println("newDoc ------------------------");
        System.out.println(newDoc);
        kafkaSenderService.sendToKafkaAsync(sendTopic, newDoc);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
    }

    @SneakyThrows
    @GetMapping("/consume")
    public ResponseEntity<List<DocumentInfoDTO>> consumeFileFromKafka() {

        return ResponseEntity.ok(objectKeeper.getDocumentInfo());
    }
}
