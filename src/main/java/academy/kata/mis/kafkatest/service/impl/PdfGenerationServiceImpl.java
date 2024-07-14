package academy.kata.mis.kafkatest.service.impl;

import academy.kata.mis.kafkatest.KafkaTestApplication;
import academy.kata.mis.kafkatest.model.enums.DocumentDestination;
import academy.kata.mis.kafkatest.model.enums.DocumentRole;
import academy.kata.mis.kafkatest.model.enums.DocumentType;
import academy.kata.mis.kafkatest.service.PdfGenerationService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class PdfGenerationServiceImpl implements PdfGenerationService {
    @Override
    public byte[] generatePdf(String text) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);
            try (InputStream fontStream = KafkaTestApplication.class.
                    getResourceAsStream("/timesnrcyrmt.ttf")) {
                PDType0Font customFont = PDType0Font.load(document, fontStream);

                try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                    contentStream.setFont(customFont, 12);

                    float y = 700;
                    String[] lines = text.split("\n");
                    for (String line : lines) {
                        contentStream.beginText();
                        contentStream.newLineAtOffset(50, y);
                        contentStream.showText(line);
                        contentStream.newLine();
                        contentStream.endText();
                        y -= 15;
                    }
                }
            }
            document.save(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }

    @Override
    public String generateInfo(UUID operationId,
                               UUID userId,
                               DocumentType documentType,
                               DocumentDestination destination,
                               DocumentRole role) {
        return String.format("""
                        operationId: %s
                        userId: %s
                        documentType: %s
                        destination: %s
                        role: %s
                        """,
                operationId,
                userId,
                documentType,
                destination,
                role
        );
    }
}
