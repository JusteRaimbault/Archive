/**
 * 
 */
package archive;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;

/**
 * @author Raimbault Juste <br/> <a href="mailto:juste.raimbault@polytechnique.edu">juste.raimbault@polytechnique.edu</a>
 *
 */
public class Archive {
	
    public static void main(String[] args) throws Exception {
       
        String odd = args[0],even=args[1],output=args[2];
    	
        Document document = new Document();
        PdfCopy copy = new PdfCopy(document, new FileOutputStream(output));
        document.open();

        PdfReader readerodd = new PdfReader(odd);
        PdfReader readereven = new PdfReader(even);
        int n1 = readerodd.getNumberOfPages(),n2=readereven.getNumberOfPages();
        //n1 and n2 are always the same !
        for (int page = 1; page <= Math.min(n1, n2); page++) {
            copy.addPage(copy.getImportedPage(readerodd, page));
            //beware, even pages are in inverse order !
            copy.addPage(copy.getImportedPage(readereven, n2 - page + 1));
        }
        //if same number of pages ok.
        //if not find the last one (in theory, will iterate on all remaining anyway)
        
        
        copy.freeReader(readereven);
        copy.freeReader(readerodd);
        readereven.close();readerodd.close();
        document.close();
    }

}
