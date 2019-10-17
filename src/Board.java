import java.awt.Point;
import java.util.LinkedList;

/**
 * Contains the tiles in the game. Also contains the snake and food objects.
 * 
 * @author Jack
 *
 */
public class Board
{
    /** 2D array of the board. [x][y] */
    TileType[][] board;
    /** The snake the player moves around */
    private Snake snake;
    /** The food the snake can eat */
    private Food food;
    private Point foodPoint;
    
    protected int width;
    protected int height;
    
    /**
     * Creates the board, snake, and first food object.
     * The snake is placed in the middle of the board moving east, and the food is randomly generated.
     * 
     * @param width The width of the board.
     * @param height The height of the board.
     */
    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        board = new TileType[width + 1][height + 1];
        createBoard();
        Point center = new Point(width / 2, height / 2);
        snake = new Snake(center, 3, Direction.EAST);
        addSnake();
        food = new Food(width, height);
        createFood();
    }
    private void createBoard() {
        // make sure everything starts as empty
        for (int x = 0; x <= width; ++x) {
            for (int y = 0; y <= height; ++y) {
                board[x][y] = TileType.empty;
            }
        }
        for (int x = 0; x <= width; ++x) {
            board[x][0] = TileType.border;
            board[x][height] = TileType.border;
        }
        for (int y = 0; y <= height; ++y) {
            board[0][y] = TileType.border;
            board[width][y] = TileType.border;
        }
    }
    private void addSnake() {
        for (Point p : snake.snake) {
            board[p.x][p.y] = TileType.snake;
        }
    }
    private void createFood() {
        boolean noFood = true;
        while (noFood) {
            foodPoint = food.newFood();
            noFood = false;
            // if the food we just created is inside the snake, don't create that food; start over.
            for (Point p : snake.snake) {
                if (foodPoint.equals(p))
                    noFood = true;
                    break;
            }
        }
        board[foodPoint.x][foodPoint.y] = TileType.food;
    }
    public void update(Direction dir) throws GameOverException {
            
        Point snakeHead = snake.setHead(dir);
        TileType hit = board[snakeHead.x][snakeHead.y];
        
        System.out.println("hit: " + hit + " : "+ snakeHead);
        
        // check if we hit the food. If we didn't, remove the tail (otherwise, we'll grow).
        if (hit != TileType.food) {
            Point tail = snake.getTail();
            board[tail.x][tail.y] = TileType.empty;
            snake.removeTail();
        }
        // check what we hit now
        switch (hit) {
            case food   :   createFood();  // no break, roll over
            case empty  :   board[snakeHead.x][snakeHead.y] = TileType.snake; // it's valid, so change it to being a snake.
                            break;
            case snake  :   // we'll want to do the same thing; throw an exception
                            System.out.println("hit snake");
            case border :   throw new GameOverException();
        }
    }
    public LinkedList<Point> getSnake() {
        return snake.snake;
    }
    public Point getFood() {
        return foodPoint;
    }
}
