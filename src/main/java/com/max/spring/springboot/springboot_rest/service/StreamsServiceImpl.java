package com.max.spring.springboot.springboot_rest.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfArray;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.commons.io.FileUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import static com.itextpdf.text.Annotation.FILE;

/**
 * @author ZuevMYu
 * @since 25.07.2024
 */
public class StreamsServiceImpl implements StreamsService {

    public String calculateManyStreamsData() {

        try {
            byte[] dataForWriting = new byte[20];
            new Random().nextBytes(dataForWriting);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();
            Paragraph paragraphOfTheDocument = new Paragraph(Font.NORMAL, "Paragraph of the document");
            Phrase phraseOfTheDocument = new Phrase(Font.NORMAL, "Phrase of the document");
            document.addTitle("My Title");
            document.addAuthor("max");
            document.add(paragraphOfTheDocument);
            document.add(phraseOfTheDocument);
            document.close();
            byte[] pdfBytes = byteArrayOutputStream.toByteArray();
            File file = new File("C://tmp/data/pdffile.pdf");
            FileUtils.writeByteArrayToFile(file, pdfBytes);
            //сложный многопоточный рассчет
            return "some string data";
        } catch (IOException | DocumentException e) {
            throw new RuntimeException(e);
        }
    }
}
