package az.bank.msaccount.utils;

import az.bank.msaccount.model.dto.OperationDto;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Table;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.List;

@Component
public class PdfCreatorUtil {

    public void createPdfFile(List<OperationDto> operations) {
        String inputFilePath = "C:\\Users\\Nigar\\Downloads\\account.pdf";
        PdfWriter writer = null;
        try {
            writer = new PdfWriter(inputFilePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        String[] theads = {"Account Id", "Date", "Operation Amount", "Category", "Description"};
        String imageFile = "C:\\Users\\Nigar\\Downloads\\printscreen-cropped.png";
        ImageData data = null;
        try {
            data = ImageDataFactory.create(imageFile);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Image img = new Image(data);
        document.add(img);
        document.add(buildTable(theads, operations));
        document.close();
    }

    private Cell newCell(String cellData) {
        Cell cell = new Cell();
        cell.add(cellData);
        return cell;
    }

    private Table buildTable(String[] theads, List<OperationDto> operations) {
        Table table = new Table(new float[]{140F, 110F, 70F, 80F, 120F});

        for (String thead : theads) {
            table.addCell(newCell(thead));
        }
        for (OperationDto operation : operations) {
            char increased = '-';
            if (operation.getIncreased()) {
                increased = '+';
            }
            String operationDate = operation.getDate().toString();
            String dateFormatted = operationDate.substring(0, 10) + " " + operationDate.substring(11, 16);
            table.addCell(newCell(operation.getAccountId()));
            table.addCell(newCell(dateFormatted));
            table.addCell(newCell(increased + "" + operation.getAmount() + " " + operation.getCurrency()));
            table.addCell(newCell(operation.getCategory()));
            table.addCell(newCell(operation.getDescription()));
        }
        return table;
    }
}
