import java.io.File;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Scanner;

/**
 * Created by Adam Collins on 19/01/2016.
 */
public class Puzzle {
    private ArrayList<String> dictionary;
    private Scanner sc;
    private File f;
    private String answer;
    private String board;
    private int answerSize;
    private boolean isCheating;

    public Puzzle(boolean cheat) {
        if (cheat) {
            isCheating = true;
            answerSize = getRandomWordLength();
            readFile(answerSize);
            board = "";
            answer = "";
            resetBoardCheat();
        } else {
            isCheating = false;
            readFile(0);
            resetBoard();
        }

    }


    private int getRandomWordLength(int min, int max) {
        return min + (int) (Math.random() * max);
    }

    private int getRandomWordLength() {
        return getRandomWordLength(5, 10);
    }

    //Helpers
    private void resetBoard() {
        board = "";
        answer = getRandomWord();
        answerSize = answer.length();
        for (char c : answer.toCharArray()) board += "■";
    }

    private void resetBoardCheat() {
        board = "";
        for (int i = 0; i <answerSize; i++) board +="■";
    }

    public void drawBoard() {
        System.out.println("\n" + board);
    }

    //Accessors
    public String getBoard() {
        return board;
    }

    public void setBoard(String s) {
        board = s;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean hasWon() {
        return answer.equals(board);
    }

    public void readFile(int n) {
        dictionary = new ArrayList<String>(95968);
        f = new File("words.txt");
        String line;
        try {
            sc = new Scanner(f);
            while (sc.hasNext()) {
                line = sc.nextLine();
                if (!Character.isUpperCase(line.charAt(0))) {
                    if (n > 0) {
                        if (line.length() == n) dictionary.add(line);
                    } else dictionary.add(line);
                }
            }
        } catch (Exception e) {
            System.out.println("File not Found.");
        }
    }


    public String getRandomWord() {
        return dictionary.get((int) (Math.random() * dictionary.size()));
    }


    public boolean isCheating()
    {
        return isCheating;
    }

    public int answerSize()
    {
        try {return answer.length();}
        catch (NullPointerException e)
        {
           return 0;
        }
    }


    public void updateCheat()
    {
        if (dictionary.size()<100)
        {
            answer = getRandomWord();
            isCheating = false;
        }
    }

    public void removeInvalidWords(String g)
    {
        for(int i = dictionary.size()-1; i>=0; i--)
        {
            String w = dictionary.get(i);
            if(w.contains(g)) dictionary.remove(i);
        }
    }
}
