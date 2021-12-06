import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.*;

public class FileReader {
//    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
//        String[] seq = tfaReader("./src/BB11001.tfa");
//        for(int i = 0; i < seq.length; i++)
//        {
//            System.out.println("tfa: " + seq[i]);
//        }
//        String[] xml = xmlReader("./src/BB11001.xml");
//        for(int i = 0; i < xml.length; i++)
//        {
//            System.out.println("xml: " + xml[i]);
//        }
//    }
    public static String[] tfaReader(String fileName) throws FileNotFoundException {
        ArrayList<String> sequences = new ArrayList<String>();
        ArrayList<String> result = new ArrayList<String>();
        try{
            File tfa = new File(fileName);
            Scanner in = new Scanner(tfa);
            while(in.hasNextLine())
            {
                sequences.add(in.nextLine());
            }
            in.close();
//            System.out.println(sequences);
            String data = "";
            for(int i = 0; i < sequences.size(); i++)
            {
//                String data = "";
                if(sequences.get(i).charAt(0) != '>')
                {
                    data = data + sequences.get(i);
                }
                else
                {
                    if(data.length() > 0)
                    {
                        result.add(data);
                        data = "";
                    }

                }
                if(i + 1 >= sequences.size())
                    result.add(data);
            }

//            for(int i = 0; i < sequences.size(); i++)
//            {
//                System.out.println(sequences.get(i));
//            }
//            System.out.println();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not exist: " + fileName);
            e.printStackTrace();
        }

        String[] resultArr = new String[result.size()];
        result.toArray(resultArr);
        return resultArr;

    }
    public static String[] xmlReader(String fileName)
    {
//        ArrayList<String> sequences = new ArrayList<String>();
        ArrayList<String> result = new ArrayList<String>();
        String prefix = "<seq-data>";
        try{
            File tfa = new File(fileName);
            Scanner in = new Scanner(tfa);
            while(in.hasNextLine())
            {
                String data = in.nextLine();
//                System.out.println(data);
                if(data.startsWith(prefix))
                    result.add(data.substring(prefix.length()));
            }
            in.close();
//            for(int i = 0; i < result.size(); i++)
//            {
//                System.out.println("result: " + result.get(i));
//            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not exist: " + fileName);
            e.printStackTrace();
        }

        String[] resultArr = new String[result.size()];
        result.toArray(resultArr);
        return resultArr;
    }
}
