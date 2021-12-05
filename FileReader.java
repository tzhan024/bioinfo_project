import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {
    public static void main(String[] args) throws FileNotFoundException {
        String[] seq = tfaReader("./src/BB11001.tfa");
        for(int i = 0; i < seq.length; i++)
        {
            System.out.println("main: " + seq[i]);
        }
    }
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
        return new String[0];
    }
}