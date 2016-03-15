import java.util.Scanner;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
/**
 * Created by Adam Collins on 19/01/2016.
 */
public class Game
{
    private Puzzle puzzle;
    private Gallows gallows;
    private static final boolean CHEAT = false; //Switch to false to give the computer a timeout for cheating(turn off cheating) Bad Computer!.
    public boolean playing;
    
    Game()
    {
        puzzle = new Puzzle(CHEAT);
        gallows = new Gallows();
    }

    public void playGame()
    {
        playing = true;
        gallows.drawGallows();
        puzzle.drawBoard();
        Scanner userIn = new Scanner(System.in);
        while(playing)
        {
           playTurn(userIn.nextLine());
        }
    }
    
    public void playTurn(String t)
    {
        checkAnswer(t);
        if(puzzle.isCheating()) puzzle.updateCheat();
        gallows.drawGallows();
        puzzle.drawBoard();
        if(puzzle.hasWon())
        {
            win();
            return;
        }
        else if(gallows.hasLost())
        {
            lose();
            return;
        }
        System.out.println("Choose a Letter or Word: ");
    }
    
    public void checkAnswer(String g)
    {
        String answer = puzzle.getAnswer();
        if(answer.equals("")){gallows.addIncorrectGuess(g); puzzle.removeInvalidWords(g); return;}
        String board = puzzle.getBoard();
        if(g.length()<1){ invalidChar(); return;}

        if(g.length()>1){
            if(!puzzle.hasWon())
                gallows.addIncorrectGuess(g);

            return;
        }


        String newBoard = "";
        for(int i = 0; i<puzzle.answerSize(); i++)
        {
            if(answer.charAt(i) == g.charAt(0)) newBoard+=g;
            else if(board.length()>0&& board.charAt(i)>64) newBoard += puzzle.getBoard().charAt(i);
            else newBoard+="■";
        }
            if (newBoard.equals(board)) gallows.addIncorrectGuess(g);
            puzzle.setBoard(newBoard);

    }
    
    
    private void win()
    {
        System.out.println("You win. Congrats.");
        endGameMenu();
    }

    private void lose()
    {
        System.out.println("You lost :(  \nThe answer was " + puzzle.getAnswer());
        endGameMenu();
    }
    
    private void endGameMenu()
    {
        Scanner userIn = new Scanner(System.in);
        System.out.println("Play Again?[Y/N/DEF]");
        String reply = userIn.nextLine();
        if("def".equalsIgnoreCase(reply) || "define".equalsIgnoreCase(reply)) {searchAnswer(puzzle.getAnswer()); endGameMenu();}
        if("y".equalsIgnoreCase(reply) ||"yes".equalsIgnoreCase(reply))
        {
            puzzle = new Puzzle(CHEAT);
            gallows = new Gallows();
            playGame();
        }
        else System.exit(0);
    }
    

    private void searchAnswer(String answer)
    {
        if(Desktop.isDesktopSupported())
        {
            try
            {
                Desktop.getDesktop().browse(new URI("http://www.google.com/search?site=&source=hp&q=define+" + answer));
            } catch (Exception e)
            {
                System.out.println("Cannot find file.");
            }

        }
    }
    
    //Errors
    private void invalidChar()
    {
        System.out.println("Invalid Character.");
    }
    
    
    
}
