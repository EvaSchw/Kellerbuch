import java.io.IOException;

import javax.swing.JOptionPane;

import org.apache.pdfbox.exceptions.COSVisitorException;

import kellerbuch.pdfRechnung.PDFRechnung;

public class TestPDF
{

	public static void main(String[] args)
	{
		try
		{
			PDFRechnung rechnung = new PDFRechnung();
			System.out.println(rechnung);
		}
		catch (COSVisitorException | IOException e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}

}
