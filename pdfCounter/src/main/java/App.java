package pdfCounter;

import java.io.File;

import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;

public class App {

    public static void main(String args[]) {
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        File root = new File(s);
        int count = 0;
        try {
            boolean recursive = true;

            Collection files = FileUtils.listFiles(root, null, recursive);

            for (Iterator iterator = files.iterator(); iterator.hasNext(); ) {
                File file = (File) iterator.next();
                if (file.getName().endsWith(".pdf")) {
                    System.out.println(file.getAbsolutePath());
                    PDDocument doc = PDDocument.load(new File(file.getAbsolutePath()));
                    count = doc.getNumberOfPages() + count;
                }

               
            }
          
            } catch(Exception e){
                e.printStackTrace();
            }
        
            try (PrintStream out = new PrintStream(new FileOutputStream("NumberOfPages.txt"))) {
                    out.print(count);
            }



    }
}




