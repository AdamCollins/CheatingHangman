/**
 * Created by Adam Collins on 19/01/2016.
 */
import java.util.ArrayList;
public class Gallows
{
    private ArrayList<String> incorrectGuesses;
    private final int DEAD =6;
   Gallows()
   {
       incorrectGuesses = new ArrayList<String>();
   }
   
   public void addIncorrectGuess(String g)
   {
       incorrectGuesses.add(g);
   }
   
   public void drawGallows()
   {
       System.out.println("\f"+hangmanImage[incorrectGuesses.size()]);
       System.out.println("Guesses: ");
        for (String i:incorrectGuesses) System.out.print(i+" ");
   }
   
   public boolean hasLost()
   {
       return incorrectGuesses.size()==DEAD;
   }
   
    private String[] hangmanImage = {"+----+\n"+
            " |\n"+
            " |\n"+
            " |\n"+
            "/ \\\n",

            "+----+\n"+
                    " |    O\n"+
                    " |\n"+
                    " |\n"+
                    "/ \\\n",

            "+----+\n"+
                    " |    O\n"+
                    " |    +\n"+
                    " |\n"+
                    "/ \\\n",

            "+----+\n"+
                    " |    O\n"+
                    " |    +-\n"+
                    " |\n"+
                    "/ \\\n",


            "+----+\n"+
                    " |    O\n"+
                    " |   -+-\n"+
                    " |\n"+
                    "/ \\\n",


            "+----+\n"+
                    " |    O\n"+
                    " |   -+-\n"+
                    " |   / \n"+
                    "/ \\\n",


            "+----+\n"+
                    " |    O\n"+
                    " |   -+-\n"+
                    " |   / \\\n"+
                    "/ \\\n",

    };
}
