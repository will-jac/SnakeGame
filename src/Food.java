import java.awt.Point;
import java.util.Random;

public class Food
{
    /** where the current food object is located */
    private Point food;
    private int xMax;
    private int yMax;
    private Random seed;
    
    /** 
     * Creates the food object. Does not generate a food location.
     * 
     *  @param xMax The maximum x location (exclusive).
     *  @param yMax The maximum y location (exclusive).
     */
    public Food(int xMax, int yMax) {
        this.xMax = xMax;
        this.yMax = yMax;
        seed = new Random();
    }
    
    /** Creates a new food object in a random location, from 0 (exclusive) to x / y Max (exclusive)
     * 
     *  @return the Point location of the new Food
     */
    public Point newFood() {
        boolean isValid = false;
        while (!isValid) {
            isValid = true;
            // create the food object
            food = new Point(seed.nextInt(xMax - 1) + 1, seed.nextInt(yMax - 1) + 1);
        }
        return food;
    }
}
