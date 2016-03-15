import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Pyram on 19/01/2016.
 */
public class FileReader extends JFrame
{
    private static ArrayList<String> dictionary;
    private Scanner sc;
    private String input;
    private File f;
    private JTextField item1;
    FileReader()
    {






        dictionary = new ArrayList<String>(95968);
        input = "words.txt";//JOptionPane.showInputDialog("Please select file.");
        f = new File(input);
        readFile();
    }

    public void readFile()
    {
        String line;
        try
        {
            sc = new Scanner(f);
            while (sc.hasNext())
            {
                line = sc.nextLine();
                if (!Character.isUpperCase(line.charAt(0))){
                    dictionary.add(line);
                }
            }
        }catch(Exception e)
        {
            System.out.println("File not Found.");
        }
    }

    public static String getRandomWord()
    {
        return dictionary.get((int)(Math.random()*dictionary.size()));
    }

}
