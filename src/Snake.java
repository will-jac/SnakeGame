import java.awt.Point;
import java.util.LinkedList;

/**
 * Holds the snake object that the player controls
 * 
 * The snake itself is a Stack of points, each represents the location of a snake "unit".
 * The snakehead is the start of this arraylist, this will move and change as the game updates
 * 
 * @author Jack
 *
 */
public class Snake
{
    LinkedList<Point> snake;
    Point snakeHead;
    
    /** Creates a snake from the point specified, with the size indicated, in the indicated direction 
     * 
     * @param initialPoint
     */
    public Snake(Point initialPoint, int initialSize, Direction startingDirection) {
        snakeHead = initialPoint;
        snake = new LinkedList<>();
        snake.add(snakeHead);

        for (int i = 0; i < initialSize; ++i) {
            setHead(startingDirection);
        }
    }

    /** 
     * Creates a new head. Does not remove the old tail.
     * @return The location of the new head.
     * @throws GameOverException If the snake hits itself
     */
    public Point setHead(Direction dir) {
        // create a new snakeHead so we can move the snake one at a time
        snakeHead = snakeHead.getLocation();
        // actually move that new snakeHead
        switch (dir) {
            case NORTH: snakeHead.translate(0, -1);
                break;
            case SOUTH: snakeHead.translate(0, 1);
                break;
            case EAST : snakeHead.translate(1, 0);
                break;
            case WEST : snakeHead.translate(-1, 0);
                break;
        }
        // add the head to the front
        snake.push(snakeHead);
        
        return snakeHead;
    }
    public void removeTail() {
        snake.pollLast();
    }
    public Point getTail() {
        return snake.peekLast();
    }
}
