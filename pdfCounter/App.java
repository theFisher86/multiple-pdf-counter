package pdfCounter;

import java.io.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class App {

    public static void main(String args[]) throws FileNotFoundException, InterruptedException {
        JFrame frame = new JFrame();
        frame.add( new JLabel(" Outout" ), BorderLayout.NORTH );

        JTextArea ta = new JTextArea();
        TextAreaOutputStream taos = new TextAreaOutputStream(ta, "ta");
        PrintStream ps = new PrintStream( taos );
        System.setOut( ps );
        System.setErr( ps );



        frame.add( new JScrollPane( ta )  );

        frame.pack();
        frame.setVisible( true );
        frame.setSize(900, 300);

        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        File root = new File(s);
        int count = 0;
            boolean recursive = true;

            Collection files = FileUtils.listFiles(root, null, recursive);

            for (Iterator iterator = files.iterator(); iterator.hasNext(); ) {
                try {
                    File file = (File) iterator.next();
                    if (file.getName().endsWith(".pdf")) {
                        System.out.println(file.getAbsolutePath());
                        PDDocument doc = PDDocument.load(new File(file.getAbsolutePath()));
                        count = doc.getNumberOfPages() + count;
                        doc.close();
                    }
                }
                catch (Exception e) {
                    continue;
                }


            }
            try (PrintStream out = new PrintStream(new FileOutputStream("NumberOfPages.txt"))) {
                    out.print(count);
            }

            try {
                Thread.sleep(100000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.exit(0);


    }
}




