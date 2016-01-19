package kellerbuch.pdfRechnung;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;

public class PDFRechnung
{
	private PDDocument dokument;
	private PDPage seite;
	
	public PDFRechnung() throws COSVisitorException, IOException
	{
		dokument = new PDDocument();
		seite = new PDPage(PDPage.PAGE_SIZE_A4);
		dokument.addPage(seite);
		
		PDPageContentStream contentStream = new PDPageContentStream(dokument, seite);
		
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(100, 700);
		contentStream.drawString("Hello World!!!");
		contentStream.endText();
		
		contentStream.close();
		
		dokument.save("testpdf.pdf");
		dokument.close();
	}
}
