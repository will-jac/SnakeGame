
/**
 * If thrown, the game is over.
 * 
 * @author Jack
 *
 */
public class GameOverException extends Exception
{
    /** so eclipse will shut up */
    private static final long serialVersionUID = 1L;
    
    /** Constructor */
    public GameOverException()
    {
        super("Game Over! Play again?");
    }
}
