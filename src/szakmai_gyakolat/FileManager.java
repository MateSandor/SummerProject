package szakmai_gyakolat;

import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;

public class FileManager {
	
	/**
	 * Save the selected Editable or Noeditable cards into PDF (A4 version)
	 * @param fronts
	 * @param backs
	 * @param fileName
	 */
	public static void writePdfA4 (Image[] fronts, Image[] backs, String fileName) {
		
		Document document = new Document(PageSize.A4);
		document.setMargins(0, 0, 0, 0);

		try {
			PdfWriter.getInstance(document, new FileOutputStream(fileName + ".pdf"));
			document.open();
		
			int countedFront = 0;
			int countedBack = 0;
			int frontIndex = 0;
			int backIndex = 0;
			
			for (int i = 0; i < fronts.length; i++) {
				for (int j = 0; j < 2 && countedFront < fronts.length; j++) {
					fronts[frontIndex].scaleAbsolute(159f, 247f);
					fronts[frontIndex].setRotationDegrees(-90);
					fronts[frontIndex].setAbsolutePosition(50.25f + j*247.5f, 842 - 181.5f - i*159.5f);
					document.add(fronts[frontIndex]);
					frontIndex++;
					countedFront++;
				
					if  (countedFront %10 == 0 || countedFront == fronts.length) {
						document.newPage();
						i = -1;
					
						for (int k = 0; k < countedFront; k++) {
							for (int l = 1; l >= 0 && countedBack < countedFront; l--) {
								backs[backIndex].setRotationDegrees(-90);
								backs[backIndex].setAbsolutePosition(50.25f + l*247.5f, 842 - 181.5f - k*159.5f);
								document.add(backs[backIndex]);
								backIndex++;
								countedBack++;
								if (countedBack == countedFront && countedFront != fronts.length) {
									document.newPage();	
								}
							}
						}
					}
				}
			}
			document.close();
			JOptionPane.showMessageDialog(null, "Sikeres mentés!", "Utóirat", 1);
		} catch (BadElementException bee) { 
			bee.getMessage(); 
		}
		catch (DocumentException de) { 
			de.getMessage(); 
		}
		catch (IOException ioe) { 
			ioe.getMessage(); 
		}
	}
	
	/**
	 * Save the selected Editable or Noeditable cards into PDF (Press version)
	 * @param cards
	 * @param fileName
	 */
	public static void writePdfPrint (Image[] cards,  String fileName) {
		
		Document document = new Document();
		document.setPageSize(new com.itextpdf.text.Rectangle(675,1050));
		document.setMargins(0, 0, 0, 0);
		
		try {
			PdfWriter.getInstance(document, new FileOutputStream(fileName + ".pdf"));
			document.open();
			
			for (int i = 0; i < cards.length; i++) {
				document.add(cards[i]);
				document.newPage();
			}
			
			document.close();
			
		} catch (BadElementException bee) { 
			bee.getMessage(); 
		}
		catch (DocumentException de) { 
			de.getMessage(); 
		}
		catch (IOException ioe) { 
			ioe.getMessage(); 
		}
	}
	
}