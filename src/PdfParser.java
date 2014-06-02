import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;

import org.pdfbox.pdfparser.PDFParser;
import org.pdfbox.util.PDFTextStripper;


public class PdfParser {

   /**
   * @param args
   */
   // TODO 自动生成方法存根

	public String pdfParser(String News) throws Exception{ 
		     String pdfFile="";  
            FileInputStream   fis   =   new   FileInputStream(News); 
           // BufferedWriter writer = new BufferedWriter(new FileWriter("F:\\task\\pdf_change.txt"));
            PDFParser   p   =   new   PDFParser(fis); 
            p.parse();         
            PDFTextStripper   ts   =   new   PDFTextStripper();         
            String   s   =   ts.getText(p.getPDDocument()); 
           // writer.write(s);
            System.out.println(s); 
            pdfFile=s; 
            fis.close(); 
            return pdfFile;
           // writer.close();
          
   }
} 
